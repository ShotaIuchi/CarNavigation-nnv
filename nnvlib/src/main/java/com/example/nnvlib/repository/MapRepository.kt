package com.example.nnvlib.repository

import android.view.SurfaceHolder
import com.example.nnvlib.NnvViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleOnSubscribe
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.properties.Delegates

class MapRepository(val viewModel: NnvViewModel, val surface: SurfaceHolder, val w:Int, val h: Int) {

    fun initialize() {
//        Single.create((SingleOnSubscribe<String> {
//            aacMxpDataSource.createMxp(object : FinishCallback<AacMxpHandle> {
//                override fun onFinish(handle: AacMxpHandle) {
//                    it.onSuccess(handle)
//                }
//            })
//        })).subscribe(object : DisposableSingleObserver<AacMxpHandle>() {
//            override fun onSuccess(t: AacMxpHandle?) {
//                aacMapViewModel?.postMxpHandle(t)
//            }
//            override fun onError(e: Throwable?) {
//            }
//        })
    }

    class XXXMap() {

        interface MapListener {
            fun onFix(reqId: Int, resultCode: Int, handle: String)
        }

        private var reqId by Delegates.notNull<Int>()

        private val listeners = ArrayList<MapListener>()

        fun addListener(listener: MapListener) {
            listeners.add(listener)
        }

        fun removeListener(listener: MapListener) {
            listeners.remove(listener)
        }

        fun create(surface: SurfaceHolder, w:Int, h: Int) : Int {
            reqId = Date().time.toInt()
            GlobalScope.launch {
                delay(1000)
                listeners.forEach {
                    it.onFix(reqId, 0, Date().time.toString())
                }
            }
            return reqId
        }
    }

}