package kr.co.dooribon.utils

import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.widget.EditText
import android.widget.NumberPicker

fun NumberPicker.setNumberPickerTextColor(color: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val count = this.childCount
        for (i in 0..count) {
            val child = this.getChildAt(i)
            if (child is EditText) {
                try {
                    child.setTextColor(color)
                    this.invalidate()

                    var selectorWheelPaintField =
                        this.javaClass.getDeclaredField("mSelectorWheelPaint")
                    var accessible = selectorWheelPaintField.isAccessible
                    selectorWheelPaintField.isAccessible = true
                    (selectorWheelPaintField.get(this) as Paint).color = color
                    selectorWheelPaintField.isAccessible = accessible
                    this.invalidate()
                } catch (e: Exception) {
                    Log.d("NumberPicker TextColor Exception", "$e")
                }
            }
        }
    } else {
        this.textColor = color
    }
}

fun NumberPicker.removeDivider() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val pickerFields = NumberPicker::class.java.declaredFields
        for (pf in pickerFields) {
            if (pf.name == "mSelectionHeight") {
                pf.isAccessible = true
                try {
                    pf.set(this, 0)
                    this.invalidate()
                } catch (e: Exception) {
                    Log.d("NumberPicker DividerColor Exception", "$e")
                }
                break
            }
        }
    } else {
        selectionDividerHeight = 0
    }
}

fun NumberPicker.changeTextSize(textSize: Float) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        try {
            val selectorWheelPaintField = this.javaClass.getDeclaredField("mSelectionWheelPaint")
            selectorWheelPaintField.isAccessible = true
            (selectorWheelPaintField[this] as Paint).textSize = textSize
        } catch (e: Exception) {
            Log.d("NumberPicker changeTextSize Exception", "$e")
        }

        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child is EditText) {
                child.textSize = textSize
            }
        }
        invalidate()
    } else {
        this.textSize = textSize
    }
}