package com.silverpine.uu.test

import android.os.Parcel
import android.os.Parcelable

abstract class UUParcelableBaseTest<T : Parcelable>
{
    protected fun doTest(obj: T)
    {
        org.junit.Assert.assertNotNull(obj)
        val copy = makeParcelableCopy(obj, parcelableCreator)
        org.junit.Assert.assertNotNull(copy)
        assertEquals(obj, copy)
    }

    private fun <T : Parcelable> makeParcelableCopy(obj: T, creator: Parcelable.Creator<T>): T
    {
        org.junit.Assert.assertNotNull(obj)
        org.junit.Assert.assertNotNull(creator)
        val p = Parcel.obtain()
        obj.writeToParcel(p, 0)
        p.setDataPosition(0)
        val result = creator.createFromParcel(p)
        org.junit.Assert.assertNotNull(result)
        return result
    }

    protected abstract val parcelableCreator: Parcelable.Creator<T>

    protected abstract fun assertEquals(lhs: T, rhs: T)
}