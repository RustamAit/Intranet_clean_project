package com.example.acer.intranet_clean_project.Views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.MainActivity
import com.example.acer.intranet_clean_project.Presenters.LoginPresenter
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.example.acer.intranet_clean_project.R
import com.google.android.gms.common.SignInButton
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener,LoginView {




    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        loginPresenter = LoginPresenter(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.create_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.done_btn-> {
                loginPresenter.startLogin(email.text.toString(),password.text.toString())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.d("SIGN_IN_LOG", "onConnectionFailed:" + p0);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();    }


    override fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show()
    }
    override fun startMainActivity() {
        startActivity(Intent(this,MainActivity::class.java))
    }




}