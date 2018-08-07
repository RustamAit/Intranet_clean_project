package com.example.acer.intranet_clean_project.Data

interface OnTeacherFramgentInteractionListener {
    fun showToast(s: String)
    fun startCourseCreateActivity()
    fun getCourseId(): String?
    fun markStudent(sEmail:String)
    fun showCourseStudents(cId: String)


}