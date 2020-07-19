package com.example.nnvlib.repository

import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.GeoPoint
import com.example.nnvlib.model.PointInfo
import com.example.otherlib.SearchPointManager
import com.example.otherlib.data.ONnvHandle
import com.example.otherlib.data.OPointInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Geo2PointInfoRepository(
    private val viewModel: NnvViewModel,
    private val nnvHandle: ONnvHandle
) {

    fun search(geoPoint: GeoPoint) {
        GlobalScope.launch {
            var requestId = -1
            SearchPointManager.addListener(object : SearchPointManager.Listener {
                override fun onSearch(reqId: Int, resultCode: Int, pointInfo: OPointInfo) {
                    if (requestId == reqId) {
                        SearchPointManager.removeListener(this)
                        viewModel.updateLongTapPoint(PointInfo(pointInfo))
                    }
                }
            })
            requestId = SearchPointManager.search(geoPoint.oGeoPoint)
        }
    }

}