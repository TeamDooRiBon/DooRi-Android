package kr.co.dooribon.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import kr.co.dooribon.R

fun LinearLayout.addChip(chipText: String) {
    val chip =
        LayoutInflater.from(context).inflate(R.layout.view_chip_member_trip_type, null) as Chip

    val layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.MarginLayoutParams.WRAP_CONTENT,
        ViewGroup.MarginLayoutParams.WRAP_CONTENT
    )
    chip.text = chipText
    layoutParams.rightMargin = context.dpToPixel(6)
    addView(chip, layoutParams)
}

@BindingAdapter("set_chip")
fun LinearLayout.setChipList(chipList: List<String>?) {
    chipList?.forEach {
        addChip(it)
    }
}