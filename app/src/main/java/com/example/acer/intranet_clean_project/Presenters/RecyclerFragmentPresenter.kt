package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Models.BaseUserFBDModel
import com.example.acer.intranet_clean_project.Models.BaseUserFBDModelImp
import com.example.acer.intranet_clean_project.Views.BaseFragmentView

class RecyclerFragmentPresenter(var view: BaseFragmentView): BaseFragmentPresenter {
    var userFBDModel: BaseUserFBDModel = BaseUserFBDModelImp(this)

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
    fun getAdmins(){
        userFBDModel.getAdminsFBD()
    }
    fun checkUserRole(){
        if(App.role == "admin"){
            Log.d("LOGIN_PRESENTER"," STUDENT MODE")
            view.getPermission()
        }
        else if(App.role == "teacher"){
            view.showToast("Teacher Mode")
        }
        else{
            view.showToast("Student Mode")
            Log.d("LOGIN_PRESENTER"," STUDENT MODE")
        }

    }
}