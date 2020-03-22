package com.yashdoshi.kotlinmvvm.retrofit.models


import com.google.gson.annotations.SerializedName

data class BoredActivity(
    @SerializedName("accessibility")
    var accessibility: Double,
    @SerializedName("activity")
    var activity: String,
    @SerializedName("key")
    var key: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("participants")
    var participants: Int,
    @SerializedName("price")
    var price: Double,
    @SerializedName("type")
    var type: String
)