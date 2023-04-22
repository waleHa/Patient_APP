package com.trends.patientapplication.data.repository.patient

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.trends.patientapplication.data.datasource.PatientRemoteDataSource
import com.trends.patientapplication.domain.model.add.AddPatientRemoteModel
import com.trends.patientapplication.domain.model.add.BodyAddPatientRemoteModel
import com.trends.patientapplication.domain.model.delete.DeletePatientResponseModel
import com.trends.patientapplication.domain.model.patient.PatientRemoteModel
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PatientRepositoryImp @Inject constructor(private val patientRemoteDataSource: PatientRemoteDataSource) :
    PatientRepository {
    override suspend fun getPatients(): List<PatientRemoteModel> =
        patientRemoteDataSource.getPatients().data

    override suspend fun addPatients(bodyAddPatientModel: BodyAddPatientRemoteModel): AddPatientRemoteModel =
        patientRemoteDataSource.addPatient(bodyAddPatientModel)

    override suspend fun deletePatient(id: String): DeletePatientResponseModel =
        patientRemoteDataSource.deletePatient(id)

    override suspend fun getPatientById(id: String): PatientRemoteModel =
        patientRemoteDataSource.getPatientById(id).data


}


