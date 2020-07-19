package com.example.nnvlib.model

import com.example.otherlib.data.OGeoPoint

class GeoPoint(val oGeoPoint: OGeoPoint) {

    val la:Int
        get() = oGeoPoint.la

    val lo:Int
        get() = oGeoPoint.lo

    override fun toString(): String {
        return "$la - $lo"
    }

}