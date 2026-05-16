package com.ufscar.devmovel.plannerdisciplina.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ufscar.devmovel.plannerdisciplina.data.dao.DisciplinaDao
import com.ufscar.devmovel.plannerdisciplina.model.Disciplina

@Database(entities = [Disciplina::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun disciplinaDao(): DisciplinaDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}