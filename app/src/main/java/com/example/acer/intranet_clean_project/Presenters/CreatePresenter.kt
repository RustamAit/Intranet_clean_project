package com.example.acer.intranet_clean_project.Presenters

import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Models.UserDataModel
import com.example.acer.intranet_clean_project.Models.UserDataModelListener
import com.example.acer.intranet_clean_project.Views.BaseView
import com.example.acer.intranet_clean_project.Views.CreateViewListener

class CreatePresenter(var view: BaseView): BasePresenter {


    var UserDataModelListener: UserDataModelListener = UserDataModel(this)

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
    }


    fun saveStudent(name: String, id: String, course: String){
        if(studentValidation(name,id, course)){
            UserDataModelListener.addStudentFBD(Student(id,name,course.toInt()))
            (view as CreateViewListener).startMainActivity()
        }
    }
    fun saveTeacher(name: String,id: String,salary: String,course: String){
        if(teacherValidation(name, id, salary, course)){
            UserDataModelListener.addTeacherFBD(Teacher(id,name,salary.toInt(),course))
            (view as CreateViewListener).startMainActivity()
        }


    }



    fun studentValidation(name: String, id: String, course: String): Boolean{
        if(name.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(id.isEmpty()){
            view.showToast("id is empty")
            return false
        }
        if(course.isEmpty()){
            view.showToast("gpa is empty")
            return false
        }
        if(course.toInt()>4){
            view.showToast("invalid gpa")
            return false
        }
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
            view.showToast("course is empty")
            return false
        }
        return true
    }
}