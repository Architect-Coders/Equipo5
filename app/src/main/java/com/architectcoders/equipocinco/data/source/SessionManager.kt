@file:JvmName("ConnectivityHelper")

package com.architectcoders.equipocinco.data.source

import android.content.Context
import android.net.ConnectivityManager


class SessionManager(private val applicationContext: Context) {

    fun isConnectedToTheInternet(): Boolean {
        return applicationContext.isConnectedToNetwork()
    }
}


fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}