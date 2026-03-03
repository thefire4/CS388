package com.example.project5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_entry_table")
data class BitFitEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo("date") val date: String,
    @ColumnInfo("hours_slept") val hoursSlept: Float,
    @ColumnInfo("notes") val notes: String?
)