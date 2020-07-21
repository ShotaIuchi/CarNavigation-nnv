package com.example.nnvlib.repository

import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.SuggestionInfo
import com.example.otherlib.SearchManager
import com.example.otherlib.data.ONnvHandle
import com.example.otherlib.data.OSuggestionInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SearchRepository(
    private val viewModel: NnvViewModel,
    private val nnvHandle: ONnvHandle
) {
    var requestId = -1

    suspend fun close(): Int {
        return suspendCoroutine { GlobalScope.launch {
            SearchManager.addListener(object : SearchManager.Listener {
                override fun onCloseFix(reqId: Int, resultCode: Int) {
                    if (requestId == reqId) {
                        SearchManager.removeListener(this)
                        it.resume(resultCode)
                    }
                }
            })
            SearchManager.close(nnvHandle, requestId)
        }}
    }

    fun searchSuggestion(ward: String) {
        GlobalScope.launch {
            SearchManager.addListener(object : SearchManager.Listener {
                override fun onSuggestionFix(reqId: Int, resultCode: Int, suggestion: OSuggestionInfo, isFix: Boolean) {
                    if (requestId == reqId) {
                        val newData = ArrayList<SuggestionInfo>()
                        viewModel.searchSuggestion.value?.peekContent()?.let {
                            newData.addAll(it)
                        }
                        newData.add(SuggestionInfo(suggestion))
                        viewModel.updateSearchSuggestion(newData)
                        if (isFix) {
                            SearchManager.removeListener(this)
                        }
                    }
                }
                override fun onCloseFix(reqId: Int, resultCode: Int) {
                    if (requestId == reqId) {
                        SearchManager.removeListener(this)
                    }
                }
            })
            requestId = SearchManager.search(nnvHandle, false)
        }
    }

}
