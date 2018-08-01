package com.example.acer.intranet_clean_project.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.acer.intranet_clean_project.MainActivity
import com.example.acer.intranet_clean_project.Presenters.CreatePresenter
import com.example.acer.intranet_clean_project.R
import kotlinx.android.synthetic.main.activity_student_create.*

class StudentCreateActivity : AppCompatActivity(),CreateViewListener {


    var createPresenter: CreatePresenter = CreatePresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_create)
    }

    override fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.create_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.done_btn-> {
                createPresenter.saveStudent(name.text.toString(),sId.text.toString(),surName.text.toString(),email.text.toString(),password.text.toString(),yearOfstudy.text.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java).putExtra("IS_DATA_LOADED",true))
    }

}
