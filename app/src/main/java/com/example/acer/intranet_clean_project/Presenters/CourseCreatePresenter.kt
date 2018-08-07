package com.example.acer.intranet_clean_project.Presenters

import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.TeacherFBDModel
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.TeacherFBDModelImp
import com.example.acer.intranet_clean_project.views.BaseTeacherCreateView

class CourseCreatePresenter(var view: BaseTeacherCreateView): BasePresenter{

    var teacherFBD: TeacherFBDModel = TeacherFBDModelImp(this)

    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun createCourse(teacherEmail: String,title: String,description: String,numberOfCredits: String ){
        if(courseValidation(title, description, numberOfCredits)){
            teacherFBD.createCourse(teacherEmail, Subject.Course(null,title,description,numberOfCredits.toInt()))
            view.startTeacherActivity()
        }

    }
    fun courseValidation(title: String,description: String,numberOfCredits: String):Boolean{
        if(title.isEmpty()){
            showToast("title is empty")
            return false
        }
        if(description.isEmpty()){
            showToast("description is empty")
            return false
        }
        if(numberOfCredits.isEmpty()){
            showToast("numberOfCredits is empty")
            return false
        }
        return true

    }


    override fun showToast(s: String) {
        view.showToast(s)
    }

}