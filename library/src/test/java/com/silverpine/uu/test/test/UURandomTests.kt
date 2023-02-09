package com.silverpine.uu.test.test

import com.silverpine.uu.core.UURandom
import com.silverpine.uu.test.*
import org.junit.Assert.assertNotNull
import org.junit.Test

class UURandomTests
{
    private val kLoops = 100

    @Test
    fun test_randomLetters()
    {
        for (i in 0 until kLoops)
        {
            val length = UURandom.uByte().toInt()
            val actual = UURandom.letters(length)
            assertNotNull(actual)
            println("UURandom.letters: $actual")
        }
    }

    @Test
    fun test_randomNumbers()
    {
        for (i in 0 until kLoops)
        {
            val length = UURandom.uByte().toInt()
            val actual = UURandom.numbers(length)
            assertNotNull(actual)
            println("UURandom.numbers: $actual")
        }
    }

    @Test
    fun test_randomLettersOrNumbers()
    {
        for (i in 0 until kLoops)
        {
            val length = UURandom.uByte().toInt()
            val actual = UURandom.lettersOrNumbers(length)
            assertNotNull(actual)
            println("UURandom.lettersOrNumbers: $actual")
        }
    }

    @Test
    fun test_randomWord()
    {
        for (i in 0 until kLoops)
        {
            val length = UURandom.uByte().toInt()
            val actual = UURandom.word(length)
            assertNotNull(actual)
            println("UURandom.word: $actual")
        }
    }

    @Test
    fun test_randomWords()
    {
        for (i in 0 until kLoops)
        {
            val actual = UURandom.words(10, 20)
            assertNotNull(actual)
            println("UURandom.words: $actual")
        }
    }
}