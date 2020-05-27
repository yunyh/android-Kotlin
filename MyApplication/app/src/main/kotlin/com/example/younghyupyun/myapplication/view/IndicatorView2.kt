package com.example.younghyupyun.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.util.concurrent.atomic.AtomicInteger

class IndicatorView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    init {

    }


}

class DotsManager(
    count: Int,
    private val dotSize: Int,
    private val dotSpacing: Int,
    private val dotBound: Int,
    private val scrollListener: ScrollListener
) {
    companion object {
        const val SIZE_THRESHOLD = 6
    }

    val dots = ByteArray(count)

    private val selectedIndex = AtomicInteger(0)
    private val selectedPos
        get() = selectedIndex.get()

    private var scrollAmount = 0

    init {
        if (count > 6) {
            dots[0] = 6
        }
        if (count <= SIZE_THRESHOLD) {
            (1 until count).forEach { dots[it] = 5 }
        } else {
            (1..3).forEach { dots[it] = 5 }
            dots[4] = 4
            if (count > SIZE_THRESHOLD) {
                dots[5] = 2
            }

            (SIZE_THRESHOLD + 1 until count).forEach { dots[it] = 0 }
        }
    }


    fun goNext() {
        if (selectedPos >= dots.size - 1) {
            return
        }
        selectedIndex.incrementAndGet()

        if (dots.size <= SIZE_THRESHOLD) {
            goToNext()
        } else {
            goToNextLarge()
        }
    }


    private fun swapNext() {
        dots[selectedPos] = 6
        dots[selectedPos + 1] = 5
    }

    private fun goToNext() {
        swapNext()
    }

    private fun goToNextLarge() {

        var needScroll = false
        swapNext()
        if (selectedPos > 3
            && dots[selectedPos - 1] == 5.toByte()
            && dots[selectedPos - 2] == 5.toByte()
            && dots[selectedPos - 3] == 5.toByte()
            && dots[selectedPos - 4] == 5.toByte()
        ) {
            dots[selectedPos - 4] = 4.toByte()
            needScroll = true
            if (selectedPos - 5 >= SIZE_THRESHOLD) {
                dots[selectedPos - 5] = 2.toByte()
                (selectedPos - 6 downTo 0)
                    .takeWhile { dots[it] != 0.toByte() }
                    .forEach { dots[it] = 0.toByte() }
            }
        }

        if (selectedPos + 1 < dots.size && dots[selectedPos + 1] < 3) {
            dots[selectedPos + 1] = 3
            needScroll = true

            if (selectedPos + 2 < dots.size && dots[selectedPos + 2] < 1) {
                dots[selectedPos + 2] = 1
            }
        }

        if (needScroll) {
            val endBound = selectedPos * (dotSize + dotSpacing) + dotSize
            if (endBound > dotBound) {
                scrollAmount = endBound - dotBound
                scrollListener.scrollToTarget(scrollAmount)
            }
        }
    }

    fun goPrivious() {
        if (selectedPos == 0) {
            return
        }
        selectedIndex.decrementAndGet()
        if (dots.size <= SIZE_THRESHOLD) {
            goToPrevious()
        } else {
            goToPreviousLarge()
        }
    }

    private fun swapPrevious() {
        dots[selectedPos] = 6
        dots[selectedPos - 1] = 5
    }

    private fun goToPrevious() {
        swapPrevious()
    }

    private fun goToPreviousLarge() {
        var needScroll = false
        swapPrevious()

        if (selectedPos < dots.size - 4
            && dots[selectedPos + 1] == 5.toByte()
            && dots[selectedPos + 2] == 5.toByte()
            && dots[selectedPos + 3] == 5.toByte()
            && dots[selectedPos + 4] == 5.toByte()
        ) {
            dots[selectedPos + 4] = 4.toByte()
            needScroll = true
            if (selectedPos + 5 < dots.size) {
                dots[selectedPos + 5] = 2.toByte()
                (selectedPos + 6 until dots.size)
                    .takeWhile { dots[it] != 0.toByte() }
                    .forEach { dots[it] = 0.toByte() }
            }
        }

        if (selectedPos - 1 >= 0 && dots[selectedPos - 1] < 3) {
            dots[selectedPos - 1] = 3
            needScroll = true
            if (selectedPos - 2 < dots.size && dots[selectedPos - 2] < 1) {
                dots[selectedPos - 2] = 1
            }
        }

        if (needScroll) {
            val startBound = selectedPos * (dotSize + dotSpacing)
            if (startBound < scrollAmount) {
                scrollAmount = startBound
                scrollListener.scrollToTarget(scrollAmount)
            }
        }
    }

    interface ScrollListener {
        fun scrollToTarget(scroll: Int)
    }
}