package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

interface BaseStudentTeacherFBDModel {
    fun findStudent(email:String)
    fun findTeacher(email:String)
    fun findTeachersCourse(email:String)
    fun findStudentCours(email:String)
}