package com.example.acer.intranet_clean_project.Views

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.acer.intranet_clean_project.Adapters.UserAdapter
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.OnFragmentInteractionListener
import com.example.acer.intranet_clean_project.MainActivity
import com.example.acer.intranet_clean_project.Presenters.RecyclerFragmentPresenter

import com.example.acer.intranet_clean_project.R
import kotlinx.android.synthetic.main.fragment_admin_recycler.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AdminRecyclerFragment : Fragment(),BaseFragmentView {


    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var presenter: RecyclerFragmentPresenter = RecyclerFragmentPresenter(this)
    lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin_recycler, container, false)
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listener = context as MainActivity
        presenter.checkUserRole()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        presenter.getAdmins()
        var layout = GridLayoutManager(activity,1)
        recList.layoutManager = layout
        super.onResume()
    }

    override fun setAdapter(arr: ArrayList<Any>) {
        adapter = UserAdapter(arr,activity as MainActivity)
        recList.adapter = adapter
    }
    override fun changeProgressBarVisability() {
        progressBar.visibility = ProgressBar.GONE
    }

    override fun getPermission() {
        addBtn.setOnClickListener{
                listener?.startAdminCreateActivity()
            }
        addBtn.visibility = FloatingActionButton.VISIBLE
    }


    override fun showToast(s: String) {
        listener?.showToast(s)
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AdminRecyclerFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
