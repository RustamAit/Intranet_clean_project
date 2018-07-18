package com.example.acer.intranet_clean_project.Models

import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.UserDataEntities
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class UserDataModel: UserDataModelListener{
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