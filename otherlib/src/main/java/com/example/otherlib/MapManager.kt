package com.example.otherlib

import android.annotation.SuppressLint
import android.view.SurfaceHolder
import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates

object MapManager {
//
//    companion object {
//
//        @SuppressLint("StaticFieldLeak")
//        @Volatile private var INSTANCE: MapManager? = null
//
//        fun getInstance() =
//            INSTANCE ?: synchronized(MapManager::class.java) {
//                INSTANCE ?: MapManager()
//            }
//
//        @VisibleForTesting
//        fun destroyInstance() {
//            INSTANCE = null
//        }
//
//    }

    interface Listener {
        fun onFix(reqId: Int, resultCode: Int, handle: String)
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