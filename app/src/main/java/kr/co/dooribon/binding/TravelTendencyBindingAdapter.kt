package kr.co.dooribon.binding

import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
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

@BindingAdapter("my_travel_tendency_visible_card_view")
fun CardView.setMyTravelTendencyTest(isTravelTendencyResult : Boolean){
    isVisible = isTravelTendencyResult
}

@BindingAdapter("my_travel_tendency_visible_recyclerview")
fun RecyclerView.setOtherTravelTendecy(isTravelTendencyResult: Boolean){
    isVisible = isTravelTendencyResult
}

@BindingAdapter("my_travel_tendency_visible_constraint")
fun ConstraintLayout.setOtherTravelTendency(isTravelTendencyResult : Boolean){
    isVisible = isTravelTendencyResult
}