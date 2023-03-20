package com.trends.patientapplication.repository

import com.trends.patientapplication.datasource.PatientRemoteDataSource
import com.trends.patientapplication.model.PatientRemoteModel
import com.trends.patientapplication.model.WrappedPatientRemoteModel
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepositoryImp @Inject constructor(private val patientRemoteDataSource: PatientRemoteDataSource) :
    PatientRepository {
    override suspend fun getPatients(): List<PatientRemoteModel> =
        patientRemoteDataSource.getPatients().patientRemoteModel

}