package com.hanif.nexmedistest.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 *Created by Ahmad Hanif S on 05/04/22
 */
class SystemUIUtils(var activity: Activity) {

    fun hideKeyboard() {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.window.decorView.rootView.windowToken,
            0
        )
    }

}