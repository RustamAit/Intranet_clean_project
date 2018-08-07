package com.example.acer.intranet_clean_project.Views

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import android.util.Log
import com.example.acer.intranet_clean_project.App.Companion.mAuth
import com.example.acer.intranet_clean_project.Data.OnTeacherFramgentInteractionListener
import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.Presenters.TeacherPresenter
import com.example.acer.intranet_clean_project.R

class TeacherActivity : AppCompatActivity(),OnTeacherFramgentInteractionListener, BaseStudentTeacherView {




    var sEmail: String = ""
    var cId: String = ""
    lateinit var presenter: TeacherPresenter
    val ID_MARKS: Int = 54321
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)
        presenter = TeacherPresenter(this)
        supportFragmentManager.beginTransaction().add(R.id.container,TeachersCoursesRecycler()).commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out_menu -> {
                mAuth?.signOut()
                startLoginActivity()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun startLoginActivity() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
    override fun startCourseCreateActivity() {
        startActivity(Intent(this,CourseCreateActivity::class.java))
    }

    override fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }


    override fun getCourseId(): String? {
        return cId
    }
    override fun showCourseStudents(cId: String) {
        this.cId = cId
        supportFragmentManager.beginTransaction().replace(R.id.container,StudentRecyclerFragment()).addToBackStack("").commit()
    }
    override fun markStudent(sEmail: String) {
        this.sEmail = sEmail
        showDialog()
    }
    private fun showDialog(){
        var arrayOfletters = arrayOf("A","B","C","D","F")
        var arrayOfMarks = arrayOf(Subject.Mark("1","A",100),Subject.Mark("2","B",80),
                Subject.Mark("3","C",70),Subject.Mark("4","D",50),Subject.Mark("5","F",0))
        var dialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Mark")
        builder.setItems(arrayOfletters){
            dialogInterface, i ->
                Log.d("MARK_ADD_TEST",arrayOfMarks[i].id!!+" "+ sEmail+"  "+cId)
                presenter.markStudent(Subject.StudentMark(arrayOfMarks[i].id!!,sEmail,cId))
                Log.d("MARK_ADD_TEST",arrayOfMarks[i].id!!+" "+ sEmail+"  "+cId)
                dialogInterface.dismiss()
        }
        dialog = builder.create()
        dialog.show()

    }





}
