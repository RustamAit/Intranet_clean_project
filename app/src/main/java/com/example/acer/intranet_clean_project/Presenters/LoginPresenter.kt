package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Models.UserLoginModelFBD
import com.example.acer.intranet_clean_project.Models.UserLoginModelFBDImp
import com.example.acer.intranet_clean_project.Views.LoginView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task


///
///
///
class LoginPresenter(var view: LoginView):BasePresenter, OnCompleteListener<Any> , BaseLoginPresenter{


    lateinit var userLoginModel: UserLoginModelFBD

    init {
        onCreate()

    }
    override fun onCreate() {
        userLoginModel = UserLoginModelFBDImp(this)
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun startLogin(email: String,password: String){
        if(validation(email, password)){
            App.mAuth?.signInWithEmailAndPassword(email,password)?.addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d("LOGIN",it.exception.toString())
                    checkRole(email)
                }
                else{
                    view.showToast("wrong email or password")
                }
            }
        }
        else{
        }

    }
    fun validation(email: String,password: String): Boolean{
        if(email.isEmpty()){
            view.showToast("Email is empty")
            return false
        }
        if(password.isEmpty()){
            view.showToast("password is empty")
            return false
        }
        return true
    }
    fun checkRole(email: String){
        userLoginModel.checkRole(email)

    }
    override fun notifyUserFound() {
        view.startUserActivity()

    }
    override fun showToast(s: String) {
        view.showToast(s)
    }

    override fun onComplete(p0: Task<Any>) {
        if(!p0.isSuccessful){
            view.showToast("Wrong Email OrPassword")
        }

    }
}