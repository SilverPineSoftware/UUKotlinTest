package com.silverpine.uu.test.instrumented

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue

object UUTestPermissions
{
    /**
     * Checks if a permission is declared in the app's manifest.
     *
     * @param permission The permission name to check
     * @return true if the permission is declared in the manifest, false otherwise
     */
    fun isPermissionDeclaredInManifest(permission: String): Boolean
    {
        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val pkg = ctx.packageName
        val pm = ctx.packageManager

        return try
        {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            {
                @Suppress("DEPRECATION")
                val flags = PackageManager.GET_PERMISSIONS.toLong()
                pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(flags))
            }
            else
            {
                @Suppress("DEPRECATION")
                pm.getPackageInfo(pkg, PackageManager.GET_PERMISSIONS)
            }

            packageInfo.requestedPermissions?.contains(permission) == true
        }
        catch (e: Exception)
        {
            false
        }
    }

    /**
     * Determines if a permission is denied because it's not declared in the manifest.
     *
     * This is useful for debugging - if a permission is denied and not in the manifest,
     * it will always return PERMISSION_DENIED regardless of user action.
     *
     * @param permission The permission name to check
     * @return true if the permission is denied AND not declared in manifest, false otherwise
     */
    fun isDeniedBecauseNotInManifest(permission: String): Boolean
    {
        val isDeclared = isPermissionDeclaredInManifest(permission)
        if (isDeclared)
        {
            return false
        }

        val ctx = InstrumentationRegistry.getInstrumentation().targetContext
        val status = ContextCompat.checkSelfPermission(ctx, permission)
        return status == PackageManager.PERMISSION_DENIED
    }

    fun grant(permission: String)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
        {
            // Fail here if not in the manifest
            assertTrue("Permission $permission is not declared in the manifest", isPermissionDeclaredInManifest(permission))

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