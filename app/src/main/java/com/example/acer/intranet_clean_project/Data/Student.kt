package com.example.acer.intranet_clean_project.Data

class Student(id: Int?, name: String,var gpa: Double): User(id,name) {
    override fun toString(): String {
        return super.toString()+ "Student(gpa=$gpa)"
    }

}