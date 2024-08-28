package kr.ac.tukorea.whereareu.presentation.nok

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CircularProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 20f
        isAntiAlias = true
    }

    private val progressPaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 20f
        isAntiAlias = true
    }

    private var progress = 0
    private var max = 30

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = Math.min(width, height) / 2 - 20

        // Draw gray background circle
        canvas.drawCircle(width / 2, height / 2, radius, backgroundPaint)

        // Draw progress (red circle arc)
        val sweepAngle = (360 * progress / max).toFloat()
        canvas.drawArc(
            width / 2 - radius,
            height / 2 - radius,
            width / 2 + radius,
            height / 2 + radius,
            -90f,
            sweepAngle,
            false,
            progressPaint
        )
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate() // Redraw the view
        Log.d("Circular ProgressBar", "Progress Updated: $progress")
    }

    fun setMax(max: Int) {
        this.max = max
    }
}
