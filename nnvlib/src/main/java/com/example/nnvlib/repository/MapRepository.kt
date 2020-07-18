package com.example.nnvlib.repository

import android.view.SurfaceHolder
import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.MapHandle
import com.example.otherlib.MapManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MapRepository(
    private val viewModel: NnvViewModel,
    private val surface: SurfaceHolder,
    private val w:Int, private val h: Int) {

    fun initialize() {
        GlobalScope.launch {
            var requestId = -1
            MapManager.addListener(object : MapManager.Listener {
                override fun onFix(reqId: Int, resultCode: Int, handle: String) {
                    if (requestId == reqId) {
                        MapManager.removeListener(this)
                        viewModel.updateMapHandle(MapHandle(handle))
                    }
                }
            })
            requestId = MapManager.create(surface, w, h)
        }
    }

}