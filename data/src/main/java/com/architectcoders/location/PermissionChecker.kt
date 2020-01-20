package com.architectcoders.location

interface PermissionChecker {

    enum class Permission { LOCATION }

    suspend fun checkPermission(permission : Permission) : Boolean
}