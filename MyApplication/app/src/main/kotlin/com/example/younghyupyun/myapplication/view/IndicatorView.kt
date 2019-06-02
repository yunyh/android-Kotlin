package com.example.younghyupyun.myapplication.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.younghyupyun.myapplication.R

class IndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        const val OFFSET = 3
        const val MAX_DRAW_COUNT = 7
    }

    private var indicatorDrawable: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.ic_indicator)
    private var indicatorDrawableMedium: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.ic_indicator_medium)
    private var indicatorDrawableSmall: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.ic_indicator_smail)
    private var indicatorDrawableSelected: Drawable? =
        ContextCompat.getDrawable(context, R.drawable.ic_indicator_selected)

    private val indicatorHeight
        get() = indicatorDrawable?.intrinsicHeight ?: 0

    private val itemCount: Int
        get() = recyclerViewAdapter?.itemCount ?: 0

    private val indicatorDrawableHeight
        get() = indicatorDrawable?.intrinsicHeight ?: 0

    private val indicatorDrawableMediumHeight
        get() = indicatorDrawableMedium?.intrinsicHeight ?: 0

    private val indicatorDrawableSmallHeight
        get() = indicatorDrawableSmall?.intrinsicHeight ?: 0

    private val paddingMediumIndicator: Int
        get() = (indicatorDrawableHeight - indicatorDrawableMediumHeight) / 2

    private val paddingSmallIndicator: Int
        get() = (indicatorDrawableHeight - indicatorDrawableSmallHeight) / 2

    private var completeVisiblePosition = 0

    private val scrollListener by lazy {
        object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    (recyclerView.layoutManager as? LinearLayoutManager)?.apply {
                        completeVisiblePosition = findFirstCompletelyVisibleItemPosition()
                    }
                    invalidate()
                }
            }
        }
    }

    private val dataObserver by lazy {
        object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                invalidate()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                invalidate()
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                invalidate()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                invalidate()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                invalidate()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                super.onItemRangeChanged(positionStart, itemCount, payload)
                invalidate()
            }
        }
    }

    private var recyclerViewAdapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            onDrawIndicator(this)
        }
    }

    private fun onDrawIndicator(canvas: Canvas) {

        val offset = 60
        var left = when {
            completeVisiblePosition == OFFSET -> (indicatorDrawable?.intrinsicWidth ?: 0) + offset
            completeVisiblePosition < OFFSET -> ((indicatorDrawable?.intrinsicWidth
                ?: 0) * 2.5f).toInt() + offset
            else -> offset
        }

        repeat((0 until itemCount).count()) {
            val drawIndicator = getIndicator(it) ?: return@repeat/*when {
                it == completeVisiblePosition -> indicatorDrawableSelected
                it == completeVisiblePosition - OFFSET -> indicatorDrawableMedium
                it == completeVisiblePosition - OFFSET - 1 -> indicatorDrawableSmall
                it <= (completeVisiblePosition - OFFSET - 2) -> return@repeat

                it == completeVisiblePosition + OFFSET -> indicatorDrawableMedium
                it == completeVisiblePosition + OFFSET + 1 -> indicatorDrawableSmall
                it >= (completeVisiblePosition + OFFSET + 2) -> return@repeat
                else -> indicatorDrawable
            }*/

            val height = drawIndicator.intrinsicHeight
            val width = drawIndicator.intrinsicWidth

            when (drawIndicator) {
                indicatorDrawableSmall -> {
                    val startLeft = if (it <= completeVisiblePosition) {
                        left - paddingSmallIndicator
                    } else {
                        left + paddingSmallIndicator
                    }

                    drawIndicator.setBounds(
                        startLeft,
                        paddingTop + paddingSmallIndicator,
                        startLeft + width,
                        height + paddingTop + paddingSmallIndicator
                    )
                }
                indicatorDrawableMedium -> {
                    val startLeft = if (it <= completeVisiblePosition) {
                        left - paddingMediumIndicator
                    } else {
                        left + paddingMediumIndicator
                    }

                    drawIndicator.setBounds(
                        startLeft,
                        paddingTop + paddingMediumIndicator,
                        startLeft + width,
                        height + paddingTop + paddingMediumIndicator
                    )
                }
                else -> drawIndicator.setBounds(
                    left,
                    paddingTop,
                    left + width,
                    height + paddingTop
                )
            }

            drawIndicator.draw(canvas)
            left += (width * 1.5).toInt()
        }
    }

    private fun getIndicator(drawIndex: Int): Drawable? {
        return when {
            completeVisiblePosition < OFFSET -> when {
                drawIndex == completeVisiblePosition -> indicatorDrawableSelected
                drawIndex == OFFSET -> indicatorDrawableMedium
                drawIndex == OFFSET + 1 -> indicatorDrawableSmall
                drawIndex >= (OFFSET + 2) -> return null
                else -> indicatorDrawable
            }
            completeVisiblePosition == OFFSET -> when {
                drawIndex == completeVisiblePosition -> indicatorDrawableSelected
                drawIndex == OFFSET + 1 -> indicatorDrawableMedium
                drawIndex == OFFSET + 2 -> indicatorDrawableSmall
                drawIndex == 0 -> indicatorDrawableMedium
                drawIndex >= (OFFSET + 3) -> return null
                else -> indicatorDrawable
            }
            else -> when {
                drawIndex == completeVisiblePosition -> indicatorDrawableSelected
                drawIndex == completeVisiblePosition - OFFSET -> indicatorDrawableMedium
                drawIndex == completeVisiblePosition - OFFSET - 1 -> indicatorDrawableSmall
                drawIndex <= (completeVisiblePosition - OFFSET - 2) -> return null

                drawIndex == completeVisiblePosition + OFFSET - 2 -> indicatorDrawableMedium
                drawIndex == completeVisiblePosition + OFFSET - 1 -> indicatorDrawableSmall
                drawIndex >= (completeVisiblePosition + OFFSET) -> return null
                else -> indicatorDrawable
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val minH = paddingTop + paddingBottom + indicatorHeight
        val h = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.AT_MOST -> minH
            MeasureSpec.UNSPECIFIED -> heightMeasureSpec
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
            else -> 0
        }
        setMeasuredDimension(widthMeasureSpec, h)
    }

    fun attachRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(scrollListener)
        recyclerView.adapter?.registerAdapterDataObserver(dataObserver)
        recyclerViewAdapter = recyclerView.adapter
        /* (recyclerView.layoutManager as? LinearLayoutManager)?.apply {
             this
         }*/

        invalidate()
    }
}