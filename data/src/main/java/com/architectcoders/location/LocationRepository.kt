package com.architectcoders.location

import com.architectcoders.source.local.LocationDataSource

class LocationRepository (
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker
    ) {
        companion object {
            private const val DEFAULT_REGION = "US"
        }

        suspend fun getLastLocation(): String {
            return if (permissionChecker.checkPermission(PermissionChecker.Permission.LOCATION)) {
                locationDataSource.getLastLocation() ?: DEFAULT_REGION
            } else {
                DEFAULT_REGION
            }
        }
}