package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

import com.example.acer.intranet_clean_project.Data.Subject

interface StudentFBDModel: BaseStudentTeacherFBDModel {
    fun asignTocourse(email: String,courseId: String)
    fun getAllCourses()
    fun getStudentsCourses(email: String)
    fun getMarks(email:String)
}