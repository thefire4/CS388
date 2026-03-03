package com.example.project5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BitFitDao
{
   @Query("SELECT * FROM sleep_entry_table ORDER BY date DESC")
   fun getALL(): Flow<List<BitFitEntity>>

   @Insert
   fun insert(entry: BitFitEntity)

}