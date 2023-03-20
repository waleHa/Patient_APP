package com.trends.patientapplication.model


import com.google.gson.annotations.SerializedName

data class WrappedPatientRemoteModel(
    @SerializedName("data")
    val patientRemoteModel: List<PatientRemoteModel>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)