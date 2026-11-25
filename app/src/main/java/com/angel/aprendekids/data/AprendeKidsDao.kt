package com.angel.aprendekids.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AprendeKidsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTutor(tutor: TutorEntity): Long
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEstudiante(estudiante: EstudianteEntity): Long
    @Query("SELECT * FROM ESTUDIANTE ORDER BY id_estudiante DESC LIMIT 1")
    fun observarEstudianteActual(): Flow<EstudianteEntity?>
    @Transaction
    @Query("SELECT * FROM TUTOR WHERE id_tutor = :idTutor")
    fun observarTutorConEstudiantes(idTutor: Int): Flow<TutorConEstudiantes?>
    @Update
    suspend fun actualizarEstudiante(estudiante: EstudianteEntity)
    @Delete
    suspend fun eliminarEstudiante(estudiante: EstudianteEntity)
}