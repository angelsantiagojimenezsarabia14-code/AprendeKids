package com.angel.aprendekids.data.repository

import com.angel.aprendekids.data.local.AprendeKidsDao
import com.angel.aprendekids.data.mapper.toDomain
import com.angel.aprendekids.data.mapper.toEntity
import com.angel.aprendekids.domain.model.Estudiante
import com.angel.aprendekids.domain.model.Tutor
import com.angel.aprendekids.domain.repository.AprendeKidsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AprendeKidsRepositoryImpl(
    private val dao: AprendeKidsDao
) : AprendeKidsRepository {

    override suspend fun guardarTutor(tutor: Tutor): Int {
        return dao.insertarTutor(tutor.toEntity()).toInt()
    }

    override suspend fun guardarEstudiante(estudiante: Estudiante) {
        dao.insertarEstudiante(estudiante.toEntity())
    }

    override fun observarEstudianteActual(): Flow<Estudiante?> {
        return dao.observarEstudianteActual().map { entity ->
            entity?.toDomain()
        }
    }

    override suspend fun actualizarEstudiante(estudiante: Estudiante) {
        dao.actualizarEstudiante(estudiante.toEntity())
    }

    override suspend fun eliminarEstudiante(estudiante: Estudiante) {
        dao.eliminarEstudiante(estudiante.toEntity())
    }
}