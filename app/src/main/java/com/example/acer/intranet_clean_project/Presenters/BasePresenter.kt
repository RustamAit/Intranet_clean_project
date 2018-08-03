package com.example.acer.intranet_clean_project.Presenters

interface BasePresenter {
    fun onCreate()
    fun onDestroy()
    fun showToast(s: String)
}