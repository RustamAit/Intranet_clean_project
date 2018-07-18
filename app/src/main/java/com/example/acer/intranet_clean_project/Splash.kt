package com.example.acer.intranet_clean_project

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.acer.intranet_clean_project.Presenters.MainPresenter

class Splash : AppCompatActivity() {
    var TIME_OUT: Long = 200
    override fun onCreate(savedInstanceState: Bundle?) {
        var presenter = MainPresenter(MainActivity())
        super.onCreate(savedInstanceState)
        Log.d("SPLASH","I am splash")
        setContentView(R.layout.activity_splash)
        presenter.onCreate()
        Handler().postDelayed({
            run {
                var intent: Intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, TIME_OUT)

    }

}