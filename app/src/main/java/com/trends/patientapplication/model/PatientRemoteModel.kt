package com.trends.patientapplication.model


import com.google.gson.annotations.SerializedName

data class PatientRemoteModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("birthdate")
    val birthdate: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("mobile")
    val mobile: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("tests")
    val tests: List<Test>,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("__v")
    val v: Int
)