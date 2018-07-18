package com.example.acer.intranet_clean_project.Data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

object Dao {
    @Dao
    interface StudentDao {
        @Query("SELECT*FROM Students")
        fun getAllStudents(): Flowable<List<UserDataEntities.StudentEntity>>
        @Insert
        fun insertStudent(sE: UserDataEntities.StudentEntity)
    }
    @Dao
    interface TeacherDao{
        @Query("SELECT*FROM Teachers")
        fun getAllTeachers(): Flowable<List<UserDataEntities.TeacherEntity>>
        @Insert
        fun insertTeacher(tE: UserDataEntities.TeacherEntity)
    }
}