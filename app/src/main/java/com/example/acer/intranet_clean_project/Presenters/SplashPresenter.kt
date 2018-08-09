package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.App.Companion.mAuth
import com.example.acer.intranet_clean_project.Models.UserLoginModelFBD
import com.example.acer.intranet_clean_project.Models.UserLoginModelFBDImp
import com.example.acer.intranet_clean_project.Views.SplashView

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
//            mAuth?.signOut()
            checkRole(mFirebaseUser.email.toString())
        }
    }
    fun checkRole(email: String){
        userLoginModelFBD.checkRole(email)
        Log.d("LOGIN_TEST","$email")
    }
    override fun notifyUserFound() {
        when(App.role){
            "admin" -> listener.startMainActivity()
            "student" -> listener.startStudentActivity()
            "teacher"->listener.startTeacherActivity()
        }

    }
}