package com.example.nnvlib

import android.provider.ContactsContract
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nnvlib.model.*
import com.example.nnvlib.repository.Geo2PointInfoRepository
import com.example.nnvlib.repository.MapRepository
import com.example.nnvlib.repository.NnvRepository
import com.example.nnvlib.repository.SearchRepository
import com.example.nnvlib.util.Event
import com.example.otherlib.SearchManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NnvViewModel : ViewModel() {

    // NnvHandle
    private val _nnvHandle = MutableLiveData<Event<NnvHandle>>()
    val nnvHandle : LiveData<Event<NnvHandle>>
        get() = _nnvHandle

    fun nnvInitialize() {
        NnvRepository(this).initialize()
    }

    fun updateNnvHandle(handle: NnvHandle?) {
        _nnvHandle.postValue(handle?.let{ Event(it) })
    }



    // MapHandle
    private val _mapHandle = MutableLiveData<Event<MapHandle>>()
    val mapHandle : LiveData<Event<MapHandle>>
        get() = _mapHandle

    fun mapInitialize(surface:SurfaceHolder, w:Int, h: Int) {
        _nnvHandle.value?.peekContent()?.let {
            MapRepository(this, it.handle, surface, w, h).initialize()
        }
    }

    fun updateMapHandle(handle: MapHandle?) {
        _mapHandle.postValue(handle?.let{ Event(it) })
    }


    //
    private var searchJob : Job? = null

    private val _searchSuggestion = MutableLiveData<Event<List<SuggestionInfo>>>()
    val searchSuggestion : LiveData<Event<List<SuggestionInfo>>>
        get() = _searchSuggestion

    fun updateSearchSuggestion(suggestions: List<SuggestionInfo>) {
        _searchSuggestion.postValue(Event(suggestions))
    }


    fun search() {
        GlobalScope.launch {
            searchJob?.cancel()
            _nnvHandle.value?.peekContent()?.let {
                val s = SearchRepository(this@NnvViewModel, it.handle)
                s.close()
                searchJob = SearchRepository(this@NnvViewModel, it.handle).searchSuggestion("hoge")
            }
        }
    }







    // RouteInfo
    private val _routeInfo = MutableLiveData<Event<RouteInfo>>()
    val routeInfo : LiveData<Event<RouteInfo>>
        get() = _routeInfo

    fun updateRouteInfo(handle: RouteInfo?) {
        _routeInfo.postValue(handle?.let{ Event(it) })
    }

    // LogTap
    private val _longTapPoint = MutableLiveData<Event<PointInfo>>()
    val longTapPoint : LiveData<Event<PointInfo>>
        get() = _longTapPoint

    fun onTouch(geoPoint: GeoPoint) {
        _nnvHandle.value?.peekContent()?.let {
            Geo2PointInfoRepository(this, it.handle).search(geoPoint)
        }
    }

    fun updateLongTapPoint(handle: PointInfo?) {
        _longTapPoint.postValue(handle?.let{ Event(it) })
    }

}