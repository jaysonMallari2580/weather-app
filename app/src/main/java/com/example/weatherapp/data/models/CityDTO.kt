package com.example.weatherapp.data.models

import com.squareup.moshi.Json

data class CityDTO (

    @Json(name="name")
    val name:String?,

    @Json(name="coord")
    val coord: Coord,

        )