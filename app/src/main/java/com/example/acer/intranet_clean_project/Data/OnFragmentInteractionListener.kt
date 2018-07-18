package com.example.acer.intranet_clean_project.Data

interface OnFragmentInteractionListener {
    fun getData(): ArrayList<Any>
    fun getStudents(): ArrayList<Any>
    fun getTeachers(): ArrayList<Any>
    fun startStudentCreateActivity()
    fun startTeacherCreateAvtivity()
}