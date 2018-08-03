package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.Admin
import com.example.acer.intranet_clean_project.Data.Subject
import com.google.firebase.database.*

class TeacherFBDModelImp: TeacherFBDModel {
    override fun markStudent(email: String, m: Subject.Mark) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findStudent(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findTeacher(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findTeachersCourse(email: String) {
        var teachersCorsesReference =  App.db?.reference?.child("TeachersCourses")
        var result = ArrayList<String?>()
        teachersCorsesReference?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String,Any>
                    if(email == map["teacherEmail"]){
                        result.add(map["courseId"].toString())
                    }
                }
                findCourses(result)
            }
        })    }

    override fun findStudentCours(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun CreateCourse(email: String,c: Subject.Course) {
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var courseRef: DatabaseReference? = mFirebaseDatabaseReference?.child("courses")?.push()
        c.id = courseRef?.key.toString()
        courseRef?.setValue(c)
        var teachersCoursesRef =  mFirebaseDatabaseReference?.child("teachersCourses")?.push()
        teachersCoursesRef?.setValue(Subject.TeachersCourse(c.id!!,email))
    }

    fun findCourses(arr: ArrayList<String?>){
        var result = ArrayList<Any?>()
        App.courseChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                var items = p0?.children?.iterator()
                while (items!!.hasNext()) {
                    var index = items?.next()
                    val map = index?.getValue() as HashMap<String, Any>
                    arr.forEach {
                        if (it == map["id"]!!.toString()) {
                            var course = Subject.Course(map["id"].toString(),map["title"].toString(),map["description"].toString(),map["numberOfCredits"].toString().toInt())
                            result.add(course)
                            Log.d("COURSE_TEST",course.toString())
                        }
                    }
                    //listener.notifySetChanged(result)
                }
            }
        })    }

}


