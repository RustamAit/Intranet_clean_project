package com.example.acer.intranet_clean_project.Models

import com.example.acer.intranet_clean_project.App.Companion.mAuth
import com.example.acer.intranet_clean_project.Data.*
import com.example.acer.intranet_clean_project.Presenters.BasePresenter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserCreatModelFBDImp(var listener: BasePresenter): UserCreateModelFBD{
    override fun addTeacherFBD(t: Teacher) {
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var ref: DatabaseReference? = mFirebaseDatabaseReference?.child("teachers")?.push()
        t.ketId = ref?.key.toString()
        ref?.setValue(t)
    }

    override fun addStudentFBD(s: Student) {
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var ref: DatabaseReference? = mFirebaseDatabaseReference?.child("students")?.push()
        s.ketId = ref?.key.toString()
        ref?.setValue(s)
    }

    override fun addAdminFBD(a: Admin) {
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var ref: DatabaseReference? = mFirebaseDatabaseReference?.child("admins")?.push()
        a.ketId = ref?.key.toString()
        ref?.setValue(a)
    }

    fun addRole(ur: UserDataEntities.UserRole){
        var mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference()
        var ref: DatabaseReference? = mFirebaseDatabaseReference?.child("roles")?.push()
        ref?.setValue(ur)
    }
    override fun addUser(u: User){
        when(u){
            is Student -> {
                mAuth?.createUserWithEmailAndPassword(u.email,u.password).let {
                    if(it!!.isSuccessful){
                        addStudentFBD(u)
                        addRole(UserDataEntities.UserRole(u.email,"student"))
                    }
                    else{
                        listener.showToast("Wrong email or password")
                    }
                }
            }
            is Admin -> {
                mAuth?.createUserWithEmailAndPassword(u.email,u.password).let {
                    if(it!!.isSuccessful){
                        addAdminFBD(u)
                    }
                    else{
                        listener.showToast("Wrong email or password")
                        addRole(UserDataEntities.UserRole(u.email,"admin"))

                    }
                }
            }
            is Teacher -> {
                mAuth?.createUserWithEmailAndPassword(u.email,u.password).let {
                    if(it!!.isSuccessful){
                        addTeacherFBD(u)
                        addRole(UserDataEntities.UserRole(u.email,"student"))
                    }
                    else{
                        listener.showToast("Wrong email or password")
                    }
                }
            }
        }
    }



}
