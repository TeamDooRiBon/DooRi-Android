package kr.co.dooribon.utils

import android.view.WindowManager
import androidx.fragment.app.DialogFragment

fun DialogFragment.fullScreenDialogSize() {
    val dialogParams : WindowManager.LayoutParams = dialog!!.window!!.attributes

    dialogParams.width = context?.resources?.displayMetrics?.widthPixels!!
    dialogParams.height = context?.resources?.displayMetrics?.heightPixels!!
}