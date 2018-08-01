package com.example.acer.intranet_clean_project.Data

import java.util.HashMap

class Student(id: String?, name: String,var course: Int): User(id,name) {
    override fun toString(): String {
        return super.toString()+ "Student(course=$course)"
    }
    fun toMap(): MutableMap<String, Any?>{
        var result: MutableMap<String,Any?> = HashMap()
        result.put("id",id)
        result.put("name",name)
        result.put("course",course)
        return result
    }

}