package kr.co.dooribon.view.numberpicker

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import kr.co.dooribon.R
import kr.co.dooribon.utils.*

/**
 * created by SSong-develop 2021.07.09
 */
class DooRiBonNumberPicker(
    context: Context,
    attrs: AttributeSet? = null
) : android.widget.NumberPicker(context, attrs) {

    var pickerTextColor by OnChangeProp(ContextCompat.getColor(context, R.color.gray_gray_5_main)) {
        update()
    }

    var pickerTextSize by OnChangeProp(context.dpToPixelFloat(24)) {
        update()
    }

    init {
        if (attrs != null) {
            getStyleableAttrs(attrs)
        }
        removeDivider()
    }

    private fun getStyleableAttrs(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.DooRiBonNumberPicker, 0, 0).use {
            pickerTextColor =
                it.getColor(R.styleable.DooRiBonNumberPicker_picker_text_color, pickerTextColor)
            pickerTextSize =
                it.getFloat(R.styleable.DooRiBonNumberPicker_picker_text_size, pickerTextSize)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        update()
    }

    private fun update() {
        setNumberPickerTextColor(pickerTextColor)
        changeTextSize(pickerTextSize)
    }
}