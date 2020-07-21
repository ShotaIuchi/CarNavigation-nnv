package com.example.nnvlib.model

interface IMapPointInfo {

    fun getPointInfo(): PointInfo

}

class IMapPointInfoMemory(val data: MemoryInfo): IMapPointInfo {

    override fun getPointInfo(): PointInfo = PointInfo(data.memoryInfo.point)

}

class IMapPointInfoPoint(val data: PointInfo): IMapPointInfo {

    override fun getPointInfo(): PointInfo = data

}

class IMapPointInfoSearch(val data: SearchInfo): IMapPointInfo {

    override fun getPointInfo(): PointInfo = PointInfo(data.searchInfo.point)

}
