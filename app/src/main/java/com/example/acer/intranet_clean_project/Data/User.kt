package com.example.acer.intranet_clean_project.Data


open class User( var id: String?, var name: String) {


    override fun toString(): String {
        return "this is ${name} he's age is ${id}"
    }
}