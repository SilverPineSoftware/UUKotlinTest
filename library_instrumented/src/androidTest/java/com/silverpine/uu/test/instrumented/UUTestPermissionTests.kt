package com.silverpine.uu.test.instrumented

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Assume.assumeTrue
import org.junit.Rule
import org.junit.Test

class UUTestPermissionTests
{
    @Rule @JvmField
    val rule = ActivityScenarioRule(UUTestActivity::class.java)

    @Test
    @SdkSuppress(minSdkVersion = 33)
    fun testGrantPermissions()
    {
        UUTestPermissions.grant(Manifest.permission.CAMERA)
        assertCameraGranted()
    }

    @Test
    @SdkSuppress(minSdkVersion = 33)
    fun testGrantPermissions_twice()
    {
        UUTestPermissions.grant(Manifest.permission.CAMERA)
        assertCameraGranted()
        UUTestPermissions.grant(Manifest.permission.CAMERA)
        assertCameraGranted()
    }

    /** Run in isolation using scripts/test_permission_transition.sh, which revokes before launch. */
    /*@Test
    @SdkSuppress(minSdkVersion = 33)
    fun testGrantPermissions_deniedToGranted()
    {
        assumeTrue(
            "Requires isolated pre-launch CAMERA revocation; use scripts/test_permission_transition.sh",
            InstrumentationRegistry.getArguments().getString("uuCameraInitiallyDenied") == "true"
        )
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            "CAMERA must be denied by the runner before instrumentation starts",
            PackageManager.PERMISSION_DENIED,
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        )
        UUTestPermissions.grant(Manifest.permission.CAMERA)
        assertCameraGranted()
    }*/

    private fun assertCameraGranted()
    {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            "CAMERA should be granted after grant()",
            PackageManager.PERMISSION_GRANTED,
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        )
    }

    @Test
    @SdkSuppress(minSdkVersion = 33)
    fun testGrantPermissions_notInManifest() = runBlocking()
    {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        rule.uuSetTitle("UUTestPermissions")
        rule.uuSetTestName("testGrantPermissions_notInManifest")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("Checking Permissions")
        Thread.sleep(2000)

        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
        
        // Verify permission is not in manifest
        assertTrue("Permission should not be declared in manifest for this test", 
            !UUTestPermissions.isPermissionDeclaredInManifest(permission))
        
        var granted = ContextCompat.checkSelfPermission(context, permission)
        assertEquals(PackageManager.PERMISSION_DENIED, granted)

        rule.uuAppendOutputLine("Granting permissions with UUTestPermissions (should fail)")
        Thread.sleep(2000)

        // Verify that grant() throws AssertionError with the expected message
        try
        {
            UUTestPermissions.grant(permission)
            fail("Expected AssertionError to be thrown when permission is not in manifest")
        }
        catch (e: AssertionError)
        {
            // Verify the error message contains the expected text
            assertNotNull("AssertionError message should not be null", e.message)
            assertTrue("AssertionError message should contain permission name and 'not declared in the manifest'",
                e.message!!.contains(permission) && 
                e.message!!.contains("not declared in the manifest"))
            
            rule.uuAppendOutputLine("Correctly caught AssertionError: ${e.message}")
        }

        rule.uuAppendOutputLine("Checking permissions (should still be denied)")
        Thread.sleep(2000)

        granted = ContextCompat.checkSelfPermission(context, permission)
        assertEquals(PackageManager.PERMISSION_DENIED, granted)

        rule.uuAppendOutputLine("All done")
        Thread.sleep(2000)
    }
}
