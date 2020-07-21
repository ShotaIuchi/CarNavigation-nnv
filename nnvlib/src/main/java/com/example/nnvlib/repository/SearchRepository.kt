package com.example.nnvlib.repository

import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.PointInfo
import com.example.nnvlib.model.RouteInfo
import com.example.nnvlib.model.RouteRequest
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

    suspend fun close(requestId: Int) : Int {
        return suspendCoroutine {
            GlobalScope.launch {
                SearchManager.addListener(object : SearchManager.Listener {
                    override fun onCloseFix(reqId: Int, resultCode: Int) {
                        if (requestId == reqId) {
                            it.resume(resultCode)
                        }
                    }
                })
                SearchManager.close(nnvHandle, requestId)
            }
        }
    }

    fun searchSuggestion(ward: String): Job {
        return GlobalScope.launch {
            var requestId = -1
            SearchManager.addListener(object : SearchManager.Listener {
                override fun onSuggestionFix(reqId: Int, resultCode: Int, suggestion: OSuggestionInfo, isFix: Boolean) {
                    if (requestId == reqId) {
                        val t = ArrayList<SuggestionInfo>()
                        viewModel.searchSuggestion.value?.peekContent()?.let { t.addAll(it) }
                        t.add(SuggestionInfo(suggestion))
                        viewModel.updateSearchSuggestion(t)
                        if (isFix) {
                            SearchManager.removeListener(this)
                        }
                    }
                }
            })
            requestId = SearchManager.search(nnvHandle, false)
        }
    }


//    fun searchSuggestion(routeRequest: RouteRequest) {
//
//
//
//        GlobalScope.launch {
//            var requestId = -1
//            SearchManager.addListener(object : SearchManager.Listener {
////                override fun onFix(reqId: Int, resultCode: Int, handle: String) {
////                    if (requestId == reqId) {
////                        SearchManager.removeListener(this)
////                        viewModel.updateRouteInfo(RouteInfo(handle))
////                    }
////                }
//
//            })
//            requestId = SearchManager.search(nnvHandle, true)
//        }
//    }
}
