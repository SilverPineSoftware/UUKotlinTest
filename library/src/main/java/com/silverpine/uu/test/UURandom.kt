package com.silverpine.uu.test

import androidx.annotation.VisibleForTesting
import com.silverpine.uu.core.UURandom

private val UPPER_CASE = Pair('A','Z')
private val LOWER_CASE = Pair('a','z')
private val NUMBERS = Pair('0','9')

@VisibleForTesting
fun UURandom.letters(maxLength: Int): String
{
    return chars(maxLength, arrayListOf(UPPER_CASE, LOWER_CASE))
}

@VisibleForTesting
fun UURandom.numbers(maxLength: Int): String
{
    return chars(maxLength, arrayListOf(NUMBERS))
}

@VisibleForTesting
fun UURandom.lettersOrNumbers(maxLength: Int): String
{
    return chars(maxLength, arrayListOf(UPPER_CASE, LOWER_CASE, NUMBERS))
}

@VisibleForTesting
fun UURandom.chars(
    maxLength: Int,
    ranges: ArrayList<Pair<Char,Char>> = arrayListOf(Pair(Char.MIN_VALUE, Char.MAX_VALUE))): String
{
    val length = int(maxLength)

    val sb = StringBuilder()

    while (sb.length < length)
    {
        val c = char()

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
fun UURandom.word(maxLength: Int): String
{
    return letters(maxLength)
}

@VisibleForTesting
fun UURandom.words(maxNumberOfWords: Int, maxWordLength: Int): String
{
    val sb = StringBuilder()
    val words = int(maxNumberOfWords)

    for (i in 0 until words)
    {
        sb.append(word(maxWordLength))
        sb.append(" ")
    }

    return sb.toString()
}