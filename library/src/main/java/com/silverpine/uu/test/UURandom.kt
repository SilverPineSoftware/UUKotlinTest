package com.silverpine.uu.test

import androidx.annotation.VisibleForTesting
import com.silverpine.uu.core.UURandom

@VisibleForTesting
fun UURandom.letters(maxLength: Int): String
{
    val length = int(maxLength)

    val sb = StringBuilder()

    while (sb.length < length)
    {
        val c = char()

        if (c in 'A'..'Z' || c in 'a'..'z')
        {
            sb.append(c)
        }
    }

    return sb.toString()
}

@VisibleForTesting
fun UURandom.string(maxLength: Int): String
{
    return String(charArray(maxLength))
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