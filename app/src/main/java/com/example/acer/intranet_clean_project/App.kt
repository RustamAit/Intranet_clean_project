package com.example.acer.intranet_clean_project

import android.app.Application
import android.arch.persistence.room.Room
import android.util.Log
import com.example.acer.intranet_clean_project.Data.AppDatabase
import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class App: Application() {
    companion object {
        var database: AppDatabase? = null
        var mAuth: FirebaseAuth? = null
        var db: FirebaseDatabase? = null
        var studentChildRef: DatabaseReference? = null
        var teacherChildRef: DatabaseReference? = null
        var adminChildRef: DatabaseReference? = null
        var courseChildRef: DatabaseReference? = null
        var roleChildRef: DatabaseReference? = null
        var teachersCoursesref: DatabaseReference? = null
        var markStudentRef: DatabaseReference? = null
        var courseStudentsRef: DatabaseReference? = null
        var role: String = "admin"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Application","On Create Method")
        database =  Room.databaseBuilder(this, AppDatabase::class.java, "new-db").fallbackToDestructiveMigration().build()
        db = FirebaseDatabase.getInstance()
        studentChildRef =  db?.getReference()?.child("students")
        teacherChildRef =  db?.getReference()?.child("teachers")
        adminChildRef = db?.getReference()?.child("admins")
        courseChildRef = db?.getReference()?.child("courses")
        roleChildRef = db?.getReference()?.child("roles")
        teachersCoursesref = db?.getReference()?.child("teachersCourses")
        markStudentRef = db?.getReference()?.child("studentsMarks")
        courseStudentsRef = db?.getReference()?.child("courseStudents")
        mAuth = FirebaseAuth.getInstance()

    }

}