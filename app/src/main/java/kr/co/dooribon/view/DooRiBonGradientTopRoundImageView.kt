package kr.co.dooribon.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.content.res.use
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import kr.co.dooribon.R
import kr.co.dooribon.utils.OnChangeProp
import kr.co.dooribon.utils.dpToPixelFloat
import kr.co.dooribon.utils.drawTopRoundRectPath

/**
 * Created by SSong-develop 2021.06.30
 *
 * startColor = gradient시작 컬러
 *
 * endColor = gradient 끝나는 컬러
 *
 * gradientAlpha = gradient의 alpha 값 , 이미자가 보여져야 하기 때문에 0.8의 alpha를 주면 잘 보여짐
 *
 * cornerRadius = top left , right에 얼마만큼 radius를 줄 것인지
 */
class DooRiBonGradientTopRoundImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr) {
    private val gradientRectF = RectF()
    private val gradientPaint = Paint()

    var startColor by OnChangeProp(Color.WHITE) {
        update()
    }

    var endColor by OnChangeProp(Color.BLACK) {
        update()
    }

    var gradientAlpha by OnChangeProp(0.6f) {
        update()
    }

    var cornerRadius by OnChangeProp(context.dpToPixelFloat(12)) {
        updateBackground()
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
                cornerRadius =
                    it.getDimension(
                        R.styleable.DooRiBonGradientImageView_gradient_corner_radius,
                        cornerRadius
                    )
            }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        update()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawTopRoundRectPath(gradientRectF, cornerRadius, gradientPaint)
    }

    private fun update() {
        val useableWidth = width - (paddingLeft + paddingRight)
        val useableHeight = height - (paddingTop + paddingBottom)
        gradientRectF.right = useableWidth.toFloat()
        gradientRectF.bottom = useableHeight.toFloat()

        gradientPaint.apply {
            shader = LinearGradient(
                0f, 0f, 0f, height.toFloat(),
                startColor, endColor, Shader.TileMode.CLAMP
            )
            alpha = (gradientAlpha * 255).toInt()
        }

        invalidate()
    }

    private fun updateBackground() {
        shapeAppearanceModel = shapeAppearanceModel.toBuilder()
            .setTopRightCorner(CornerFamily.ROUNDED, cornerRadius)
            .setTopLeftCorner(CornerFamily.ROUNDED, cornerRadius)
            .build()
    }
}