package com.example.acer.intranet_clean_project.Presenters

import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.TeacherFBDModel
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.TeacherFBDModelImp
import com.example.acer.intranet_clean_project.views.BaseStudentTeacherView

class TeacherPresenter(var listener: BaseStudentTeacherView): BasePresenter {
    var teacherFBDModel: TeacherFBDModel = TeacherFBDModelImp(this)
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun markStudent(studentMark: Subject.StudentMark){
        teacherFBDModel.markStudent(studentMark)

    }

    override fun showToast(s: String) {
        listener.showToast(s)
    }
}
