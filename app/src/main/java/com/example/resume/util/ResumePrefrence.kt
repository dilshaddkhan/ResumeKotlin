package com.example.resume.util

import android.content.Context
import android.content.SharedPreferences
import com.example.resume.R

class ResumePreference(private val context: Context) {

    private val preferenceName = context.getString(R.string.resume_preference)
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)


        //this function is used to set the verified used
    fun setVerifiedUser(keyValue: String, status: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(keyValue, status!!)
        editor.commit()
    }

    //this method is used to check that the user is verified or not
    fun checkVerifiedUser(keyValue: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(keyValue, defaultValue)

    }
}