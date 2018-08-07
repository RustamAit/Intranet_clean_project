package com.example.acer.intranet_clean_project.Presenters

import com.example.acer.intranet_clean_project.App.Companion.mAuth

import com.example.acer.intranet_clean_project.Data.*
import com.example.acer.intranet_clean_project.Views.MainViewListener


class MainPresenter(var view: MainViewListener): BasePresenter, dataChangeListener {



    override fun onCreate() {

//        if(isDataNotUploaded == true){
//            dataListener.getData().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe{ list -> entitiesList.addAll(list)}
//            Log.d("MAIN_PRESENTER_ENTITIES","${entitiesList.size}")
//        }
    }

    override fun onDestroy() {    }

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