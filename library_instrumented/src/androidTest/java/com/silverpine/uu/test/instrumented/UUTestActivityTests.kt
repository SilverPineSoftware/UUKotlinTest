package com.silverpine.uu.test.instrumented

import android.Manifest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class UUTestActivityTests
{
    @Rule @JvmField
    val rule = ActivityScenarioRule(UUTestActivity::class.java)

    @Test
    fun testShowSomeOutput() = runBlocking()
    {
        rule.uuSetTitle("UU Unit Tests")
        rule.uuSetTestName("testShowSomeOutput")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("Performing some work")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("Waiting for something")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("And waiting for more")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("All done")
        Thread.sleep(2000)
    }

    @Test
    fun testRequestSomePermissions() = runBlocking()
    {
        rule.uuSetTitle("UU Unit Tests")
        rule.uuSetTestName("testRequestSomePermissions")

        rule.uuAppendOutputLine("Setting up a test")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("Requesting permissions")
        rule.uuRequestPermissions(arrayOf(
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT
        ))

        Thread.sleep(2000)

        rule.uuAppendOutputLine("Got permissions")
        Thread.sleep(2000)

        rule.uuAppendOutputLine("All done")
        Thread.sleep(2000)
    }
}