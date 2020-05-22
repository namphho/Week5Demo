package com.hnam.week5demo.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hnam.week5demo.DATABASE_NAME


@Database(entities = [Account::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDAO(): AccountDAO

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries() //don't use this line in product. it just for demo
            .build()
    }
}