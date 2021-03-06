package com.example.acer.intranet_clean_project.Views

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.acer.intranet_clean_project.Adapters.CourseRecyclerAdapter
import com.example.acer.intranet_clean_project.Adapters.OnTeacherItemClicked
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.OnTeacherFramgentInteractionListener
import com.example.acer.intranet_clean_project.Presenters.RecyclerFragmentPresenter
import com.example.acer.intranet_clean_project.R
import kotlinx.android.synthetic.main.fragment_teachers_courses_recycler.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TeachersCoursesRecycler : Fragment(), BaseFragmentView, OnTeacherItemClicked {


    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnTeacherFramgentInteractionListener? = null
    var presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)
    lateinit var adapter: CourseRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teachers_courses_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listener = context as TeacherActivity
        addBtn.setOnClickListener {
            listener?.startCourseCreateActivity()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onResume() {
        presenter.getTeachersCourses(App.mAuth?.currentUser?.email.toString())
        adapter = CourseRecyclerAdapter(ArrayList<Any>(), App.role,this)
        var layout = GridLayoutManager(activity,1)
        recList.layoutManager = layout
        super.onResume()
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun setAdapter(arr: ArrayList<Any>) {
        adapter = CourseRecyclerAdapter(arr,App.role,this)
        recList.adapter = adapter
    }

    override fun changeProgressBarVisability() {
        progressBar.visibility = ProgressBar.GONE
    }

    override fun getPermission() {
        addBtn.setOnClickListener{
        }
        addBtn.visibility = FloatingActionButton.VISIBLE    }

    override fun showToast(s: String) {
        listener?.showToast(s)

    }
    override fun showStudents(cId: String) {
        listener?.showCourseStudents(cId)
    }
    override fun markStudent(sEmail: String) {
        //listener?.markStudent(sEmail)
        Log.d("zxcvb","show dialog")
    }




    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TeachersCoursesRecycler().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
