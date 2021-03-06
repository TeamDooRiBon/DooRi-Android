package kr.co.dooribon.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlin.math.roundToInt

/**
 * Created by SSong-develop 2021-06-29
 *
 * Just use For CustomView dp , Not Whole Projects
 */
fun Context.dpToPixel(dp: Int): Int = (dp * resources.displayMetrics.density).roundToInt()

fun Context.dpToPixelFloat(dp: Int): Float =
    (dp * resources.displayMetrics.density).roundToInt().toFloat()

// intent 받아오는 확장함수
inline fun <reified T : Any> Context.getIntent() = Intent(this, T::class.java)

// 디버그용 토스트 함수
fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}