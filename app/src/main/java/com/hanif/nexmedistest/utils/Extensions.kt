package com.hanif.nexmedistest.utils

object Extensions {

    fun convertStringToArrayInt(input: String): List<Int> {
        if (input.isNotEmpty()) {
            val convertedList = input.removeSurrounding("[", "]")
                .split(",")
                .map { it.trim().toInt() }
                .toIntArray()
            return convertedList.toList()
        } else {
            return listOf()
        }
    }

    fun convertStringToArrayString(input: String): Array<String> {
        return input.removeSurrounding("[", "]")
            .split(",")
            .map { it.trim().removeSurrounding("\"") }
            .toTypedArray()
    }
}