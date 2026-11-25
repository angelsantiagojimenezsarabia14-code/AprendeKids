package com.angel.aprendekids.data.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        TutorEntity::class,
        EstudianteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AprendeKidsDatabase : RoomDatabase() {

    abstract fun aprendeKidsDao(): AprendeKidsDao

    companion object {
        @Volatile
        private var INSTANCE: AprendeKidsDatabase? = null

        fun getDatabase(context: Context): AprendeKidsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AprendeKidsDatabase::class.java,
                    "aprendekids_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}