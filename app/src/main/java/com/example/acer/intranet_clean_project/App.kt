package com.example.acer.intranet_clean_project

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.example.acer.intranet_clean_project.Data.AppDatabase
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher

class App: Application() {
    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Application","On Create Method")
        database =  Room.databaseBuilder(this, AppDatabase::class.java, "new-db").fallbackToDestructiveMigration().build()
    }
}