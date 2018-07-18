package com.example.acer.intranet_clean_project.Data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

object UserDataEntities {
    @Entity(tableName = "Students") class StudentEntity(@PrimaryKey(autoGenerate = true) var id: Int?, var name: String, var gpa: Double){
        override fun toString(): String{
            return "$id   $name $gpa"
        }
    }
    @Entity(tableName = "Teachers") class TeacherEntity(@PrimaryKey(autoGenerate = true) var id: Int?, var name: String, var salary: Int, var course: String){}

}
