package com.trends.patientapplication.repository

import com.trends.patientapplication.domain.model.PatientRemoteModel

interface PatientRepository {
    suspend fun getPatients(): List<PatientRemoteModel>
}