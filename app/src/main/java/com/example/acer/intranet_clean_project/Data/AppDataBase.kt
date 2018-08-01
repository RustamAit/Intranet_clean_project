package com.example.acer.intranet_clean_project.Data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(UserDataEntities.StudentEntity::class, UserDataEntities.TeacherEntity::class), version = 2) abstract class AppDatabase: RoomDatabase() {
    abstract fun studentDao(): Dao.StudentDao
    abstract fun teacherDao(): Dao.TeacherDao
}