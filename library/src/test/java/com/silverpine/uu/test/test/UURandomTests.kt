package com.silverpine.uu.test.test

import com.silverpine.uu.test.uuRandomInt
import com.silverpine.uu.test.uuRandomLetters
import com.silverpine.uu.test.uuRandomLettersOrNumbers
import com.silverpine.uu.test.uuRandomNumbers
import com.silverpine.uu.test.uuRandomWord
import com.silverpine.uu.test.uuRandomWords
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
            val length = uuRandomInt(255)
            val actual = uuRandomLetters(length)
            assertNotNull(actual)
            println("UURandom.letters: $actual")
        }
    }

    @Test
    fun test_randomNumbers()
    {
        for (i in 0 until kLoops)
        {
            val length = uuRandomInt(255)
            val actual = uuRandomNumbers(length)
            assertNotNull(actual)
            println("UURandom.numbers: $actual")
        }
    }

    @Test
    fun test_randomLettersOrNumbers()
    {
        for (i in 0 until kLoops)
        {
            val length = uuRandomInt(255)
            val actual = uuRandomLettersOrNumbers(length)
            assertNotNull(actual)
            println("UURandom.lettersOrNumbers: $actual")
        }
    }

    @Test
    fun test_randomWord()
    {
        for (i in 0 until kLoops)
        {
            val length = uuRandomInt(255)
            val actual = uuRandomWord(length)
            assertNotNull(actual)
            println("UURandom.word: $actual")
        }
    }

    @Test
    fun test_randomWords()
    {
        for (i in 0 until kLoops)
        {
            val actual = uuRandomWords(10, 20)
            assertNotNull(actual)
            println("UURandom.words: $actual")
        }
    }
}