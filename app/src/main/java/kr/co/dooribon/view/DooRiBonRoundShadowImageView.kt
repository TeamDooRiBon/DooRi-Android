package kr.co.dooribon.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.res.use
import com.google.android.material.imageview.ShapeableImageView
import kr.co.dooribon.R
import kr.co.dooribon.utils.OnChangeProp

/**
 * Created by SSong-develop on 2021-06-29
 *
 * DooRiBonRoundGradientImageView
 *
 * 타원형의 원을 그리지 않을 것이기 때문에 굳이 radius는 그냥 width로 계산함
 *
 * app:image_view_alpha , 이미지 위에 색상을 끼얹고 싶을때 이미지가 얼마만큼 보이게 할지? 0.4~0.5이 적당함
 * app:image_view_shadow_color , 이미지 위에 끼얹고 싶은 색상 설정
 * app:shapeAppearanceOverlay="@style/roundedImageView" , 이미자뷰를 둥그렇게 만들어줄 style Value
 * 이렇게 3개 값을 주게 되면 그림자가 낀 원형 이미지뷰를 만들 수 있습니다.
 */
class DooRiBonRoundShadowImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ShapeableImageView(context, attrs) {

    private val shadowPaint = Paint()

    private var useWidth = 0
    private var useHeight = 0

    var shadowColor by OnChangeProp(Color.BLACK) {
        update()
    }

    var imageViewShadowAlpha by OnChangeProp(0.4f) {
        update()
    }

    init {
        if (attrs != null) {
            getStyleableAttrs(attrs)
        }
    }

    private fun getStyleableAttrs(attrs: AttributeSet) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.DooRiBonRoundGradientImageView,
            0,
            0
        ).use {
            shadowColor = it.getColor(
                R.styleable.DooRiBonRoundGradientImageView_image_view_shadow_color,
                shadowColor
            )
            imageViewShadowAlpha = it.getFloat(
                R.styleable.DooRiBonRoundGradientImageView_image_view_shadow_alpha,
                imageViewShadowAlpha
            )
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        update()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(useWidth / 2f, useHeight / 2f, useWidth / 2f, shadowPaint)
    }

    private fun update() {
        useWidth = width
        useHeight = height

        shadowPaint.apply {
            color = shadowColor
            alpha = (imageViewShadowAlpha * 255).toInt()
        }
        invalidate()
    }

}