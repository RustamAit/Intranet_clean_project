package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

import com.example.acer.intranet_clean_project.Data.Subject

interface TeacherFBDModel: BaseStudentTeacherFBDModel {
    fun CreateCourse(email: String,c: Subject.Course)
    fun markStudent(email: String, m: Subject.Mark)
}