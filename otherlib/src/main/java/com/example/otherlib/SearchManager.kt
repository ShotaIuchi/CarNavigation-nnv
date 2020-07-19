package com.example.otherlib

import android.view.SurfaceHolder
import android.view.View
import com.example.otherlib.data.OGeoPoint
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates

object SearchManager {

    class Listener {
        fun onFix(reqId: Int, resultCode: Int, handle: String) {}
        fun onSuggestion(reqId: Int, resultCode: Int, handle: String) {}
        fun onSearch(reqId: Int, resultCode: Int, handle: String) {}
    }

    private var reqId by Delegates.notNull<Int>()

    private val listeners = ArrayList<Listener>()

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun removeListener(listener: Listener) {
        listeners.remove(listener)
    }

    suspend fun create(surface: SurfaceHolder, w:Int, h: Int) : Int {
        reqId = Date().time.toInt()
        GlobalScope.launch {
            delay(100)
            listeners.forEach {
                it.onFix(reqId, 0, Date().time.toString())
            }
        }
        return reqId
    }

}