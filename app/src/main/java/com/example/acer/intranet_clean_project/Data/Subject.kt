package com.example.acer.intranet_clean_project.Data

import java.sql.Time

object Subject {
    class Mark (var id: String?,var letter: String, var points: Int){
        override fun toString(): String {
            return "Mark(id=$id, letter='$letter', points=$points)"
        }
    }
    class StudentMark(var markId: String,var studentEmail: String,var courseId: String){
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as StudentMark

            if (studentEmail != other.studentEmail || courseId != other.courseId) return false

            return true
        }


    }
    class Course(var id: String?, var title: String,var description: String, var numberOfCredits: Int){
        override fun toString(): String {
            return "Course(id=$id, title='$title', description='$description', numberOfCredits=$numberOfCredits)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Course

            if (title != other.title) return false

            return true
        }



    }
    class TeachersCourse(var courseId: String, var teacherEmail: String){}
    class CourseStudents(var studentEmail: String,var courseId: String){}



}