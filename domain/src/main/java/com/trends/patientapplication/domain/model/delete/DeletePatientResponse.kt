package com.trends.patientapplication.domain.model.delete

import com.google.gson.annotations.SerializedName

data class DeletePatientResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)
