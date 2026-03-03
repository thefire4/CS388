package com.example.project5

import android.app.Application

class BitFitApplication : Application() {
    val db by lazy { BitFitDataBase.getInstance(this) }
}