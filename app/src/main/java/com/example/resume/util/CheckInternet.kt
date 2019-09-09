package com.example.resume.util

import android.content.Context
import android.net.ConnectivityManager

class CheckInternet {


    companion object{
        public fun checkConnection(context: Context?): Boolean {
            val connMgr = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connMgr.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }

}