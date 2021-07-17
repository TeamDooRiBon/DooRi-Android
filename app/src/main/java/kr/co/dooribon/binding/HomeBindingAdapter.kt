package kr.co.dooribon.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter

object HomeBindingAdapter {
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("home_place_text")
    fun setHomePlaceText(textView: TextView, placeText: String?) {
        if (placeText == null) {
            textView.text = "OO"
        } else {
            textView.text = placeText
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("home_people_text")
    fun setHomePeopleText(textView: TextView, peopleText: String?) {
        if (peopleText == null) {
            textView.text = "OO"
        } else {
            textView.text = peopleText
        }
    }
}