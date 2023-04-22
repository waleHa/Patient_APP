package com.trends.patientapplication.domain.model

import com.google.gson.annotations.SerializedName

data class BaseWrapperResponse<T>(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String,
    @SerializedName("data")
    val data: T
)