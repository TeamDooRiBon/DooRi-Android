package kr.co.dooribon.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.co.dooribon.R
import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.Travel
import kr.co.dooribon.domain.entity.UpComingTravel
import kotlin.math.roundToInt

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("home_proceeding_travel_image")
    fun setHomeProceedingTravelImage(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.bg_gray_top_round_skeleton)
            .override(
                imageView.context.resources.displayMetrics.widthPixels,
                (imageView.context.resources.displayMetrics.heightPixels * 0.4).roundToInt()
            )
            .into(imageView)
    }


    @JvmStatic
    @BindingAdapter("set_image_url")
    fun setImageWithUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

}