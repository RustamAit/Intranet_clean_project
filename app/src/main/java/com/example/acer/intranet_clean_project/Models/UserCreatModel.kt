package com.example.acer.intranet_clean_project.Models

import com.example.acer.intranet_clean_project.Data.Admin
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.UserDataEntities
import com.example.acer.intranet_clean_project.Presenters.BasePresenter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserCreatModel(var listener: BasePresenter): UserCreateModelListener{
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



}
