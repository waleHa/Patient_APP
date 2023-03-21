package com.trends.patientapplication.repository

import com.trends.patientapplication.datasource.PatientRemoteDataSource
import com.trends.patientapplication.domain.model.PatientRemoteModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepositoryImp @Inject constructor(private val patientRemoteDataSource: PatientRemoteDataSource) :
    PatientRepository {
    override suspend fun getPatients(): List<PatientRemoteModel> =
        patientRemoteDataSource.getPatients().patientRemoteModel

}