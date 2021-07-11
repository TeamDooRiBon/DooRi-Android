package kr.co.dooribon.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF

fun Canvas.drawTopRoundRectPath(
    rectF: RectF,
    radius: Float,
    paint: Paint
) {
    val path = Path()

    // Move to Start in bottomLeft
    path.moveTo(rectF.left, rectF.bottom)

    // TopLeft
    path.lineTo(rectF.left, rectF.top + radius)
    path.quadTo(rectF.left, rectF.top, rectF.left + radius, rectF.top)

    // TopRight
    path.lineTo(rectF.right - radius, rectF.top)
    path.quadTo(rectF.right, rectF.top, rectF.right, rectF.top + radius)

    // bottom Right
    path.lineTo(rectF.right, rectF.bottom)

    // bottom Left
    path.lineTo(rectF.left, rectF.bottom)

    path.close()
    drawPath(path, paint)
}