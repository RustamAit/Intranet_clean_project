package com.example.acer.intranet_clean_project.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.acer.intranet_clean_project.Data.Admin
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Data.HeaderFooter
import com.example.acer.intranet_clean_project.R


class UserAdapter(val dataset: ArrayList<Any>,var listener: OnItemClicked): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
            when(dataset[position]){
                is Student-> UserTypes.STUDENT
                is Teacher-> UserTypes.TEACHER
                is Admin->UserTypes.ADMIN
                is HeaderFooter.Header -> when((dataset[position] as HeaderFooter.Header).getType()){
                    1 -> UserTypes.STUDENT_HEADER
                    2 -> UserTypes.TEACHER_HEADER
                    else -> {UserTypes.ADMIN_HEADER}
                }
                else ->{UserTypes.UNDEFINED }
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("asdfg","ViewType is $viewType")
        when(viewType){
            UserTypes.TEACHER -> return TeacherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teacher,parent,false))
            UserTypes.STUDENT -> return StudentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_student,parent,false))
            UserTypes.ADMIN -> return AdminViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_admin,parent,false))
            UserTypes.TEACHER_HEADER -> return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.teacher_item_header,parent,false))
            UserTypes.STUDENT_HEADER -> return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.student_item_header,parent,false))
            UserTypes.ADMIN_HEADER ->return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.admin_item_header,parent,false))
            else -> {
                return UndefinedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_undefined,parent,false))
            }
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("ADAPTER_LOG","${dataset[position].toString()}")
        when(holder){
            is StudentViewHolder -> {
                holder.itemView.setOnClickListener {
                    listener.studentClick()
                }
                return holder.bind(dataset[position] as Student)}
            is TeacherViewHolder -> return holder.bind(dataset[position] as Teacher)
            is AdminViewHolder -> return holder.bind(dataset[position] as Admin)

        }
    }


    inner class StudentViewHolder(v: View): RecyclerView.ViewHolder(v){

        fun bind(p: Student){
            val name = itemView.findViewById<TextView>(R.id.name)
            val age  = itemView.findViewById<TextView>(R.id.age)
            val course  = itemView.findViewById<TextView>(R.id.course)

            name.text=p.name
            age.text =p.id
            course.text = p.yearOfStudy!!.toString()

            Log.d("qwerty", "viewHolder => $p")
        }

    }
    inner class TeacherViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: Teacher){
            val name = itemView.findViewById<TextView>(R.id.name)
            val age  = itemView.findViewById<TextView>(R.id.age)
            val salary  = itemView.findViewById<TextView>(R.id.gpa)

            name.text=p.name
            age.text =p.id
            salary.text = p.salary.toString()

            Log.d("qwerty", "vieewHolder => $p")
        }
    }
    inner class AdminViewHolder(v: View): RecyclerView.ViewHolder(v){
        fun bind(p: Admin){
            val name = itemView.findViewById<TextView>(R.id.name)
            val surname = itemView.findViewById<TextView>(R.id.surname)
            val id = itemView.findViewById<TextView>(R.id.aId)
            name.text = p.name
            surname.text = p.surname
            id.text = p.id
        }
    }

    inner class UndefinedViewHolder(v: View): RecyclerView.ViewHolder(v){}
    inner class HeaderViewHolder(v: View): RecyclerView.ViewHolder(v){}

    object UserTypes{
        const val UNDEFINED = 0
        const val STUDENT = 1
        const val TEACHER = 2
        const  val ADMIN = 3
        const val STUDENT_HEADER = 4
        const val TEACHER_HEADER = 5
        const val ADMIN_HEADER  = 6
    }
}
