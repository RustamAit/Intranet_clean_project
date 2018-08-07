package com.example.acer.intranet_clean_project.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.acer.intranet_clean_project.MainActivity
import com.example.acer.intranet_clean_project.Presenters.SplashPresenter
import com.example.acer.intranet_clean_project.R

class SplashActivity : AppCompatActivity(),SplashView {

    lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this)
        presenter.checkUser()

    }
    override fun startTeacherActivity() {
        startActivity(Intent(this,TeacherActivity::class.java))
        finish()      }

    override fun startStudentActivity() {
        startActivity(Intent(this,StudentActivity::class.java))
        finish()
    }
    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    override fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()    }
}
