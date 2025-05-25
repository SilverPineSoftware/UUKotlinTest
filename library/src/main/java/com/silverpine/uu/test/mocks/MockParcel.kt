package com.silverpine.uu.test.mocks

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyByte
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyFloat
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`


@SuppressLint("ParcelClassLoader")
open class MockParcel
{
    val mockedObject: Parcel = Mockito.mock(Parcel::class.java)
    val objects: MutableList<Any?> = ArrayList()
    var position = 0

    init
    {
        // String
        `when`(mockedObject.writeString(anyString())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readString()).thenAnswer()
        {
            objects[position++] as String
        }

        // Byte
        `when`(mockedObject.writeByte(anyByte())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readByte()).thenAnswer()
        {
            objects[position++] as Byte
        }

        // Int
        `when`(mockedObject.writeInt(anyInt())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readInt()).thenAnswer()
        {
            objects[position++] as Int
        }

        // Long
        `when`(mockedObject.writeLong(anyLong())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readLong()).thenAnswer()
        {
            objects[position++] as Long
        }

        // Float
        `when`(mockedObject.writeFloat(anyFloat())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readFloat()).thenAnswer()
        {
            objects[position++] as Float
        }

        // Double
        `when`(mockedObject.writeDouble(anyDouble())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readDouble()).thenAnswer()
        {
            objects[position++] as Double
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {
            // Blob
            `when`(mockedObject.writeBlob(any())).thenAnswer()
            { invocation ->
                objects.add(invocation.getArgument(0))
            }

            `when`(mockedObject.readBlob()).thenAnswer()
            {
                objects[position++] as ByteArray
            }

            // Blob
            `when`(mockedObject.writeBlob(any(), anyInt(), anyInt())).thenAnswer()
            { invocation ->
                val blob = invocation.getArgument<ByteArray>(0)
                val offset = invocation.getArgument<Int>(1)
                val len = invocation.getArgument<Int>(2)
                val chunk = blob.sliceArray(offset..len)
                objects.add(chunk)
            }

            `when`(mockedObject.readBlob()).thenAnswer()
            {
                objects[position++] as ByteArray
            }
        }

        // Bundle
        `when`(mockedObject.writeBundle(any())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readBundle()).thenAnswer()
        {
            objects[position++] as Bundle
        }

        // Serializable
        `when`(mockedObject.writeSerializable(any())).thenAnswer()
        { invocation ->
            objects.add(invocation.getArgument(0))
        }

        `when`(mockedObject.readSerializable()).thenAnswer()
        {
            objects[position++]
        }
    }
    companion object
    {
        fun obtain(): Parcel
        {
            return MockParcel().mockedObject
        }
    }
}