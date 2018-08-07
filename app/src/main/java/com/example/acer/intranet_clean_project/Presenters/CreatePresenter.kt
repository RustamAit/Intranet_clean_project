package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.Data.Admin
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Models.UserCreatModelFBDImp
import com.example.acer.intranet_clean_project.views.BaseView
import com.google.firebase.auth.FirebaseAuth

class CreatePresenter(var view: BaseView): BasePresenter {


    lateinit private var mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }
    var userCreatModel: UserCreatModelFBDImp = UserCreatModelFBDImp(this)

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
    }


    fun saveStudent(name: String, id: String, surname: String,email: String,password: String,yearOfStudy: String ){
        Log.d("CREATE_USER",email)
        if(studentValidation(name,id, yearOfStudy,surname,email,password)){
            userCreatModel.addUser(Student(id,name,surname,email,password,"",yearOfStudy.toInt()))

        }
    }
    fun saveTeacher(name: String,id: String,email: String, surname: String,password: String,salary: String,course: String){
        if(teacherValidation(name, id, salary, course,surname,email,password)){
            userCreatModel.addUser(Teacher(id,name,surname,email,password,"",salary.toString().toInt(),course))
        }


    }
    fun saveAdmin(name: String,id: String, surname: String,email: String,password: String){
        if(adminValidation(name, id,surname,email,password)){
            userCreatModel.addUser(Admin(id,name,surname,email,password,""))
        }
    }




    fun studentValidation(name: String, id: String, yearOfStudy: String,surname: String,email: String,password: String): Boolean{
        if(name.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(surname.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(email.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(password.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(password.length<6){
            view.showToast("Password should contain at least 6 characters")
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
        if(yearOfStudy.toInt()>7){
            view.showToast("invalid year of study")
            return false
        }
        return true
    }
    fun teacherValidation(name: String, id: String, salary: String,course: String,surname: String,email: String,password: String): Boolean{
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
        if(surname.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(email.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(password.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(password.length<6){
            view.showToast("Password should contain at least 6 characters")
            return false
        }
        return true
    }
    fun adminValidation(name: String, id: String, surname: String,email: String,password: String): Boolean{
        if(name.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(id.isEmpty()){
            view.showToast("id is empty")
            return false
        }
        if(surname.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(email.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(password.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(password.length<6){
            view.showToast("Password should contain at least 6 characters")
            return false
        }
        return true
    }
    override fun showToast(s: String) {
        view.showToast(s)
    }
}