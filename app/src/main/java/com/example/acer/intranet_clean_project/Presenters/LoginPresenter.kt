package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.Views.BaseView
import com.example.acer.intranet_clean_project.Views.LoginView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth


///
///
///
class LoginPresenter(var view: LoginView):BasePresenter, OnCompleteListener<Any> {
    override fun onComplete(p0: Task<Any>) {
        if(!p0.isSuccessful){
            view.showToast("Wrong Email OrPassword")
        }

    }
    lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener
    lateinit private var mAuth: FirebaseAuth

    init {
        onCreate()

    }
    override fun onCreate() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    fun startLogin(email: String,password: String){
        if(validation(email, password)){
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d("LOGIN",it.exception.toString())
                    view.startMainActivity()
                }
            }
        }
        else{
            view.showToast("wrong data")
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
}