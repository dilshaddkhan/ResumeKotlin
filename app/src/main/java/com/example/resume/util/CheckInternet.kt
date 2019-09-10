package com.example.resume.util

import android.content.Context
import android.net.ConnectivityManager


/**
 * Check internet class
 */
class CheckInternet {


    companion object{

        // this method is used to check the internet the connection that the device is connected with the internet or not
        public fun checkConnection(context: Context?): Boolean {
            val connMgr = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }

}