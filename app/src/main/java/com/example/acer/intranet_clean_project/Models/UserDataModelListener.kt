package com.example.acer.intranet_clean_project.Models

import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.UserDataEntities
import io.reactivex.Flowable

interface UserDataModelListener {
    fun addStudent(se: UserDataEntities.StudentEntity)
    fun addTeacher(te: UserDataEntities.TeacherEntity)
    fun addStudentFBD(s: Student)
    fun addTeacherFBD(t: Teacher)
    fun getData(): Flowable<ArrayList<Any>>
}