package com.example.acer.intranet_clean_project.Presenters

interface BaseFragmentPresenter{
    fun onCreate()
    fun onDestroy()
    fun notifySetChanged(Arr: ArrayList<Any>)
}