package com.example.acer.intranet_clean_project.Views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.acer.intranet_clean_project.Adapters.CourseRecyclerAdapter
import com.example.acer.intranet_clean_project.Adapters.OnItemClicked
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.OnStudentFragmentInteractionListener
import com.example.acer.intranet_clean_project.Presenters.RecyclerFragmentPresenter

import com.example.acer.intranet_clean_project.R
import com.example.acer.intranet_clean_project.Views.StudentActivity.Companion.gpa
import kotlinx.android.synthetic.main.fragment_transcript.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class TranscriptFragment : Fragment(),BaseFragmentView,OnItemClicked {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnStudentFragmentInteractionListener? = null
    private var presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)
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
        return inflater.inflate(R.layout.fragment_transcript, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.getMarks(App.mAuth?.currentUser?.email.toString())
        listener = context as StudentActivity
        presenter.getAllCourses()
        val layout = GridLayoutManager(activity,1)
        recList.layoutManager = layout
        currentGpa.text = "Current gpa is ${gpa}"
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getPermission() {

    }

    override fun showToast(s: String) {
        listener?.showToast(s)
    }

    override fun setAdapter(arr: ArrayList<Any>) {
        changeProgressBarVisability()
        adapter = CourseRecyclerAdapter(arr, App.role,this)
        recList.adapter = adapter
    }

    override fun changeProgressBarVisability() {
        progressBar.visibility = ProgressBar.GONE
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TranscriptFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
