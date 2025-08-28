package com.silverpine.uu.test.test

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.silverpine.uu.test.UUParcelableBaseTest
import kotlinx.parcelize.Parcelize
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


    override fun equals(other: Any?): Boolean
    {
        if (this === other) return true
        if (other !is MyParcelableObject) return false
        if (readOnlyPrimitive != other.readOnlyPrimitive) return false
        if (writablePrimitive != other.writablePrimitive) return false
        if (readOnlyString != other.readOnlyString) return false
        if (writableString != other.writableString) return false
        if (!constantBytes.contentEquals(other.constantBytes)) return false
        if (nullableConstant != other.nullableConstant) return false
        return true
    }

    override fun hashCode(): Int {
        var result = readOnlyPrimitive
        result = 31 * result + writablePrimitive.hashCode()
        result = 31 * result + readOnlyString.hashCode()
        result = 31 * result + writableString.hashCode()
        result = 31 * result + constantBytes.contentHashCode()
        result = 31 * result + (nullableConstant?.hashCode() ?: 0)
        return result
    }
}

@Keep
@Parcelize
class MyParcelizedObject(): Parcelable
{
    val readOnlyPrimitive: Int = 0
    var writablePrimitive: Long = 57L
    val readOnlyString: String = "1234"
    var writableString: String = "hello world"

    val constantBytes: ByteArray = ByteArray(4)

    val nullableConstant: Double? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MyParcelizedObject) return false
        if (readOnlyPrimitive != other.readOnlyPrimitive) return false
        if (writablePrimitive != other.writablePrimitive) return false
        if (readOnlyString != other.readOnlyString) return false
        if (writableString != other.writableString) return false
        if (!constantBytes.contentEquals(other.constantBytes)) return false
        if (nullableConstant != other.nullableConstant) return false
        return true
    }

    override fun hashCode(): Int {
        var result = readOnlyPrimitive
        result = 31 * result + writablePrimitive.hashCode()
        result = 31 * result + readOnlyString.hashCode()
        result = 31 * result + writableString.hashCode()
        result = 31 * result + constantBytes.contentHashCode()
        result = 31 * result + (nullableConstant?.hashCode() ?: 0)
        return result
    }
}


class UUParcelableTests: UUParcelableBaseTest<MyParcelableObject>()
{
    @Test
    fun test_0000_default()
    {
        doTest(MyParcelableObject())
    }
}

class UUParcelizedTests: UUParcelableBaseTest<MyParcelizedObject>()
{
    @Test
    fun test_0000_default()
    {
        doTest(MyParcelizedObject())
    }
}
