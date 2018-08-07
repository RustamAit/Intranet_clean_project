package com.example.acer.intranet_clean_project.views

interface BaseFragmentView: BaseView {
    fun setAdapter(arr: ArrayList<Any>)
    fun changeProgressBarVisability()
    fun getPermission()
}