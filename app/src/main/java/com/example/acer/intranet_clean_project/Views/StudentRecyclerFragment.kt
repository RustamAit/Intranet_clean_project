package com.example.acer.intranet_clean_project.Views

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.acer.intranet_clean_project.Adapters.OnItemClicked
import com.example.acer.intranet_clean_project.Adapters.OnTeacherItemClicked
import com.example.acer.intranet_clean_project.Adapters.UserAdapter
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.OnFragmentInteractionListener
import com.example.acer.intranet_clean_project.Data.OnTeacherFramgentInteractionListener
import com.example.acer.intranet_clean_project.MainActivity
import com.example.acer.intranet_clean_project.Presenters.RecyclerFragmentPresenter
import com.example.acer.intranet_clean_project.R
import kotlinx.android.synthetic.main.fragment_student_recycler.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class StudentRecyclerFragment : Fragment(), BaseFragmentView,OnItemClicked, OnTeacherItemClicked {


    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter: UserAdapter
    private var listener: OnFragmentInteractionListener? = null
    private var teacherlistener: OnTeacherFramgentInteractionListener? = null
    var presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.checkUserRole()
        when(App.role) {
            "admin" -> listener = context as MainActivity
            "teacher" -> {
                teacherlistener = context as TeacherActivity
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onResume() {
        when(App.role){
            "admin" -> presenter.getStudents()
            "teacher" -> {
                presenter.getCourseStudents(teacherlistener?.getCourseId()!!)
            }

        }
        var layout = GridLayoutManager(activity,1)
        recList.layoutManager = layout
        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun setAdapter(arr: ArrayList<Any>) {
        adapter =  UserAdapter(arr, this,App.role)
        recList.adapter = adapter
    }
    override fun changeProgressBarVisability() {
        progressBar.visibility = ProgressBar.GONE
    }
    override fun getPermission() {
        addBtn.visibility = FloatingActionButton.VISIBLE
        addBtn.setOnClickListener{
            listener?.startStudentCreateActivity()
        }
    }
    override fun showStudents(cId: String) {
    }
    override fun markStudent(sEmail: String) {
        teacherlistener?.markStudent(sEmail)

    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentRecyclerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

    }
    override fun showToast(s: String) {
        listener?.showToast(s)
    }
}
