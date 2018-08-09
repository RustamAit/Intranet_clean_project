package com.example.acer.intranet_clean_project.Presenters

import com.example.acer.intranet_clean_project.Data.Subject

interface BaseFragmentPresenter: BasePresenter{

    fun notifySetChanged(Arr: ArrayList<Any>)
    fun calculateGpa(arr: ArrayList<Any>)
}