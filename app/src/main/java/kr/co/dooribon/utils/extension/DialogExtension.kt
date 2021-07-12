package kr.co.dooribon.utils.extension

import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import kr.co.dooribon.utils.dpToPixel

fun DialogFragment.setDooRiBonDialogSize(){
    val dialogParams : WindowManager.LayoutParams = this.dialog!!.window!!.attributes

    dialogParams.width = context!!.dpToPixel(310)
    dialogParams.height = context!!.dpToPixel(168)

    this.dialog!!.window!!.apply { attributes = dialogParams }
}