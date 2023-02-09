package com.silverpine.uu.test

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
}