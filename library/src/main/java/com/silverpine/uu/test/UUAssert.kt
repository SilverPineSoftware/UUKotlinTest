package com.silverpine.uu.test

import androidx.lifecycle.LiveData
import org.junit.Assert

object UUAssert
{
    fun assertSameNullness(lhs: Any?, rhs: Any?)
    {
        Assert.assertTrue(areSameNullness(lhs, rhs))
    }

    fun areSameNullness(lhs: Any?, rhs: Any?): Boolean
    {
        return lhs == null && rhs == null || lhs != null && rhs != null
    }

    fun <T> assertEquals(expected: T, liveData: LiveData<T>)
    {
        uuDispatchMainAndWait()
        {
            Assert.assertEquals(expected, liveData.value)
        }
    }
}

fun <T> Assert.uuUnwrap(obj: T?, msg: String = ""): T
{
    Assert.assertNotNull(msg, obj)
    return obj!!
}