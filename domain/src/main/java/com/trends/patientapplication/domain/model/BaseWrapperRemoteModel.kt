package com.trends.patientapplication.domain.model

data class BaseWrapperRemoteModel<T>(
    val status:Int,
    val message:String,
    val data: T
)