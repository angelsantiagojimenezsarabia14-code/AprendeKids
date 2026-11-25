package com.angel.aprendekids.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class TutorConEstudiantes(
    @Embedded val tutor: TutorEntity,
    @Relation(
        parentColumn = "id_tutor",
        entityColumn = "id_tutor"
    )
    val estudiantes: List<EstudianteEntity>
)