package com.trends.patientapplication.domain.repository.patient

import com.trends.patientapplication.domain.model.patient.PatientRemoteModel

interface PatientRepository {
    suspend fun getPatients(): List<PatientRemoteModel>
}