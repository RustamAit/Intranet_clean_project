package com.example.acer.intranet_clean_project.Presenters

import android.util.Log
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Models.BaseUserFBDModel
import com.example.acer.intranet_clean_project.Models.BaseUserFBDModelImp
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.StudentFBDModel
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.StudentFDBModelImp
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.TeacherFBDModel
import com.example.acer.intranet_clean_project.Models.StudentTeacherModels.TeacherFBDModelImp
import com.example.acer.intranet_clean_project.views.BaseFragmentView
import com.example.acer.intranet_clean_project.views.StudentActivity.Companion.gpa

class RecyclerFragmentPresenter(var view: BaseFragmentView): BaseFragmentPresenter {


    var teacherFBDModel: TeacherFBDModel = TeacherFBDModelImp(this)
    var userFBDModel: BaseUserFBDModel = BaseUserFBDModelImp(this)
    var studentFBDModel: StudentFBDModel = StudentFDBModelImp(this)
    override fun onCreate() {

    }

    override fun onDestroy() {
    }


    override fun notifySetChanged(arr: ArrayList<Any>) {
        view.changeProgressBarVisability()
        view.setAdapter(arr)
    }
    fun getStudents(){
        userFBDModel.getStudentsFBD()

    }
    fun getTeachers(){
        userFBDModel.getTeacherFBD()
    }
    fun getAdmins(){
        userFBDModel.getAdminsFBD()
    }
    fun getTeachersCourses(email: String){
        teacherFBDModel.findTeachersCourse(email)
    }
    fun getCourseStudents(courseId:String){
        teacherFBDModel.findCourseStudents(courseId)
    }
    fun getAllCourses(){
        studentFBDModel.getAllCourses()
    }
    fun asignToCourse(email: String,courseId: String){
        studentFBDModel.asignTocourse(email, courseId)
    }
    fun getStudentsCourses(sEmail: String){
        studentFBDModel.getStudentsCourses(sEmail)
    }

    fun getMarks(email: String){
        studentFBDModel.getMarks(email)
    }

    fun checkUserRole(){
        if(App.role == "admin"){
            Log.d("LOGIN_PRESENTER"," STUDENT MODE")
            view.getPermission()
        }
        else if(App.role == "teacher"){
            view.showToast("Teacher Mode")
        }
        else{
            view.showToast("Student Mode")
            Log.d("LOGIN_PRESENTER"," STUDENT MODE")
        }

    }
    override fun showToast(s: String) {
        view.showToast(s)
    }
    override fun calculateGpa(map: HashMap<Subject.Course,Subject.Mark>) {
        Log.d("Map_Test",map.toString())
        var temporaryGpa: Double = 0.0
        var totalCredits = 0
        var items = map.iterator()
        while(items.hasNext()){
            val index = items.next()
            temporaryGpa+=convertToGpaPoints(index.value)*index.key.numberOfCredits
            Log.d("Map_Test_temporaryGpa",temporaryGpa.toString())
            totalCredits+= index.key.numberOfCredits
            Log.d("Map_Test_credits",totalCredits.toString())
        }
        gpa = temporaryGpa/totalCredits
        Log.d("Map_Test_Gpa",gpa.toString())

    }
    fun convertToGpaPoints(mark: Subject.Mark): Double{
        Log.d("GPA_TEST","${mark.letter}")

        when(mark.letter){
            "A" -> return 4.0
            "B" -> return 3.0
            "C" -> return 2.0
            "D" -> return 1.0
            "F" -> return 0.0
        }
        return 0.0
    }

}