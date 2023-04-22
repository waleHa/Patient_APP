package com.trends.patientapplication.domain.model.details

import com.trends.patientapplication.domain.model.patient.PatientRemoteModel

class DetailsPatientWrapperRemoteModel(
    val status: Int,
    val message: String,
    val data: PatientRemoteModel
)