package com.silverpine.uu.test

import android.Manifest
import android.os.Build
import androidx.test.platform.app.InstrumentationRegistry

object UUTestPermissions
{
    fun grant(permission: String)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
        {
            val ctx = InstrumentationRegistry.getInstrumentation().targetContext
            val pkg = ctx.packageName
            val ua = InstrumentationRegistry.getInstrumentation().uiAutomation
            ua.grantRuntimePermission(pkg, permission)
        }
    }

    fun grantBlePermissions()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        {
            grant(Manifest.permission.BLUETOOTH_SCAN)
            grant(Manifest.permission.BLUETOOTH_CONNECT)
            grant(Manifest.permission.BLUETOOTH_ADVERTISE)
        }
        else
        {
            grant(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }
}

