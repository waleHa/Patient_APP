package com.trends.patientapplication.data.repository.patient

import com.trends.patientapplication.data.datasource.PatientRemoteDataSource
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepositoryImp @Inject constructor(private val patientRemoteDataSource: PatientRemoteDataSource) :
    PatientRepository {
    override suspend fun getPatients(): List<PatientRemoteModel> =
        patientRemoteDataSource.getPatients().patientRemoteModel

}