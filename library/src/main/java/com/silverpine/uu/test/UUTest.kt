package com.silverpine.uu.test

import android.os.Handler
import android.os.Looper
import java.util.concurrent.CountDownLatch

fun uuDispatchMainAndWait(block: ()->Unit)
{
    val latch = CountDownLatch(1)

    Handler(Looper.getMainLooper()).post()
    {
        block.invoke()
        latch.countDown()
    }

    latch.await()
}