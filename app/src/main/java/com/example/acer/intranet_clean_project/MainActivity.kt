package com.example.acer.intranet_clean_project

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
import com.example.acer.intranet_clean_project.Adapters.OnItemClicked
import com.example.acer.intranet_clean_project.Data.OnFragmentInteractionListener
import com.example.acer.intranet_clean_project.Presenters.MainPresenter
import com.example.acer.intranet_clean_project.Views.*
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),OnFragmentInteractionListener,MainViewListener, OnItemClicked, GoogleApiClient.OnConnectionFailedListener {

    lateinit var mainPresenter: MainPresenter
    companion object {
        val PAGE_COUNT = 3
        var pagerPosition = 0
    }
    var pager: ViewPager? = null
    var pagerAdapter: RecyclerViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

        mainPresenter = MainPresenter(this)

        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
       pager = findViewById(R.id.pager) as ViewPager
        pagerAdapter = RecyclerViewPagerAdapter(supportFragmentManager)
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
        teachersList.setOnClickListener{
            pager?.setCurrentItem(2)
        }
        studentsList.setOnClickListener{
            pager?.setCurrentItem(1)
        }
        allUsersList.setOnClickListener{
            pager?.setCurrentItem(0)
        }
        super.onResume()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out_menu -> {
                mainPresenter.signOut()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun startStudentCreateActivity() {
        startActivity(Intent(this,StudentCreateActivity::class.java))
    }

    override fun startTeacherCreateAvtivity() {
        startActivity(Intent(this,TeacherCreateActivity::class.java))
    }

    override fun startAdminCreateActivity() {
        startActivity(Intent(this,AdminCreateActivity::class.java))
    }

    override fun startLoginActivity() {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }

    override fun showToast(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_LONG)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class RecyclerViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            Log.d("POSITION",position.toString())
            when(position){
                1 -> return StudentRecyclerFragment()
                2 -> return TeacherRecyclerFragment()
                0 -> return AdminRecyclerFragment()
            }

            return AdminRecyclerFragment()

        }

        override fun getCount(): Int {
            return MainActivity.PAGE_COUNT
        }
    }
}
