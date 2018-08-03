package com.example.acer.intranet_clean_project.Models

import com.example.acer.intranet_clean_project.Data.Admin
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.User

interface UserCreateModelFBD {
    fun addStudentFBD(s: Student)
    fun addTeacherFBD(t: Teacher)
    fun addAdminFBD(a: Admin)
    fun addUser(u: User)

}