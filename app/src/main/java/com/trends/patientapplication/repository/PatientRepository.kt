package com.trends.patientapplication.repository

import com.trends.patientapplication.model.PatientRemoteModel
import com.trends.patientapplication.model.WrappedPatientRemoteModel

interface PatientRepository {
    suspend fun getPatients(): List<PatientRemoteModel>
}