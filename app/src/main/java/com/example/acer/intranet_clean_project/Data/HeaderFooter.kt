package com.example.acer.intranet_clean_project.Data

object HeaderFooter {
    class Header(private var type: Int){
        fun getType(): Int{
            return type
        }
    }
    class Footer()
}