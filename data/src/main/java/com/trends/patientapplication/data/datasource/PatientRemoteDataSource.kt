package com.trends.patientapplication.data.datasource

import com.trends.patientapplication.domain.model.add.AddPatientRemoteModel
import com.trends.patientapplication.domain.model.add.BodyAddPatientRemoteModel
import com.trends.patientapplication.domain.model.patient.WrappedPatientRemoteModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PatientRemoteDataSource {
    @GET("patients")
    suspend fun getPatients(): WrappedPatientRemoteModel

    @POST("patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientRemoteModel):AddPatientRemoteModel
}