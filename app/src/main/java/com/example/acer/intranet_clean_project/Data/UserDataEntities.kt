package com.example.acer.intranet_clean_project.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

object UserDataEntities {
    @Entity(tableName = "Students") class StudentEntity(@PrimaryKey var id: String, var name: String, var gpa: Double){
        override fun toString(): String{
            return "$id   $name $gpa"
        }
    }
    @Entity(tableName = "Teachers") class TeacherEntity(@PrimaryKey var id: String, var name: String, var salary: Int, var course: String){}


    class UserRole(var email: String,var role: String)
}
