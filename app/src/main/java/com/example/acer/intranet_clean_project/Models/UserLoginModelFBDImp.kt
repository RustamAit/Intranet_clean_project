package com.example.acer.intranet_clean_project.Models

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.HeaderFooter
import com.example.acer.intranet_clean_project.Presenters.BaseLoginPresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener



class UserLoginModelFBDImp(var listener: BaseLoginPresenter): UserLoginModelFBD{
    override fun checkRole(email: String) {

        App.roleChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,Any>
                    if(map["email"] == email){
                        App.role = map["role"].toString()
                        Log.d("ROLE_TEST",App.role)
                        listener.notifyUserFound()
                    }
                }
            }
        })
    }

    override fun foundAdmin(email: String) {

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
        })
    }

    override fun foundTeacher(email: String) {

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