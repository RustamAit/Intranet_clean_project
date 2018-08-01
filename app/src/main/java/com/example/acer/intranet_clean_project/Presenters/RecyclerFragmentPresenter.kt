package com.example.acer.intranet_clean_project.Presenters

import com.example.acer.intranet_clean_project.Models.UserFBDModel
import com.example.acer.intranet_clean_project.Views.BaseFragmentView

class RecyclerFragmentPresenter(var view: BaseFragmentView): BaseFragmentPresenter {
    var userFBDModel: UserFBDModel = UserFBDModel(this)

    override fun onCreate() {

    }

    override fun onDestroy() {
    }

    override fun notifySetChanged(arr: ArrayList<Any>) {
        view.changeProgressBarVisability()
        view.setAdapter(arr)
    }
    fun getStudents(){
        userFBDModel.getStudentsFBD()

    }
    fun getTeachers(){
        userFBDModel.getTeacherFBD()
    }
    fun getAllUsers(){
        userFBDModel.getAllFBDUsers()
    }
}