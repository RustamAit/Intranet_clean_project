package com.example.acer.intranet_clean_project.Adapters

import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Subject

interface OnTeacherItemClicked: OnItemClicked {
    fun showStudents(cId: String)
    fun markStudent(sEmail:String)
}