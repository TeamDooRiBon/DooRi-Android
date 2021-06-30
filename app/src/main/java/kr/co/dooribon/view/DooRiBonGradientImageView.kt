package kr.co.dooribon.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.res.use
import kr.co.dooribon.R
import kr.co.dooribon.utils.OnChangeProp

/**
 * Created by SSong-develop 2021.06.30
 *
 * startColor = gradient시작 컬러
 *
 * endColor = gradient 끝나는 컬러
 *
 * gradientAlpha = gradient의 alpha 값 , 이미자가 보여져야 하기 때문에 0.8의 alpha를 주면 잘 보여짐
 */
class DooRiBonGradientImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    private val gradientRectF = RectF()
    private val gradientPaint = Paint()

    var startColor by OnChangeProp(Color.parseColor("#E2E2E2")) {
        update()
    }

    var endColor by OnChangeProp(Color.BLACK) {
        update()
    }

    var gradientAlpha by OnChangeProp(0.6f) {
        update()
    }

    init {
        if (attrs != null) {
            getStyleableAttrs(attrs)
        }
    }

    private fun getStyleableAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.DooRiBonGradientImageView, 0, 0)
            .use {
                startColor = it.getColor(
                    R.styleable.DooRiBonGradientImageView_gradient_start_color,
                    startColor
                )
                endColor =
                    it.getColor(R.styleable.DooRiBonGradientImageView_gradient_end_color, endColor)
                gradientAlpha =
                    it.getFloat(R.styleable.DooRiBonGradientImageView_gradient_alpha, gradientAlpha)
            }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        update()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(gradientRectF, gradientPaint)
    }

    private fun update() {
        val useableWidth = width - (paddingLeft + paddingRight)
        val useableHeight = height - (paddingTop + paddingBottom)
        gradientRectF.right = useableWidth.toFloat()
        gradientRectF.bottom = useableHeight.toFloat()

        gradientPaint.apply {
            shader = LinearGradient(
                0f,0f,0f,height.toFloat(),
                startColor,endColor,Shader.TileMode.CLAMP
            )
            alpha = (gradientAlpha * 255).toInt()
        }

        invalidate()
    }
}