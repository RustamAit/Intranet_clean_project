package com.example.acer.intranet_clean_project.Adapters

interface OnStudentItemClicked: OnItemClicked {
    fun asignToCourse(cId: String)
}