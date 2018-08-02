package com.example.acer.intranet_clean_project.Views

interface BaseFragmentView {
    fun setAdapter(arr: ArrayList<Any>)
    fun changeProgressBarVisability()
    fun getPermission()
    fun showToast(s: String)
}