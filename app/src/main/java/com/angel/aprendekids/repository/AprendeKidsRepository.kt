package com.angel.aprendekids.domain.repository

import com.angel.aprendekids.domain.model.Estudiante
import com.angel.aprendekids.domain.model.Tutor
import kotlinx.coroutines.flow.Flow

interface AprendeKidsRepository {
    suspend fun guardarTutor(tutor: Tutor): Int
    suspend fun guardarEstudiante(estudiante: Estudiante)
    fun observarEstudianteActual(): Flow<Estudiante?>
    suspend fun actualizarEstudiante(estudiante: Estudiante)
    suspend fun eliminarEstudiante(estudiante: Estudiante)
}