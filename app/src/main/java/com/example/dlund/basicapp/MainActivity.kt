package com.example.dlund.basicapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.dlund.basicapp.fragments.FragmentOne
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentList: List<Fragment>

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                FragmentOne.newInstance("haha", "haha")
                supportFragmentManager.beginTransaction()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

//    supportFragmentManager.beginTransaction()
//            .replace(fragment_layout.id, fragment)
//            .commit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentList = listOf(FragmentOne.newInstance("haha", "haha"))//fragment1.newInstance()))

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
