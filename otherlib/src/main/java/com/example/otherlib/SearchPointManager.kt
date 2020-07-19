package com.example.otherlib

import com.example.otherlib.data.OGeoPoint
import com.example.otherlib.data.OPointInfo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.properties.Delegates

object SearchPointManager {

    interface Listener {
        fun onSearch(reqId: Int, resultCode: Int, pointInfo: OPointInfo) {}
    }

    private var reqId by Delegates.notNull<Int>()

    private val listeners = ArrayList<Listener>()

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

    suspend fun search(point: OGeoPoint) : Int {
        reqId = Date().time.toInt()
        GlobalScope.launch {
            delay(100)
            listeners.forEach {
                it.onSearch(reqId, 0, OPointInfo(point, "shiga", "shiga-ken", "biwako"))
            }
        }
        return reqId
    }

}