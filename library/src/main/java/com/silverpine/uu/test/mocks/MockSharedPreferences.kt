package com.silverpine.uu.test.mocks

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.core.content.edit
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

@SuppressLint("UseKtx")
open class MockSharedPreferences
{
    val mockedObject: SharedPreferences = Mockito.mock(SharedPreferences::class.java)
    val mockedEditor: SharedPreferences.Editor = Mockito.mock(SharedPreferences.Editor::class.java)

    private val data: HashMap<String, Any?> = hashMapOf()

    init
    {
        Mockito.`when`(mockedObject.getString(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenAnswer()
        {
            data[it.getArgument(0)] ?: it.getArgument(1)
        }

        Mockito.`when`(mockedObject.edit()).thenAnswer()
        {
            mockedEditor
        }

        Mockito.`when`(mockedEditor.putString(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenAnswer()
        {
            val key = it.arguments.getOrNull(0)
            val value = it.arguments.getOrNull(1)
            if (key != null && key is String)
            {
                data[key] = value
            }
            mockedEditor
        }

        Mockito.`when`(mockedEditor.commit()).thenAnswer { true }
    }
}