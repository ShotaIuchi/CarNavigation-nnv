package com.example.otherlib

import android.view.SurfaceHolder
import android.view.View
import com.example.otherlib.data.OGeoPoint
import com.example.otherlib.data.OMapHandle
import com.example.otherlib.data.ONnvHandle
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates

object MapManager {

    interface Listener {
        fun onFix(reqId: Int, resultCode: Int, handle: OMapHandle) {}
        fun onTouch(geoPoint: OGeoPoint) {}
    }

    private var reqId by Delegates.notNull<Int>()

    private val listeners = ArrayList<Listener>()

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

    fun onLongClick(view: View) {
        GlobalScope.launch {
            delay(1)
            listeners.forEach {
                it.onTouch(OGeoPoint(Date().time.toInt(), Date().time.toInt()))
            }
        }
    }

    suspend fun create(nnvHandle: ONnvHandle, surface: SurfaceHolder, w:Int, h: Int) : Int {
        reqId = Date().time.toInt()
        GlobalScope.launch {
            delay(100)
            listeners.forEach {
                it.onFix(reqId, 0, OMapHandle(nnvHandle, Date().time.toString()))
            }
        }
        return reqId
    }

}