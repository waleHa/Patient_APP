package com.trends.patientapplication.repository

import com.trends.patientapplication.datasource.PatientRemoteDataSource
import com.trends.patientapplication.model.WrappedPatientRemoteModel
import javax.inject.Inject

class PatientRepositoryImp @Inject constructor(private val patientRemoteDataSource: PatientRemoteDataSource) :
    PatientRepository {
    override suspend fun getPatients(): WrappedPatientRemoteModel =
        patientRemoteDataSource.getPatients()

}