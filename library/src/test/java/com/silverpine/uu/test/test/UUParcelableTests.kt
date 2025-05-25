package com.silverpine.uu.test.test

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.silverpine.uu.test.UUParcelableBaseTest
import org.junit.Assert
import org.junit.Test

@Keep
class MyParcelableObject(): Parcelable
{
    val readOnlyPrimitive: Int = 0
    var writablePrimitive: Long = 57L
    val readOnlyString: String = "1234"
    var writableString: String = "hello world"

    val constantBytes: ByteArray = ByteArray(4)

    val nullableConstant: Double? = null

    override fun describeContents(): Int
    {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int)
    {
        p0.writeLong(writablePrimitive)
        p0.writeString(writableString)
        p0.writeBlob(constantBytes)
    }

    private constructor(parcel: Parcel): this()
    {
        writablePrimitive = parcel.readLong()
        writableString = parcel.readString() ?: ""

        parcel.readByteArray(constantBytes)
    }

    companion object
    {
        @JvmField
        val CREATOR = object : Parcelable.Creator<MyParcelableObject>
        {
            override fun createFromParcel(p: Parcel): MyParcelableObject?
            {
                return MyParcelableObject(p)
            }

            override fun newArray(size: Int) = arrayOfNulls<MyParcelableObject?>(size)
        }
    }
}


class UUParcelableTests: UUParcelableBaseTest<MyParcelableObject>()
{
    override val parcelableCreator: Parcelable.Creator<MyParcelableObject>
        get() = MyParcelableObject.CREATOR

    override fun assertEquals(lhs: MyParcelableObject, rhs: MyParcelableObject)
    {
        Assert.assertEquals(lhs.readOnlyPrimitive, rhs.readOnlyPrimitive)
        Assert.assertEquals(lhs.writablePrimitive, rhs.writablePrimitive)
        Assert.assertEquals(lhs.readOnlyString, rhs.readOnlyString)
        Assert.assertEquals(lhs.writableString, rhs.writableString)
        Assert.assertArrayEquals(lhs.constantBytes, rhs.constantBytes)
        Assert.assertEquals(lhs.nullableConstant, rhs.nullableConstant)
    }

    @Test
    fun test_0000_default()
    {
        doTest(MyParcelableObject())
    }
}
