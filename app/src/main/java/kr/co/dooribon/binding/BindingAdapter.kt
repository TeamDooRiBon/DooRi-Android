package kr.co.dooribon.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kr.co.dooribon.R
import kr.co.dooribon.utils.dpToPixel
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
            .override(imageView.context.dpToPixel(200), imageView.context.dpToPixel(160))
            .dontTransform()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("set_profile_image_url")
    fun setProfileImageUrl(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .dontTransform()
            .override(imageView.context.dpToPixel(20), imageView.context.dpToPixel(20))
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("set_travel_tendency_result")
    fun setTravelTendencyResult(imageView: ImageView, imageUrl: String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .dontTransform()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("set_up_coming_travel_image_url")
    fun setUpComingTravelImageUrl(imageView : ImageView , imageUrl : String?){
        Glide.with(imageView.context)
            .load(imageUrl)
            .override(imageView.context.dpToPixel(160),imageView.context.dpToPixel(160))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("set_previous_travel_image_url")
    fun setPreviousTravelImageUrl(imageView : ImageView , imageUrl : String?) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .override(imageView.context.dpToPixel(100), imageView.context.dpToPixel(100))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
    }
}