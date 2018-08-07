package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.App.Companion.courseChildRef
import com.example.acer.intranet_clean_project.App.Companion.courseStudentsRef
import com.example.acer.intranet_clean_project.App.Companion.markStudentRef
import com.example.acer.intranet_clean_project.App.Companion.teachersCoursesref
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Presenters.BaseFragmentPresenter
import com.example.acer.intranet_clean_project.Presenters.BasePresenter
import com.google.firebase.database.*
import java.util.*

class TeacherFBDModelImp(var listener: BasePresenter): TeacherFBDModel {

    override fun findTeachersCourse(email: String) {
        var result = ArrayList<String?>()
        val eventListener: ValueEventListener = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }
            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    val index = items?.next()
                    val map = index?.value as HashMap<String,Any?>
                    if(email == map["teacherEmail"].toString()){
                        result.add(map["courseId"].toString())
                    }
                }
                findCourses(result)
            }
        }
        teachersCoursesref?.addListenerForSingleValueEvent(eventListener)
    }

    fun findCourses(arr: ArrayList<String?>){
        var result = ArrayList<Any>()
        App.courseChildRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }
            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while (items!!.hasNext()) {
                    val index = items?.next()
                    val map = index?.value as HashMap<String, Any>
                    arr.forEach {
                        if (it == map["id"]!!.toString()) {
                            val course = Subject.Course(map["id"].toString(),map["title"].toString(),map["description"].toString(),
                                    map["numberOfCredits"].toString().toInt())
                            result.add(course)
                        }
                    }
                }
                (listener as BaseFragmentPresenter).notifySetChanged(result)
            }
        })    }
    override fun findCourseStudents(courseId: String) {
        var result = ArrayList<String?>()
        courseStudentsRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }
            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    val index = items?.next()
                    val map = index?.value as HashMap<String,Any>
                    if(courseId == map["courseId"].toString()){
                        result.add(map["studentEmail"].toString())

                    }
                }
                findStudents(result)
            }
        })

    }
    fun findStudents(arr: ArrayList<String?>){
        var result = ArrayList<Any>()
        App.studentChildRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }
            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while (items!!.hasNext()) {
                    val index = items?.next()
                    val map = index?.value as HashMap<String, Any>
                    arr.forEach {
                        if (it == map["email"]!!.toString()) {
                            val student = Student(map["id"].toString(),map["name"]!!.toString(),
                                    map["surname"]!!.toString(),map["email"]!!.toString(),
                                    map["password"]!!.toString(),map["ketId"]!!.toString(),map["yearOfStudy"]!!.toString().toInt())
                            result.add(student)
                        }
                    }

                }
                (listener as BaseFragmentPresenter).notifySetChanged(result)
            }
        })
    }

    override fun markStudent(studentMark: Subject.StudentMark) {
        var result = ArrayList<Subject.StudentMark>()
        markStudentRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    val index = items.next()
                    val map = index.value as HashMap<String,Any>
                    result.add(Subject.StudentMark(map["markId"].toString(),map["studentEmail"].toString(),map["courseId"].toString() ))
                }
                addStudentMarkToFBD(result,studentMark)
            }

        })
    }


    override fun createCourse(email: String, c: Subject.Course) {
        var result = ArrayList<Subject.Course?>()
        App.courseChildRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }
            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while (items!!.hasNext()) {
                    val index = items?.next()
                    val map = index?.value as HashMap<String, Any>
                    val course = Subject.Course(map["id"].toString(),map["title"].toString(),map["description"].toString(),map["numberOfCredits"].toString().toInt())
                    result.add(course)
                }
                addCourseToFBD(result,c,email)
            }
        })

    }

    private fun addCourseToFBD(arr: ArrayList<Subject.Course?>, c: Subject.Course,email: String){
        var courseResult = true
        arr.forEach {
            if(it?.equals(c)!!){
                courseResult = false
            }
        }
        if (courseResult) {
            c.id = courseChildRef?.push()?.key.toString()
            courseChildRef?.push()?.setValue(c)
            App.teachersCoursesref?.push()?.setValue(Subject.TeachersCourse(c.id!!,email))
        }
        else{
            listener.showToast("Course already exist")
        }
    }
    private fun addStudentMarkToFBD(arr: ArrayList<Subject.StudentMark>,studentMark: Subject.StudentMark){
        var markResult = true
        arr.forEach {
            if(it?.equals(studentMark)){
                markResult = false
            }
        }
        if(markResult) {
            markStudentRef?.push()?.setValue(studentMark)
        }
        else{
            listener.showToast("You Already marked this student")
        }
        markResult = true
    }


}


