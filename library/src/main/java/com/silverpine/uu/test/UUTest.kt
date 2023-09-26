package com.silverpine.uu.test

import com.silverpine.uu.core.uuDispatchMain
import java.util.concurrent.CountDownLatch

fun uuDispatchMainAndWait(block: ()->Unit)
{
    val latch = CountDownLatch(1)

    uuDispatchMain()
    {
        block.invoke()
        latch.countDown()
    }

    latch.await()
}