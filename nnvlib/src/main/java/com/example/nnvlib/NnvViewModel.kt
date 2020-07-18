package com.example.nnvlib

import android.view.SurfaceHolder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nnvlib.model.MapHandle
import com.example.nnvlib.model.NnvHandle
import com.example.nnvlib.repository.MapRepository
import com.example.nnvlib.util.Event

class NnvViewModel : ViewModel() {

    // NnvHandle
    private val _nnvHandle = MutableLiveData<Event<NnvHandle>>()
    val nnvHandle : LiveData<Event<NnvHandle>>
        get() = _nnvHandle


    // MapHandle
    private val _mapHandle = MutableLiveData<Event<MapHandle>>()
    val mapHandle : LiveData<Event<MapHandle>>
        get() = _mapHandle


    fun updateNnvHandle(handle: NnvHandle?) {
        _nnvHandle.postValue(handle?.let{ Event(it) })
    }

    fun updateMapHandle(handle: MapHandle?) {
        _mapHandle.postValue(handle?.let{ Event(it) })
    }

    fun initializeMapHandle(surface:SurfaceHolder, w:Int, h: Int) {
        val m = MapRepository(this, surface, w, h)
        //m.
    }
}