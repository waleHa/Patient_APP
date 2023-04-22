package com.trends.patientapplication.data.repository.patient

import com.trends.patientapplication.data.datasource.PatientRemoteDataSource
import com.trends.patientapplication.domain.model.add.AddPatientResponse
import com.trends.patientapplication.domain.model.add.AddPatientRequest
import com.trends.patientapplication.domain.model.delete.DeletePatientResponse
import com.trends.patientapplication.domain.model.patient.PatientResponse
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepositoryImp @Inject constructor(private val patientRemoteDataSource: PatientRemoteDataSource) :
    PatientRepository {
    override suspend fun getPatients(): List<PatientResponse> =
        patientRemoteDataSource.getPatients().data

    override suspend fun addPatients(bodyAddPatientModel: AddPatientRequest): AddPatientResponse =
        patientRemoteDataSource.addPatient(bodyAddPatientModel)

    override suspend fun deletePatient(id: String): DeletePatientResponse =
        patientRemoteDataSource.deletePatient(id)

    override suspend fun getPatientById(id: String): PatientResponse =
        patientRemoteDataSource.getPatient(id).data


}


