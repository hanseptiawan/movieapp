package com.hanif.nexmedistest.data.pref

import android.content.SharedPreferences
import javax.inject.Inject

class PrefManager @Inject constructor(sharedPref: SharedPreferences) {

    private var pref: SharedPreferences = sharedPref
    private var editor: SharedPreferences.Editor = pref.edit()

    companion object {
        const val is_offline_mode = "is_offline_mode"
        const val image_base_url = "image_base_url"
        const val image_poster_size = "image_poster_size"
    }

    var imagePosterSize: String
        get() {
            return pref.getString(image_poster_size, "").toString()
        }
        set(value) {
            editor.putString(image_poster_size, value)
            editor.apply()
        }

    var imageBaseUrl: String
        get() {
            return pref.getString(image_base_url, "").toString()
        }
        set(value) {
            editor.putString(image_base_url, value)
            editor.apply()
        }

    var isOffline: Boolean
        get() {
            return pref.getBoolean(is_offline_mode, false)
        }
        set(value) {
            editor.putBoolean(is_offline_mode, value)
            editor.apply()
        }

}