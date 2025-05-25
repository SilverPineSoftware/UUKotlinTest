package com.silverpine.uu.test.mocks

import android.content.Context
import org.mockito.Mockito

open class MockContext
{
    val mockedObject: Context = Mockito.mock(Context::class.java)

    init
    {
        Mockito.`when`(mockedObject.applicationContext).thenAnswer()
        {
            mockedObject
        }
    }
}