package com.example.acer.intranet_clean_project.Models

import android.util.Log
import com.example.acer.intranet_clean_project.App.Companion.studentChildRef
import com.example.acer.intranet_clean_project.App.Companion.teacherChildRef
import com.example.acer.intranet_clean_project.Data.HeaderFooter
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Presenters.BaseFragmentPresenter
import com.google.firebase.database.*

class UserFBDModel(var listener: BaseFragmentPresenter): UserFBDModelListener{
    override fun getTeacherFBD() {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(2))

        teacherChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")

                   var teacher = Teacher(map["id"].toString(),map["name"]!!.toString(),map["surname"]!!.toString(),map["email"]!!.toString(),map["password"]!!.toString(),map["ketId"]!!.toString()
                    ,map.get("salary").toString().toInt(),map.get("course")!!)
                    result.add(teacher)

                }
                listener.notifySetChanged(result)

            }
        })

    }
    override fun getStudentsFBD() {
        var result: ArrayList<Any> = ArrayList()
        result.add(HeaderFooter.Header(1))

        studentChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,Any>
                    Log.d("STUDENT_FROM_FB","$map")
                    var student = Student(map["id"].toString(),map["name"]!!.toString(),map["surname"]!!.toString(),map["email"]!!.toString(),map["password"]!!.toString(),map["ketId"]!!.toString(),map["yearOfStudy"]!!.toString().toInt())
                    result.add(student)
                    //listener.notifySetChanged(result)
                }
                listener.notifySetChanged(result)
            }
        })
    }
    fun getAllFBDUsers(){
        var result: ArrayList<Any> = ArrayList()
        val db: FirebaseDatabase = FirebaseDatabase.getInstance()
        var stRef:DatabaseReference = db.getReference().child("students")
        var thRef: DatabaseReference = db.getReference().child("teachers")
        studentChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                result.add(0,HeaderFooter.Header(1))
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,Any>
                    Log.d("STUDENT_FROM_FB","$map")
                    var student = Student(map["id"].toString(),map["name"]!!.toString(),map["surname"]!!.toString(),map["email"]!!.toString(),map["password"]!!.toString(),map["ketId"]!!.toString(),map["yearOfStudy"]!!.toString().toInt())
                    result.add(student)
                    //listener.notifySetChanged(result)
                }
                listener.notifySetChanged(result)
            }
        })
        teacherChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                var count = 0
                //result.add(result.size-1,HeaderFooter.Header(2))
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,String>
                    Log.d("STUDENT_FROM_FB","$map")
                    var teacher = Teacher(map["id"].toString(),map["name"]!!.toString(),map["surname"]!!.toString(),map["email"]!!.toString(),map["password"]!!.toString(),map["ketId"]!!.toString()
                            ,map.get("salary").toString().toInt(),map.get("course")!!)
                    result.add(teacher)
                   // listener.notifySetChanged(result)
                    result.add(result.size-1,HeaderFooter.Header(2))


                }
                listener.notifySetChanged(result)
            }
        })

    }
}