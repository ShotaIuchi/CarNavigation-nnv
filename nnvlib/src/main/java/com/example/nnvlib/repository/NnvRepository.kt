package com.example.nnvlib.repository

import com.example.nnvlib.NnvViewModel
import com.example.nnvlib.model.NnvHandle
import com.example.otherlib.NnvManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NnvRepository(private val viewModel: NnvViewModel
) {

    var requestId = -1

    fun initialize() {
        GlobalScope.launch {
            NnvManager.addListener(object : NnvManager.Listener {
                override fun onFix(reqId: Int, resultCode: Int, handle: String) {
                    if (requestId == reqId) {
                        NnvManager.removeListener(this)
                        viewModel.updateNnvHandle(NnvHandle(handle))
                    }
                }
            })
            requestId = NnvManager.create()
        }
    }
}