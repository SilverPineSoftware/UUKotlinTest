package com.silverpine.uu.test

import android.os.Parcelable
import com.silverpine.uu.test.mocks.MockParcel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame

abstract class UUParcelableBaseTest<T : Parcelable>
{
    protected fun doTest(obj: T)
    {
        assertNotNull(obj)
        val copy = makeParcelableCopy(obj)
        assertNotNull(copy)
        doAssertEquals(obj, copy)
    }

    private fun <T : Parcelable> makeParcelableCopy(obj: T): T
    {
        val creator = creatorFor(obj)

        assertNotNull(obj)
        assertNotNull(creator)

        val p = MockParcel.obtain()
        obj.writeToParcel(p, 0)
        p.setDataPosition(0)

        val result = creator.createFromParcel(p)
        assertNotNull(result)
        return result
    }

    private fun <T : Parcelable> creatorFor(obj: T): Parcelable.Creator<T>
    {
        val fld = obj.javaClass.getField("CREATOR")

        @Suppress("UNCHECKED_CAST")
        return fld.get(null) as Parcelable.Creator<T>
    }

    private fun hasCustomEquals(clazz: Class<*>): Boolean
    {
        return try
        {
            val method = clazz.getDeclaredMethod("equals", Any::class.java)
            method.declaringClass != Any::class.java
        }
        catch (_: NoSuchMethodException)
        {
            false
        }
    }

    protected open fun doAssertEquals(lhs: T, rhs: T)
    {
        val clazz = lhs::class.java

        if (hasCustomEquals(clazz))
        {
            assertEquals(lhs, rhs)
        }
        else
        {
            assertSame("Parcelable did not preserve identity", lhs, rhs)
        }
    }
}