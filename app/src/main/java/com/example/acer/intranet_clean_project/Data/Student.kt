package com.example.acer.intranet_clean_project.Data

import java.util.HashMap

class Student(id: String?, name: String,surname: String,email: String,password: String,keyId: String,var yearOfStudy: Int): User(id,name,surname,email,password,keyId) {
    override fun toString(): String {
        return super.toString()+ "Student(yearOfStudy=$yearOfStudy)"
    }
    fun toMap(): MutableMap<String, Any?>{
        var result: MutableMap<String,Any?> = HashMap()
        result.put("id",id)
        result.put("name",name)
        result.put("yearOfStudy",yearOfStudy)
        return result
    }
}