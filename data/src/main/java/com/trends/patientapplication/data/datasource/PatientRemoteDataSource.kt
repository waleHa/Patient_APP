package com.trends.patientapplication.data.datasource

import com.trends.patientapplication.domain.model.BaseWrapperResponse
import com.trends.patientapplication.domain.model.add.AddPatientResponse
import com.trends.patientapplication.domain.model.add.AddPatientRequest
import com.trends.patientapplication.domain.model.delete.DeletePatientResponse
import com.trends.patientapplication.domain.model.patient.PatientResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientRemoteDataSource {
    @GET("patients")
    suspend fun getPatients(): BaseWrapperResponse<List<PatientResponse>> //WrappedPatientRemoteModel

    @POST("patients")
    suspend fun addPatient(@Body addPatientRequest: AddPatientRequest): AddPatientResponse

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): DeletePatientResponse

    @GET("patients/{id}")
    suspend fun getPatient(@Path("id") id: String): BaseWrapperResponse<PatientResponse>
}