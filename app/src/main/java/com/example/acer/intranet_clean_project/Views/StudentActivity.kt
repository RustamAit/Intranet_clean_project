package com.example.acer.intranet_clean_project.Views

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.acer.intranet_clean_project.App
import com.example.acer.intranet_clean_project.Data.OnStudentFragmentInteractionListener
import com.example.acer.intranet_clean_project.R
import com.example.acer.intranet_clean_project.Views.StudentActivity.Companion.PAGE_COUNT

class StudentActivity : AppCompatActivity(),BaseStudentTeacherView,OnStudentFragmentInteractionListener {

    companion object {
        val PAGE_COUNT = 2
        var pagerPosition = 0
        var gpa = 0.0
    }

    lateinit var pagerAdapter: RecyclerViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

    }

    override fun onResume() {
        var pager = findViewById<ViewPager>(R.id.pager)
        pagerAdapter =RecyclerViewPagerAdapter(supportFragmentManager)
        pager?.adapter = pagerAdapter

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
    override fun registerToCourse(cId: String) {
    }
    override fun changePagerPosition(i: Int) {
        pagerAdapter.getItem(i)
    }


    override fun startLoginActivity() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    override fun showToast(s: String) {
        Toast.makeText(this,s, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.student_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out_menu -> {
                App.mAuth?.signOut()
                startLoginActivity()
                finish()
                return true
            }
            R.id.gpa_menu->{
                showAlarmMessage("Your current gpa is ${gpa}")
            }

        }
        return super.onOptionsItemSelected(item)
    }
    fun showAlarmMessage(s: String){
        var dialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setMessage(s)
        builder.setPositiveButton("Ok"){dialogInterface, i ->
            dialogInterface.dismiss()
        }
        dialog = builder.create()
        dialog.show()
    }
}






class RecyclerViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        Log.d("POSITION",position.toString())
        when(position){
            0 -> return StudentCoursesRecyclerFragment()
            1 -> return CourseRecyclerFragment()
        }

        return CourseRecyclerFragment()


    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "My courses"
            1->return "All courses"
        }
        return super.getPageTitle(position)
    }
    override fun getCount(): Int {
        return PAGE_COUNT
    }

}