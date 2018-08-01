package com.example.acer.intranet_clean_project.Models

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.UserDataEntities
import com.example.acer.intranet_clean_project.Data.dataChangeListener
import com.example.acer.intranet_clean_project.Presenters.BasePresenter
import com.google.firebase.database.*
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/// FDB - FIREBASE DATABASE

class UserDataModel(var listener: BasePresenter): UserDataModelListener{


    override fun addTeacherFBD(t: Teacher) {
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var ref: DatabaseReference? = mFirebaseDatabaseReference?.child("teachers")?.push()
        t.ketId = ref?.key.toString()
        ref?.setValue(t)
    }

    override fun addStudentFBD(s: Student) {
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var ref: DatabaseReference? = mFirebaseDatabaseReference?.child("students")?.push()
        s.ketId = ref?.key.toString()
        ref?.setValue(s)
    }





    override fun addStudent(se: UserDataEntities.StudentEntity) {
        Single.fromCallable {
            App.database!!.studentDao().insertStudent(se)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun addTeacher(te: UserDataEntities.TeacherEntity) {
        Single.fromCallable {
            App.database!!.teacherDao().insertTeacher(te)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()    }

    fun updateData(){
        Single.fromCallable {
            App.database!!.teacherDao().getAllTeachers()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
    override fun getData(): Flowable<ArrayList<Any>> {
        var dataList: ArrayList<Any> = ArrayList()
        var flowable: Flowable<ArrayList<Any>> = Flowable.zip(
                App.database!!.studentDao().getAllStudents(),
                App.database!!.teacherDao().getAllTeachers(),
                BiFunction { t1, t2 ->
                    dataList.addAll(t1)
                    dataList.addAll(t2)
                    return@BiFunction dataList
                })
        return flowable
    }

}