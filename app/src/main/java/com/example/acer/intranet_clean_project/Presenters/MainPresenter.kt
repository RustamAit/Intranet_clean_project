package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.App.Companion.studentsArray
import com.example.acer.intranet_clean_project.App.Companion.teacherArray
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.UserDataEntities
import com.example.acer.intranet_clean_project.Models.UserDataModel
import com.example.acer.intranet_clean_project.Models.UserDataModelListener
import com.example.acer.intranet_clean_project.Views.MainViewListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(var listener: MainViewListener): BasePresenter {
    var dataListener: UserDataModelListener = UserDataModel()
    var entitiesList: ArrayList<Any> = ArrayList()
    var isDataNotUploaded: Boolean = true
    init {
        Log.d("MAIN_PRESENTER","${entitiesList.size}")
        if(App.studentsArray.size!=0 && App.teacherArray.size!=0){
            isDataNotUploaded = false
        }

    }
    override fun onCreate() {
        dataListener.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe{ list -> entitiesList.addAll(list)}
        Log.d("MAIN_PRESENTER_ENTITIES","${entitiesList.size}")
    }

    override fun onDestroy() {

    }
    fun updateStudentArray(){
        if(isDataNotUploaded) {
            studentsArray.clear()
            entitiesList.forEach {
                if (it is UserDataEntities.StudentEntity) {
                    studentsArray.add(Student(it.id, it.name, it.gpa))
                }
            }
        }
    }
    fun updateTeacherArray(){
        if(isDataNotUploaded) {
            teacherArray.clear()
            entitiesList.forEach {
                if (it is UserDataEntities.TeacherEntity) {
                    teacherArray.add(Teacher(it.id, it.name, it.salary, it.course))
                }
            }
        }
    }
}