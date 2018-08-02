package com.example.acer.intranet_clean_project.Models

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.Admin
import com.example.acer.intranet_clean_project.Data.HeaderFooter
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Presenters.BaseFragmentPresenter
import com.example.acer.intranet_clean_project.Presenters.BaseLoginPresenter
import com.example.acer.intranet_clean_project.Presenters.LoginPresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener



class UserLoginModel(var listener: BaseLoginPresenter): UserLoginModelListener{
    override fun foundAdmin(email: String) {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(1))

        App.adminChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,Any>
                    if(map["email"] == email){
                        App.role = "admin"
                        listener.notifyUserFound()
                    }
                }
            }
        })    }

    override fun foundTeacher(email: String) {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(2))

        App.teacherChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")
                    if(map["email"] == email){
                        App.role = "teacher"
                        listener.notifyUserFound()
                    }

                }
            }
        })

    }
    override fun foundStudent(email: String) {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(1))

        App.studentChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,Any>
                    if(map["email"] == email){
                        App.role = "student"
                        listener.notifyUserFound()
                    }
                }
            }
        })
    }

}