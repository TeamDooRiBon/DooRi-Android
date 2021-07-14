package kr.co.dooribon.binding

import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import kr.co.dooribon.utils.extension.resizeMatchWidth
import kr.co.dooribon.utils.extension.resizeWrapWidth

object TravelTendencyBindingAdapter {
    @JvmStatic
    @BindingAdapter("travel_tendency_previous_btn_visibilty")
    fun setTravelTendencyPreviousBtnVisibility(button: Button, questionPosition: Int) {
        if (questionPosition == 0)
            button.visibility = View.INVISIBLE
        else
            button.visibility = View.VISIBLE
    }

    @JvmStatic
    @BindingAdapter("travel_tendency_next_btn_size")
    fun setTravelTendencyNextBtnSize(button: Button, questionPosition: Int) {
        if (questionPosition == 0)
            button.resizeMatchWidth()
        else
            button.resizeWrapWidth()
    }

}

@BindingAdapter("my_travel_tendency_test")
fun CardView.setMyTravelTendencyTest(isMyTravelTendencyResult : Boolean){
    isVisible = isMyTravelTendencyResult
}

@BindingAdapter("my_travel_tendency_type")
fun CardView.setMyTravelTendencyType(isMyTravelTendencyResult: Boolean){
    isVisible = !isMyTravelTendencyResult
}