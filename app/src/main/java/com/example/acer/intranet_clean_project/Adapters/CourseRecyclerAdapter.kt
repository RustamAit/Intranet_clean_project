package com.example.acer.intranet_clean_project.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Subject
import com.example.acer.intranet_clean_project.R

class CourseRecyclerAdapter(val dataset: ArrayList<Any>, var userRole: String, var listener: OnItemClicked): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var visibility: Int = Button.VISIBLE

    override fun getItemViewType(position: Int): Int =
        when(dataset[position]){
            is Subject.Transctript -> Types.TRANSCRIPT
            else ->  Types.COURSE
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            Types.TRANSCRIPT ->return TranscriptViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_transcript,parent,false),userRole)
        else -> return CourseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_course,parent,false),userRole)
       }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
           is TranscriptViewHolder -> holder.bind(dataset[position] as Subject.Transctript)
           is CourseViewHolder-> holder.bind(dataset[position] as Subject.Course)
        }
    }

    fun setBtnVisible(){
        visibility = Button.VISIBLE
    }
    fun setBtnInvisible(){
        visibility = Button.GONE
    }



    inner class CourseViewHolder(v: View, var userRole: String): RecyclerView.ViewHolder(v){

        fun bind(p: Subject.Course){
            val title = itemView.findViewById<TextView>(R.id.title)
            val numberOfCredits  = itemView.findViewById<TextView>(R.id.teacherName)
            val btn  = itemView.findViewById<Button>(R.id.button)
            title.text = p.title
            numberOfCredits.text = p.numberOfCredits.toString()
            when(userRole){
                "student" -> {
                    btn.visibility = visibility
                    btn.text = "Asign To Course"
                    btn.setOnClickListener {
                        (listener  as OnStudentItemClicked).asignToCourse(p.id!!)
                    }
                }
                "teacher" -> {
                    btn.visibility = visibility
                    btn.text = "View Students"
                    btn.setOnClickListener {
                        (listener as OnTeacherItemClicked).showStudents(p.id!!)
                    }
                }
            }



            Log.d("qwerty", "viewHolder => $p")
        }

    }
    inner class TranscriptViewHolder(v: View, var userRole: String): RecyclerView.ViewHolder(v){

        fun bind(p: Subject.Transctript){
            val courseTitle = itemView.findViewById<TextView>(R.id.courseTitle)
            val mark  = itemView.findViewById<TextView>(R.id.mark)
            courseTitle.text = p.course.title
            mark.text = p.mark.letter
            Log.d("qwerty", "viewHolder => $p")
        }

    }


    object Types{
        const val COURSE = 0
        const val TRANSCRIPT = 1
    }


}