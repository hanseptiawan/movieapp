package com.hanif.nexmedistest.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


/**
 *Created by Ahmad Hanif S on 26/08/22
 */
class DateUtils @Inject constructor() {

    companion object {
        const val DATE_NUMBERS_ONLY = "dd/MM/yyyy"
        const val DATE_SHORT_MONTH = "dd MMM yyyy, HH:mm"
    }

    fun getDateWithFormat(format: String): String{
        val date=System.currentTimeMillis() //here the date comes in 13 digits
        val dtlong = Date(date)
        return SimpleDateFormat(format, Locale.getDefault()).format(dtlong)
    }

    fun changeDateFormat(
        currentFormat: String,
        requiredFormat: String,
        dateString: String
    ): String {
        var result = ""
        if (dateString.isEmpty()) {
            return result
        }
        val formatterOld = SimpleDateFormat(currentFormat, Locale.getDefault())
        val formatterNew = SimpleDateFormat(requiredFormat, Locale("en", "US"))
        var date: Date? = null
        try {
            date = formatterOld.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        if (date != null) {
            result = formatterNew.format(date)
        }
        return result
    }

}