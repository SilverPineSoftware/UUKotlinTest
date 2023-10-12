package com.silverpine.uu.test

import androidx.annotation.VisibleForTesting
import java.nio.ByteBuffer
import java.security.SecureRandom

private val UPPER_CASE = Pair('A','Z')
private val LOWER_CASE = Pair('a','z')
private val NUMBERS = Pair('0','9')

@VisibleForTesting
fun uuRandomLetters(maxLength: Int): String
{
    return uuRandomChars(maxLength, arrayListOf(UPPER_CASE, LOWER_CASE))
}

@VisibleForTesting
fun uuRandomNumbers(maxLength: Int): String
{
    return uuRandomChars(maxLength, arrayListOf(NUMBERS))
}

@VisibleForTesting
fun uuRandomLettersOrNumbers(maxLength: Int): String
{
    return uuRandomChars(maxLength, arrayListOf(UPPER_CASE, LOWER_CASE, NUMBERS))
}

@VisibleForTesting
fun uuRandomChars(
    maxLength: Int,
    ranges: ArrayList<Pair<Char,Char>> = arrayListOf(Pair(Char.MIN_VALUE, Char.MAX_VALUE))): String
{
    val length = uuRandomInt(maxLength)

    val sb = StringBuilder()

    while (sb.length < length)
    {
        val c = uuRandomChar()

        for (i in 0 until ranges.size)
        {
            if (c in ranges[i].first .. ranges[i].second)
            {
                sb.append(c)
                break
            }
        }
    }

    return sb.toString()
}

@VisibleForTesting
fun uuRandomWord(maxLength: Int): String
{
    return uuRandomLetters(maxLength)
}

@VisibleForTesting
fun uuRandomWords(maxNumberOfWords: Int, maxWordLength: Int): String
{
    val sb = StringBuilder()
    val words = uuRandomInt(maxNumberOfWords)

    for (i in 0 until words)
    {
        sb.append(uuRandomWord(maxWordLength))
        sb.append(" ")
    }

    return sb.toString()
}

private val rando = SecureRandom()
fun uuRandomInt(max: Int): Int
{
    var max = max
    if (max < 1)
    {
        max = Byte.MAX_VALUE.toInt()
    }

    return rando.nextInt(max)
}

fun uuRandomBytes(count: Int): ByteArray
{
    val buffer = ByteArray(count)
    rando.nextBytes(buffer)
    return buffer
}

fun uuRandomChar(): Char
{
    val bb = ByteBuffer.wrap(uuRandomBytes(Short.SIZE_BYTES))
    return Char(bb.short.toUShort())
}