package com.trends.patientapplication.domain.model


import com.google.gson.annotations.SerializedName

data class Test(
    @SerializedName("date")
    val date: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("reading")
    val reading: String,
    @SerializedName("type")
    val type: String
)