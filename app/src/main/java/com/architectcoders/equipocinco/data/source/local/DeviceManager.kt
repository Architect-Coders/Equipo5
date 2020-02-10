@file:JvmName("ConnectivityHelper")

package com.architectcoders.equipocinco.data.source.local

import android.content.Context
import android.net.ConnectivityManager
import com.architectcoders.source.local.DeviceSource


class DeviceManager(private val applicationContext: Context): DeviceSource {

    override fun isConnectedToTheInternet(): Boolean {
        return applicationContext.isConnectedToNetwork()
    }
}

fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
}