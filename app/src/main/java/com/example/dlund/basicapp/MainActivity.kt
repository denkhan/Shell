package com.example.dlund.basicapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.dlund.basicapp.R.id.fragment_layout
import com.example.dlund.basicapp.fragments.FragmentOne
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentList: List<Fragment>
    lateinit var fragment: Fragment
    private lateinit var GDPR: GDPR

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        fragment = when (item.itemId) {
            R.id.navigation_home -> fragmentList[0]
            R.id.navigation_dashboard -> fragmentList[1]
            R.id.navigation_notifications -> fragmentList[1]
            else -> fragmentList[1]
        }
        openFragment(fragment)

        true
    }

    fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(fragment_layout.id, fragment)
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentList = listOf(FragmentOne.newInstance("haha", "haha"),
                FragmentOne.newInstance("h","h"))//fragment1.newInstance()))
        fragment = fragmentList[0]
        openFragment(fragment)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // TODO: navigation.setOnNavigationItemReselectedListener()

        GDPR = GDPR(this, arrayOf("publisherID"))
        MobileAds.initialize(this, "ca-app-pub-7340600106400694~6330013678")

        GDPR.checkForConsent()
    }
}
