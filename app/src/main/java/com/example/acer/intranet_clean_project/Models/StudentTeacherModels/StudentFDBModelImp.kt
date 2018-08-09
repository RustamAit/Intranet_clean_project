package com.example.acer.intranet_clean_project.Models.StudentTeacherModels

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Presenters.BaseFragmentPresenter
import com.example.acer.intranet_clean_project.Presenters.BasePresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class StudentFDBModelImp(var listener: BasePresenter): StudentFBDModel {

    override fun getStudentsCourses(email: String) {

        val result = ArrayList<String?>()
        App.courseStudentsRef?.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                result.clear()
                val items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    val index = items?.next()
                    val map = index?.value as HashMap<String,Any>
                    if(email == map["studentEmail"].toString()){
                        if(map["studentEmail"].toString() !in result){
                            result.add(map["courseId"].toString())
                        }
                    }
                }
                findCourses(result)
                Log.d("ARR_TEST_RESULT",result.toString())
            }
        })

    }
    fun findCourses(arr: ArrayList<String?>){
        Log.d("ARR_TEST",arr.toString())
        val result = ArrayList<Any>()
        App.courseChildRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                result.clear()
                val items = p0?.children?.iterator()
                while (items!!.hasNext()) {
                    val index = items?.next()
                    val map = index?.value as HashMap<String, Any>
                    arr.forEach {
                        if (it == map["id"]!!.toString()) {
                            val course = Subject.Course(map["id"].toString(),map["title"].toString(),map["description"].toString(),map["numberOfCredits"].toString().toInt())
                            result.add(course)
                        }
                    }
                }
                (listener as BaseFragmentPresenter).notifySetChanged(result)

            }
        })

    }

    override fun asignTocourse(email: String, courseId: String) {
        var result = true
        App.courseStudentsRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    val index = items?.next()
                    val map = index?.value as HashMap<String,Any>
                    if(email == map["studentEmail"].toString()&&courseId == map["courseId"]){
                        listener.showToast("You have allready registered to this course")
                        result = false
                        break
                    }
                }
                if(result){
                    addCourseStudent(Subject.CourseStudents(email,courseId))
                }
            }
        })


    }

    override fun getAllCourses() {
        val result = ArrayList<Any>()
        App.courseChildRef?.addValueEventListener(object: ValueEventListener {
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
                (listener as BaseFragmentPresenter).notifySetChanged(result)

            }

        })


    }
    fun addCourseStudent(cs: Subject.CourseStudents){
        App.courseStudentsRef?.push()?.setValue(cs)

    }
    override fun getMarks(email: String) {
        val result = HashMap<String,String>()
        App.markStudentRef?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while(items!!.hasNext()){
                    val index = items.next()
                    if(index.child("studentEmail").value==email){
                        result.put(index.child("courseId").value.toString(),index.child("markId").value.toString())
                    }
                }
                getStudentMarks(result)

            }

        })
    }
    fun getStudentMarks(map: HashMap<String,String>){
        val ref = App.db?.reference?.child("marks")
        val result = HashMap<String,Subject.Mark>()
        ref?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val markArray = p0?.value as ArrayList<Any>
                Log.d("GPA_TEST",p0?.value.toString())
                val items = map.iterator()
                while (items.hasNext()){
                    val index = items.next()
                    if(markArray[index.value.toInt()]!=null){
                        val markMap = markArray[index.value.toInt()] as HashMap<String,Any?>
                        result.put(index.key,Subject.Mark(index.value,markMap["letter"].toString(),markMap["points"].toString().toInt()))
                    }
                    
                }
               getMarkedCourses(result)

            }

        })
    }
    fun getMarkedCourses(map: HashMap<String,Subject.Mark>){
        Log.d("Map_Test",map.toString())
        val result = ArrayList<Any>()
        App.courseChildRef?.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val items = p0?.children?.iterator()
                while (items!!.hasNext()) {
                    val index = items.next()
                    val courseMap = index?.value as HashMap<String, Any>
                    val itemsMap = map.iterator()
                    while (itemsMap.hasNext()){
                        val indexMap = itemsMap.next()
                        if(courseMap["id"].toString() == indexMap.key){
                            val course = Subject.Course(courseMap["id"].toString(),courseMap["title"].toString(),courseMap["description"].toString(),courseMap["numberOfCredits"].toString().toInt())
                            result.add(Subject.Transctript(course,indexMap.value))
                        }
                    }
                }
                (listener as BaseFragmentPresenter).calculateGpa(result)
                (listener as BaseFragmentPresenter).notifySetChanged(result)

            }
        })
    }

}
