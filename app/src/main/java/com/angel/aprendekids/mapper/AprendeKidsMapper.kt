package com.angel.aprendekids.data.mapper

import com.angel.aprendekids.data.local.EstudianteEntity
import com.angel.aprendekids.data.local.TutorEntity
import com.angel.aprendekids.domain.model.Estudiante
import com.angel.aprendekids.domain.model.Tutor

fun TutorEntity.toDomain(): Tutor {
    return Tutor(
        idTutor = idTutor,
        retoMatematico = retoMatematico
    )
}

fun EstudianteEntity.toDomain(): Estudiante {
    return Estudiante(
        idEstudiante = idEstudiante,
        idTutor = idTutor,
        nombre = nombre,
        apellido = apellido,
        edad = edad,
        gradoEscolar = gradoEscolar,
        avatar = avatar,
        puntos = puntos,
        rachaDias = rachaDias
    )
}
fun Tutor.toEntity(): TutorEntity {
    return TutorEntity(
        idTutor = idTutor,
        retoMatematico = retoMatematico
    )
}

fun Estudiante.toEntity(): EstudianteEntity {
    return EstudianteEntity(
        idEstudiante = idEstudiante,
        idTutor = idTutor,
        nombre = nombre,
        apellido = apellido,
        edad = edad,
        gradoEscolar = gradoEscolar,
        avatar = avatar,
        puntos = puntos,
        rachaDias = rachaDias
    )
}