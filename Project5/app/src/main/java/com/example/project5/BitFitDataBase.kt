package com.example.project5

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BitFitEntity::class], version = 1)
abstract class BitFitDataBase : RoomDatabase()
{
    abstract fun entryDao() : BitFitDao

    companion object {

        @Volatile
        private var INSTANCE: BitFitDataBase? = null

        fun getInstance(context: Context): BitFitDataBase =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    BitFitDataBase::class.java,
                    "sleep-db"
                ).build().also {INSTANCE = it}
            }


    }


}