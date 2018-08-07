package com.example.acer.intranet_clean_project.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.acer.intranet_clean_project.App.Companion.mAuth
import com.example.acer.intranet_clean_project.Presenters.CourseCreatePresenter
import com.example.acer.intranet_clean_project.R
import kotlinx.android.synthetic.main.activity_course_create.*

class CourseCreateActivity : AppCompatActivity(),BaseTeacherCreateView {


    lateinit var courseCreatePresenter: CourseCreatePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        courseCreatePresenter = CourseCreatePresenter(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_create)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.create_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.done_btn-> {
                courseCreatePresenter.createCourse(mAuth?.currentUser?.email.toString(),coursetitle.text.toString(),desciption.text.toString(),numberOfCredits.text.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun startLoginActivity() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
    override fun startTeacherActivity() {
        startActivity(Intent(this,TeacherActivity::class.java))
        finish()      }

    override fun showToast(s: String) {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show()
    }
}
