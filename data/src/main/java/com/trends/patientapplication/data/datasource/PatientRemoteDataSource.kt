package com.trends.patientapplication.data.datasource

import com.trends.patientapplication.domain.model.patient.WrappedPatientRemoteModel
import retrofit2.http.GET

interface PatientRemoteDataSource {
    @GET("patients")
    suspend fun getPatients(): WrappedPatientRemoteModel
}