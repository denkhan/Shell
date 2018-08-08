package com.example.dlund.basicapp.commons

import android.content.Context
import android.preference.PreferenceManager
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import android.os.Bundle



class AdmobHelper{
    companion object {
        fun setPreference(private: Boolean, context: Context){
            PreferenceManager.getDefaultSharedPreferences(context)
                    .edit()
                    .putBoolean("preference", private)
                    .apply()
        }

        private fun getPreference(context: Context?): Boolean {
            return PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean("preference", false)
        }

        fun getAdRequest(context: Context?): AdRequest{
            val request = AdRequest.Builder()
            if(!getPreference(context)){
                val extras = Bundle()
                extras.putString("npa", "1")
                request.addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
            }
            return request.build()
        }
    }
}