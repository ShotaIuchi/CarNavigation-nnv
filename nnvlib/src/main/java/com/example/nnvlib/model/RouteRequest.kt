package com.example.nnvlib.model

class RouteRequest(
    var routeOption: RouteOption,
    var start: PointInfo,
    var end: PointInfo,
    var via: List<PointInfo>
) {
}