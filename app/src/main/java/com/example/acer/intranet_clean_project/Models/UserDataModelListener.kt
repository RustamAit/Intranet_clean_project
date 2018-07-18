package com.example.acer.intranet_clean_project.Models

import com.example.acer.intranet_clean_project.Data.UserDataEntities
import io.reactivex.Flowable

interface UserDataModelListener {
    fun addStudent(se: UserDataEntities.StudentEntity)
    fun addTeacher(te: UserDataEntities.TeacherEntity)
    fun getData(): Flowable<ArrayList<Any>>
}