package com.example.creativemindstask

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class MyApp : Application() {

    fun hasNetwork(): Boolean {
        return app!!.isNetworkConnected()
    }

    companion object {
        private var app: MyApp? = null

        fun getInstance(): MyApp? {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (app == null) {
            app = this
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities.also {
            if (it != null) {
                if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                    return true
                else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                }
            }
        }
        return false
    }
}