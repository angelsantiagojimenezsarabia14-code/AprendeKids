package com.angel.aprendekids.data.local
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Tabla 1: Tutor
@Entity(tableName = "TUTOR")
data class TutorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_tutor")
    val idTutor: Int = 0,

    @ColumnInfo(name = "reto_matematico")
    val retoMatematico: String
)

// Tabla 2: Estudiante
@Entity(
    tableName = "ESTUDIANTE",
    foreignKeys = [
        ForeignKey(
            entity = TutorEntity::class,
            parentColumns = ["id_tutor"],
            childColumns = ["id_tutor"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("id_tutor")]
)
data class EstudianteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_estudiante")
    val idEstudiante: Int = 0,

    @ColumnInfo(name = "id_tutor")
    val idTutor: Int,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "apellido")
    val apellido: String,

    @ColumnInfo(name = "edad")
    val edad: String,

    @ColumnInfo(name = "grado_escolar")
    val gradoEscolar: String,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "puntos")
    val puntos: Int = 0,

    @ColumnInfo(name = "racha_dias")
    val rachaDias: Int = 1
)