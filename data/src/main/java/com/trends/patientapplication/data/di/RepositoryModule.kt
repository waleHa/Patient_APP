package com.trends.patientapplication.data.di

import com.trends.patientapplication.data.datasource.PatientRemoteDataSource
import com.trends.patientapplication.data.repository.patient.PatientRepositoryImp
import com.trends.patientapplication.domain.repository.patient.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryPatient(patientRemoteDataSource: PatientRemoteDataSource): PatientRepository {
        return PatientRepositoryImp(patientRemoteDataSource)
    }
}