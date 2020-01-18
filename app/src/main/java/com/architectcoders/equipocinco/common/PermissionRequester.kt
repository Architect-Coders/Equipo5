package com.architectcoders.equipocinco.common

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener

class PermissionRequester(private val activity: FragmentActivity?, private val permission: String) {

    fun request(continuation: (Boolean) -> Unit) {
        activity?.let {
            Dexter
                .withActivity(activity)
                .withPermission(permission)
                .withListener(object : BasePermissionListener() {
                    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                        continuation(true)
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                        continuation(false)
                    }
                }
                ).check()
        }
    }

}