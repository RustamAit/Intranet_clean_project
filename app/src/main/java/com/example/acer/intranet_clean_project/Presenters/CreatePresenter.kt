package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import android.widget.Toast
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.UserDataEntities
import com.example.acer.intranet_clean_project.Models.UserDataModel
import com.example.acer.intranet_clean_project.Models.UserDataModelListener
import com.example.acer.intranet_clean_project.Views.BaseView
import com.example.acer.intranet_clean_project.Views.CreateViewListener
import com.example.acer.intranet_clean_project.Views.MainViewListener

class CreatePresenter(var view: BaseView): BasePresenter {


    var studentModelListener: UserDataModelListener = UserDataModel()

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
    }


    fun saveStudent(name: String, id: String, gpa: String){
        if(studentValidation(name,id, gpa)){
            studentModelListener.addStudent(UserDataEntities.StudentEntity(null,name,gpa.toDouble()))
            App.studentsArray.add(Student(id.toInt(),name,gpa.toDouble()))
            Log.d("PRESENTER","${App.studentsArray.size}")
            (view as CreateViewListener).startMainActivity()
        }
    }
    fun saveTeacher(name: String,id: String,salary: String,course: String){
        if(teacherValidation(name, id, salary, course)){
            studentModelListener.addTeacher(UserDataEntities.TeacherEntity(null,name,salary.toInt(),course))
            App.teacherArray.add(Teacher(id.toInt(),name,salary.toInt(),course))
            (view as CreateViewListener).startMainActivity()
        }


    }



    fun studentValidation(name: String, id: String, gpa: String): Boolean{
        if(name.isEmpty()){
            view.showToast("Name is empty")
            return false
        }
        if(id.isEmpty()){
            view.showToast("id is empty")
            return false
        }
        if(gpa.isEmpty()){
            view.showToast("gpa is empty")
            return false
        }
        if(gpa.toDouble()>4.0){
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