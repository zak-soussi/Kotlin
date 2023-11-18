package com.example.tp4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tp4.database.entities.Schedule

@Database(entities = [Schedule::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scheduleDao(): ScheduleDao

    companion object {
        private const val DATABASE_NAME = "bus_schedule.db"

        // Volatile ensures that the instance is always up-to-date
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if(INSTANCE != null)
                return INSTANCE!!

            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .createFromAsset("database/bus_schedule.db")
                .build()

            INSTANCE = db

            return db

        }
    }
}