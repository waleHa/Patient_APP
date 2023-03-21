package com.trends.patientapplication.presentation.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.trends.patientapplication.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}