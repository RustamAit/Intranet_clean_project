package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App.Companion.mAuth

import com.example.acer.intranet_clean_project.Data.*
import com.example.acer.intranet_clean_project.Models.UserDataModelRoomDB
import com.example.acer.intranet_clean_project.Models.UserDataModelListener
import com.example.acer.intranet_clean_project.Views.MainViewListener
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuth



class MainPresenter(var view: MainViewListener): BasePresenter, dataChangeListener {




    var entitiesList: ArrayList<Any> = ArrayList()
    var isUserSignedIn: Boolean = false
    var isDataNotUploaded: Boolean = true
    override fun onCreate() {
//        if(isDataNotUploaded == true){
//            dataListener.getData().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe{ list -> entitiesList.addAll(list)}
//            Log.d("MAIN_PRESENTER_ENTITIES","${entitiesList.size}")
//        }
    }

    override fun onDestroy() {    }




    fun checkUser(){
            var mFirebaseUser = mAuth?.currentUser
            if(mFirebaseUser==null){
                view?.startLoginActivity()
                isUserSignedIn = true
                Log.d("LOGIN_TEST","firebase user = null")

            }
            else{
                Log.d("LOGIN_TEST","firebase user = found")
                Log.d("LOGIN_TEST","${mFirebaseUser?.uid}")
                Log.d("LOGIN_TEST","${mFirebaseUser?.displayName}")
                Log.d("LOGIN_TEST","${mFirebaseUser?.email}")
            }
    }
    fun signOut(){
        mAuth?.signOut()

        view?.startLoginActivity()
    }
    override fun onDataUpdated() {
    }
    override fun showToast(s: String) {
        view.showToast(s)
    }

}