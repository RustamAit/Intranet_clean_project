package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

import com.example.acer.intranet_clean_project.Data.Subject

interface TeacherFBDModel: BaseStudentTeacherFBDModel {
    fun createCourse(email: String, c: Subject.Course)
    fun markStudent(studentMark: Subject.StudentMark)
    fun findCourseStudents(courseId: String)
    fun findTeachersCourse(email:String)

}