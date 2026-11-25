package com.angel.aprendekids.domain.model

data class Tutor(
    val idTutor: Int = 0,
    val retoMatematico: String
)

data class Estudiante(
    val idEstudiante: Int = 0,
    val idTutor: Int,
    val nombre: String,
    val apellido: String,
    val edad: String,
    val gradoEscolar: String,
    val avatar: String,
    val puntos: Int = 0,
    val rachaDias: Int = 1
)