package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Models.UserLoginModelFBD
import com.example.acer.intranet_clean_project.Models.UserLoginModelFBDImp
import com.example.acer.intranet_clean_project.views.SplashView

class SplashPresenter(var listener: SplashView): BaseLoginPresenter {
    lateinit var userLoginModelFBD: UserLoginModelFBD


    fun checkUser(){
        userLoginModelFBD = UserLoginModelFBDImp(this)
        var mFirebaseUser = App.mAuth?.currentUser
        if(mFirebaseUser==null){
            listener.startLoginActivity()
            Log.d("LOGIN_TEST","firebase user = null")

        }
        else{
            checkRole(mFirebaseUser.email.toString())
        }
    }
    fun checkRole(email: String){
        userLoginModelFBD.checkRole(email)
    }
    override fun notifyUserFound() {
        when(App.role){
            "admin" -> listener.startMainActivity()
            "student" -> listener.startStudentActivity()
            "teacher"->listener.startTeacherActivity()
        }

    }
}