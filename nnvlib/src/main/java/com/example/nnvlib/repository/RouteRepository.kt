package com.example.nnvlib.repository

import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.RouteInfo
import com.example.nnvlib.model.RouteRequest
import com.example.otherlib.SearchManager
import com.example.otherlib.data.ONnvHandle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.suspendCoroutine

class RouteRepository(
    private val viewModel: NnvViewModel,
    private val nnvHandle: ONnvHandle
) {



//    suspend fun searchSuggestion(routeRequest: RouteRequest) {
//        return suspendCoroutine {
//            var requestId = -1
//            SearchManager.addListener(object : SearchManager.Listener {
//            })
//            requestId = SearchManager.search(nnvHandle, false)
//        }
//    }


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
