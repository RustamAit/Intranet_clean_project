package com.example.acer.intranet_clean_project

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.Toast
import com.example.acer.intranet_clean_project.Data.HeaderFooter
import com.example.acer.intranet_clean_project.Data.OnFragmentInteractionListener
import com.example.acer.intranet_clean_project.Data.Student
import com.example.acer.intranet_clean_project.Data.Teacher
import com.example.acer.intranet_clean_project.Presenters.MainPresenter
import com.example.acer.intranet_clean_project.Views.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),OnFragmentInteractionListener,MainViewListener {


    var mainPresenter: MainPresenter = MainPresenter(this)
    companion object {
        val PAGE_COUNT = 3
        var pagerPosition = 0
    }
    var dataset: ArrayList<Any> = ArrayList<Any>()
    var pager: ViewPager? = null
    var pagerAdapter: RecyclerViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        mainPresenter.onCreate()
        Log.d("MAIN_ACTIVITY_oncreate","${App.studentsArray.size}")
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        Log.d("MAIN_ACTIVITY_resume","${App.studentsArray.size}")
        pager = findViewById(R.id.pager) as ViewPager
        pagerAdapter = RecyclerViewPagerAdapter(supportFragmentManager)
        pager?.adapter = pagerAdapter
        teachersList.setOnClickListener{
            pager?.setCurrentItem(2)
        }
        studentsList.setOnClickListener{
            pager?.setCurrentItem(1)
        }
        allUsersList.setOnClickListener{
            pager?.setCurrentItem(0)
        }
        pager?.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                //Log.d("MAIN_ACTIVITY", "onPageSelected, position = $position")
                Log.d("VIEW_STATUS_TEST", "pagerAdapter Position $position")

                pagerPosition = position
            }

            override fun onPageScrolled(position: Int, positionOffset: Float,
                                        positionOffsetPixels: Int) {
                Log.d("MAIN_ACTIVITY", "scroled $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.d("MAIN_ACTIVITY", "State $state")
            }
        })
        super.onResume()
    }

    override fun getData(): ArrayList<Any> {
        dataset.clear()
        dataset.addAll(getStudents())
        dataset.addAll(getTeachers())
        return dataset
    }
    override fun getStudents(): ArrayList<Any> {
        mainPresenter.updateStudentArray()
        App.studentsArray.add(0,HeaderFooter.Header(1))
        return App.studentsArray
    }

    override fun getTeachers(): ArrayList<Any> {
        mainPresenter.updateTeacherArray()
        App.teacherArray.add(0,HeaderFooter.Header(2))
        return App.teacherArray
    }
    override fun startStudentCreateActivity() {
        startActivity(Intent(this,StudentCreateActivity::class.java))
    }

    override fun startTeacherCreateAvtivity() {
        startActivity(Intent(this,TeacherCreateActivity::class.java))
    }
    override fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG)
    }

    override fun onPause() {
        super.onPause()
    }
    class RecyclerViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            Log.d("POSITION",position.toString())
            when(position){
                1 -> return StudentRecyclerFragment()
                2 -> return TeacherRecyclerFragment()
                0 -> return UserRecyclerFragment()
            }

            return UserRecyclerFragment()

        }

        override fun getCount(): Int {
            return MainActivity.PAGE_COUNT
        }
    }
}
