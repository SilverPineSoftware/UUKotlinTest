package com.silverpine.uu.test.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.test.ext.junit.rules.ActivityScenarioRule
import java.util.concurrent.CountDownLatch

open class UUBaseTestActivity : AppCompatActivity()
{
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var testNameLabel: AppCompatTextView
    private lateinit var label: AppCompatTextView

    var permissionsCompletion: ((Map<String,Boolean>)->Unit) = { }

    private val permissionLauncher = registerForActivityResult(RequestMultiplePermissions())
    { permissions ->
        permissionsCompletion(permissions)
    }

    internal fun requestPermissions(permissions: Array<String>, completion: (Map<String,Boolean>)->Unit)
    {
        permissionsCompletion = completion
        permissionLauncher.launch(permissions)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val rootLayoutId = View.generateViewId()
        val testNameLabelId = View.generateViewId()
        val labelId = View.generateViewId()

        rootLayout = ConstraintLayout(this)
        rootLayout.id = rootLayoutId
        rootLayout.setBackgroundColor(Color.argb(255, 123, 123, 123))
        rootLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        rootLayout.fitsSystemWindows = true

        testNameLabel = AppCompatTextView(this)
        testNameLabel.id = testNameLabelId
        testNameLabel.setTextColor(Color.argb(255, 0, 0, 0))
        testNameLabel.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        testNameLabel.textAlignment = AppCompatTextView.TEXT_ALIGNMENT_CENTER
        testNameLabel.setTypeface(testNameLabel.typeface, Typeface.BOLD)
        testNameLabel.textSize = 24.0f

        label = AppCompatTextView(this)
        label.id = labelId
        label.setTextColor(Color.argb(255, 0, 0, 0))
        label.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,
            ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
        )
        label.textSize = 18.0f
        label.movementMethod = ScrollingMovementMethod()

        rootLayout.addView(testNameLabel)
        rootLayout.addView(label)
        setContentView(rootLayout)

        val parentId = ConstraintSet.PARENT_ID

        val constraintSet = ConstraintSet()
        constraintSet.clone(rootLayout)

        constraintSet.connect(testNameLabelId, ConstraintSet.TOP, parentId, ConstraintSet.TOP)
        constraintSet.connect(testNameLabelId, ConstraintSet.START, parentId, ConstraintSet.START)
        constraintSet.connect(testNameLabelId, ConstraintSet.END, parentId, ConstraintSet.END)

        constraintSet.connect(labelId, ConstraintSet.START, parentId, ConstraintSet.START)
        constraintSet.connect(labelId, ConstraintSet.END, parentId, ConstraintSet.END)
        constraintSet.connect(labelId, ConstraintSet.BOTTOM, parentId, ConstraintSet.BOTTOM)

        constraintSet.connect(labelId, ConstraintSet.TOP, testNameLabelId, ConstraintSet.BOTTOM)
        constraintSet.connect(testNameLabelId, ConstraintSet.BOTTOM, labelId, ConstraintSet.TOP)

        constraintSet.setMargin(testNameLabelId, ConstraintSet.TOP, 30)
        constraintSet.setMargin(testNameLabelId, ConstraintSet.BOTTOM, 30)
        constraintSet.setMargin(testNameLabelId, ConstraintSet.START, 30)
        constraintSet.setMargin(testNameLabelId, ConstraintSet.END, 30)

        constraintSet.setMargin(labelId, ConstraintSet.BOTTOM, 30)
        constraintSet.setMargin(labelId, ConstraintSet.START, 30)
        constraintSet.setMargin(labelId, ConstraintSet.END, 30)

        constraintSet.applyTo(rootLayout)

        testNameLabel.text = "Test Name"
        label.text = ""

        title = "Unit Tests"
    }

    fun setTestName(testName: String)
    {
        testNameLabel.text = testName
    }

    @SuppressLint("SetTextI18n")
    fun appendLine(line: String)
    {
        val current = label.text.toString()
        label.text = "${current}\n${line}".trimIndent()

        label.dkScrollToBottom()
    }

    fun TextView.dkScrollToBottom()
    {
        runOnUiThread()
        {
            layout?.let()
            {
                val yScroll = (it.getLineTop(lineCount) - height).coerceAtLeast(0)
                scrollTo(0, yScroll)
            }
        }
    }
}

fun <T: UUBaseTestActivity> ActivityScenarioRule<T>.uuSetTitle(title: String)
{
    scenario.onActivity { a -> a.title = title }
}

fun <T: UUBaseTestActivity> ActivityScenarioRule<T>.uuSetTestName(name: String)
{
    scenario.onActivity { a -> a.setTestName(name) }
}

fun <T: UUBaseTestActivity> ActivityScenarioRule<T>.uuAppendOutputLine(line: String)
{
    scenario.onActivity { a -> a.appendLine(line) }
}

fun <T: UUBaseTestActivity> ActivityScenarioRule<T>.uuRequestPermissions(permissions: Array<String>): Map<String,Boolean>
{
    val latch = CountDownLatch(1)

    var result: Map<String,Boolean> = mapOf()

    scenario.onActivity()
    { a ->
        a.requestPermissions(permissions)
        { r ->
            result = r
            latch.countDown()
        }
    }

    latch.await()

    return result
}