package com.example.otherlib

import com.example.otherlib.data.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.properties.Delegates

object SearchManager {

    interface Listener {
        fun onCloseFix(reqId: Int, resultCode: Int) {}
        fun onSuggestionFix(reqId: Int, resultCode: Int, suggestion: OSuggestionInfo, isFix: Boolean) {}
        fun onSearchFix(reqId: Int, resultCode: Int, search: OSearchInfo, isFix: Boolean) {}
    }

    private var reqId by Delegates.notNull<Int>()

    private val listeners = ArrayList<Listener>()

    fun addListener(listener: Listener) {
        synchronized(this) {
            GlobalScope.launch {
                delay(100)
                listeners.add(listener)
            }
        }
    }

    fun removeListener(listener: Listener) {
        synchronized(this) {
            GlobalScope.launch {
                delay(100)
                listeners.remove(listener)
            }
        }
    }

    fun search(nnvHandle: ONnvHandle?, isSuggestion: Boolean) : Int {
        reqId = Date().time.toInt()
        GlobalScope.launch {
            delay(100)
            if (isSuggestion) {
                GlobalScope.launch {

                    (0..10).forEach { n ->
                        delay(10)
                        synchronized(this) {
                            listeners.forEach {
                                it.onSuggestionFix(reqId, 0, OSuggestionInfo("a"), false)
                            }
                        }
                    }
                    synchronized(this) {
                        listeners.forEach {
                            it.onSuggestionFix(reqId, 0, OSuggestionInfo("b"), true)
                        }
                    }
                }
            } else {
                GlobalScope.launch {

                    (0..10).forEach { n ->
                        delay(10)
                        synchronized(this) {
                            listeners.forEach {
                                it.onSearchFix(reqId, 0, OSearchInfo(OPointInfo(OGeoPoint(Date().time.toInt(), Date().time.toInt()), "a", "b", "c")), false)
                            }
                        }
                    }
                    synchronized(this) {
                        listeners.forEach {
                            it.onSearchFix(reqId, 0, OSearchInfo(OPointInfo(OGeoPoint(Date().time.toInt(), Date().time.toInt()), "1", "2", "3")), true)
                        }
                    }
                }
            }
        }
        return reqId
    }

    fun close(nnvHandle: ONnvHandle, reqId: Int) {
        GlobalScope.launch {
            delay(100)
            synchronized(this) {
                listeners.forEach {
                    it.onCloseFix(reqId, 0)
                }
            }
        }
    }

}