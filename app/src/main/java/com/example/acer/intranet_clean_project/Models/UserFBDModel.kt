package com.example.acer.intranet_clean_project.Models

import android.util.Log
import com.example.acer.intranet_clean_project.Data.HeaderFooter
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Presenters.BaseFragmentPresenter
import com.google.firebase.database.*

class UserFBDModel(var listener: BaseFragmentPresenter): UserFBDModelListener{
    override fun getTeacherFBD() {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(2))
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        var ref: DatabaseReference = db.getReference().child("teachers")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")

                    var teacher = Teacher(index.key,map.get("name")!!,map.get("salary").toString().toInt(),map.get("course")!!)
                    result.add(teacher)
                    listener.notifySetChanged(result)

                }
            }
        })

    }
    override fun getStudentsFBD() {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(1))
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        var ref: DatabaseReference = db.getReference().child("students")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")
                    var student = Student(index.key,map.get("name")!!,map.get("course").toString().toInt())
                    result.add(student)
                    listener.notifySetChanged(result)
                }
            }
        })
    }
    fun getAllFBDUsers(){
        var result: ArrayList<Any> = ArrayList()
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        var stRef:DatabaseReference = db.getReference().child("students")
        var thRef: DatabaseReference = db.getReference().child("teachers")
        stRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                result.add(0,HeaderFooter.Header(1))
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")
                    var student = Student(index.key,map.get("name")!!,map.get("course").toString().toInt())
                    result.add(student)
                    listener.notifySetChanged(result)
                }
            }
        })
        thRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")

                    var teacher = Teacher(index.key,map.get("name")!!,map.get("salary").toString().toInt(),map.get("course")!!)
                    result.add(teacher)
                    listener.notifySetChanged(result)
                    result.add(result.size-1,HeaderFooter.Header(2))


                }
            }
        })

    }
}