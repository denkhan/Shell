package com.example.dlund.basicapp

import android.util.Log
import com.example.dlund.basicapp.commons.AdmobHelper
import com.google.ads.consent.*
import java.net.MalformedURLException
import java.net.URL

class GDPR(val activity: MainActivity, val publisherIds: Array<String>){
    private val TAG = "GDPR"
    private var form: ConsentForm? = null

    fun checkForConsent() {
        val consentInformation = ConsentInformation.getInstance(activity)
        consentInformation.requestConsentInfoUpdate(publisherIds, object: ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                // User's consent status successfully updated.
                when (consentStatus) {
                    ConsentStatus.PERSONALIZED -> {
                        Log.d(TAG, "Showing Personalized ads")
                        //TODO showPersonalizedAds()
                    }
                    ConsentStatus.NON_PERSONALIZED -> {
                        Log.d(TAG, "Showing Non-Personalized ads")
                        //TODO showNonPersonalizedAds();
                    }
                    ConsentStatus.UNKNOWN -> {
                        Log.d(TAG, "Requesting Consent")
                        if (ConsentInformation.getInstance(activity)
                                        .isRequestLocationInEeaOrUnknown) {
                            requestConsent()
                        } else {
                            //TODO showPersonalizedAds()
                        }
                    }
                    else -> return
                }
            }

            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                // User's consent status failed to update.
            }
        })
    }

    fun requestConsent() {
        var privacyUrl: URL? = null
        try {
            privacyUrl = URL("https://pastebin.com/6ZbqywdF")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        form = ConsentForm.Builder(activity, privacyUrl)
                .withListener(object: ConsentFormListener() {
                    override fun onConsentFormLoaded() { // Consent form loaded successfully.
                        Log.d(TAG, "Requesting Consent: onConsentFormLoaded")
                        showForm()
                    }
                    override fun onConsentFormOpened() { // Consent form was displayed.
                        Log.d(TAG, "Requesting Consent: onConsentFormOpened")
                    }
                    override fun onConsentFormClosed(consentStatus: ConsentStatus,
                                                     userPrefersAdFree: Boolean) {
                        Log.d(TAG, "Requesting Consent: onConsentFormClosed")
                        if (false){     //userPrefersAdFree) { // Buy or Subscribe
                            Log.d(TAG, "Requesting Consent: User prefers AdFree")
                        } else {
                            Log.d(TAG, "Requesting Consent: Requesting consent again")
                            return when (consentStatus) {
                                ConsentStatus.PERSONALIZED ->
                                    AdmobHelper.setPreference(true, activity)
                                ConsentStatus.NON_PERSONALIZED ->
                                    AdmobHelper.setPreference(false, activity)
                                ConsentStatus.UNKNOWN ->
                                    AdmobHelper.setPreference(false, activity)
                            }
                        }
                    }

                    override fun onConsentFormError(errorDescription: String) {
                        Log.d(TAG, "Requesting Consent: onConsentFormError. $errorDescription")
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                //.withAdFreeOption()
                .build()
        form?.load()
    }

    private fun showForm() {
        Log.d(TAG, "Showing consent form (consentform = $form)")
        form?.show()
    }
}

