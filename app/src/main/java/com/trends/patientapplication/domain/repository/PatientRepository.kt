package com.trends.patientapplication.domain.repository

import com.trends.patientapplication.domain.model.patient.PatientRemoteModel

interface PatientRepository {
    suspend fun getPatients(): List<PatientRemoteModel>
}