package com.example.acer.intranet_clean_project.Models

interface UserLoginModelListener {
    fun foundAdmin(email: String)
    fun foundStudent(email: String)
    fun foundTeacher(email: String)

}