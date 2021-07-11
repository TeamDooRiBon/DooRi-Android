package kr.co.dooribon.utils.extension

import android.util.Log
import android.widget.ImageView
import kr.co.dooribon.application.MainApplication.Companion.pixelRatio
import kotlin.math.roundToInt

fun ImageView.resizeHomeProgressTripImageView(heightRatio : Float){
    val imageViewParam = layoutParams

    val screenHeight = pixelRatio.screenHeight

    imageViewParam.width = pixelRatio.screenWidth
    imageViewParam.height = ((screenHeight) * heightRatio).roundToInt()

    Log.d("bindingAdapter","${imageViewParam.width} , ${imageViewParam.height}")
    this.layoutParams = imageViewParam
}