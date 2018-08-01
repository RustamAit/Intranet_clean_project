package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Models.UserDataModel
import com.example.acer.intranet_clean_project.Models.UserDataModelListener
import com.example.acer.intranet_clean_project.Views.BaseView
import com.example.acer.intranet_clean_project.Views.CreateViewListener
import com.google.firebase.auth.FirebaseAuth

class CreatePresenter(var view: BaseView): BasePresenter {

    lateinit private var mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }
    var UserDataModelListener: UserDataModelListener = UserDataModel(this)

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
    }


    fun saveStudent(name: String, id: String, surname: String,email: String,password: String,yearOfStudy: String ){
        Log.d("CREATE_USER",email)
        if(studentValidation(name,id, surname)){
            Log.d("CREATE_USER",email)

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(!it.isSuccessful){
                    Log.d("CREATE_USER",it.exception.toString())
                    view.showToast("Wrong Email")
                }
                else{
                    UserDataModelListener.addStudentFBD(Student(id,name,surname,email,password,"",yearOfStudy.toInt()))
                    (view as CreateViewListener).startMainActivity()
                }
            }
        }
    }
    fun saveTeacher(name: String,id: String,email: String, surname: String,password: String,salary: String,course: String){
  //      if(teacherValidation(name, id, salary, course)){

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if(!it.isSuccessful){
                    view.showToast("Wrong Email")
                }
                else{
                    UserDataModelListener.addTeacherFBD(Teacher(id,name,surname,email,password,"",salary.toString().toInt(),course))
                    (view as CreateViewListener).startMainActivity()
                }
            }
    //    }


    }



    fun studentValidation(name: String, id: String, yearOfStudy: String): Boolean{
        if(name.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(id.isEmpty()){
            view.showToast("id is empty")
            return false
        }
        if(yearOfStudy.isEmpty()){
            view.showToast("yearOfStudy is empty")
            return false
        }
//        if(yearOfStudy.toInt()>4){
  //          view.showToast("invalid gpa")
    //        return false
      //  }
        return true
    }
    fun teacherValidation(name: String, id: String, salary: String,course: String): Boolean{
        if(name.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(id.isEmpty()){
            view.showToast("id is empty")
            return false
        }
        if(salary.isEmpty()){
            view.showToast("salary is empty")
            return false
        }
        if(course.isEmpty()){
            view.showToast("yearOfStudy is empty")
            return false
        }
        return true
    }
}