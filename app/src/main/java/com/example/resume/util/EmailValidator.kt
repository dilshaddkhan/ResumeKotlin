package com.example.resume.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class EmailValidator {

    companion object {
        private val emailPattern = ("^[_A-Za-z0-9-]+(\\." + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*"
                + "(\\.[A-Za-z]{2,})$")
        private var pattern: Pattern? = null
        private var matcher: Matcher? = null


        fun validateEmail(mailId: String): Boolean {
            pattern = Pattern.compile(emailPattern)
            matcher = pattern!!.matcher(mailId)
            return if (matcher!!.matches()) {
                true
            } else {
                false
            }
        }
    }
}