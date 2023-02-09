package com.silverpine.uu.test.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.silverpine.uu.test.UUAssert
import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UUAssertTests
{
    @Test
    fun test_areSameNullness()
    {
        // noinspection ConstantConditions
        Assert.assertTrue(UUAssert.areSameNullness(null, null))

        // noinspection ConstantConditions
        Assert.assertTrue(UUAssert.areSameNullness("foo", "bar"))

        // noinspection ConstantConditions
        Assert.assertFalse(UUAssert.areSameNullness(null, 1L))

        // noinspection ConstantConditions
        Assert.assertFalse(UUAssert.areSameNullness(2L, null))
    }
}