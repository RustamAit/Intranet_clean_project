package com.example.acer.intranet_clean_project.Models

interface UserLoginModelFBD {
    fun foundAdmin(email: String)
    fun foundStudent(email: String)
    fun foundTeacher(email: String)
    fun checkRole(email: String)

}