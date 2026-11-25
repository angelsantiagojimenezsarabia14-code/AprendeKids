package com.angel.aprendekids.di

import android.content.Context
import com.angel.aprendekids.data.local.AprendeKidsDatabase
import com.angel.aprendekids.data.repository.AprendeKidsRepositoryImpl
import com.angel.aprendekids.domain.repository.AprendeKidsRepository

class AppContainer(context: Context) {

    private val database: AprendeKidsDatabase by lazy {
        AprendeKidsDatabase.getDatabase(context)
    }

    val repository: AprendeKidsRepository by lazy {
        AprendeKidsRepositoryImpl(database.aprendeKidsDao())
    }
}