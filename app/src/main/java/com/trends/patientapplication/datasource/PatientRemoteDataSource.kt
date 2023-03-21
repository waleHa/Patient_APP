package com.trends.patientapplication.datasource

import com.trends.patientapplication.domain.model.WrappedPatientRemoteModel
import retrofit2.http.GET

interface PatientRemoteDataSource {
    @GET("patients")
    suspend fun getPatients(): WrappedPatientRemoteModel
}