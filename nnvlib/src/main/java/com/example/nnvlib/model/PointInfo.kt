package com.example.nnvlib.model

import com.example.otherlib.data.OPointInfo

class PointInfo(private val point: OPointInfo) {

    val geoPoint: GeoPoint
        get() = GeoPoint(point.geoPoint)

    val name: String
        get() = point.name

    val address: String
        get() = point.address

    val details: String
        get() = point.details

}