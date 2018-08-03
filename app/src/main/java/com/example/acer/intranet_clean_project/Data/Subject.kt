package com.example.acer.intranet_clean_project.Data

import java.sql.Time

object Subject {
    class Mark (var letter: Char, var points: Int)
    class Course(var id: String?, var title: String,var description: String, var numberOfCredits: Int){
        override fun toString(): String {
            return "Course(id=$id, title='$title', description='$description', numberOfCredits=$numberOfCredits)"
        }
    }
    class TeachersCourse(var courseId: String, var teacherEmail: String){}
    class studentCourse(var studentEmail: String,var course: String){}



}