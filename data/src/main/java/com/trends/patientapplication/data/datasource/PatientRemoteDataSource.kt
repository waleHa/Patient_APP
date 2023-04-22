package com.trends.patientapplication.data.datasource

import com.trends.patientapplication.domain.model.BaseWrapperRemoteModel
import com.trends.patientapplication.domain.model.add.AddPatientRemoteModel
import com.trends.patientapplication.domain.model.add.BodyAddPatientRemoteModel
import com.trends.patientapplication.domain.model.delete.DeletePatientResponseModel
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientRemoteDataSource {
    @GET("patients")
    suspend fun getPatients(): BaseWrapperRemoteModel<List<PatientRemoteModel>> //WrappedPatientRemoteModel

    @POST("patients")
    suspend fun addPatient(@Body bodyAddPatientModel: BodyAddPatientRemoteModel): AddPatientRemoteModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): DeletePatientResponseModel

    @GET("patients/{id}")
    suspend fun getPatientById(@Path("id") id: String): BaseWrapperRemoteModel<PatientRemoteModel>
}