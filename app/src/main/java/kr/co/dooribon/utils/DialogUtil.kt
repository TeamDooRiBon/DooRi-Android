package kr.co.dooribon.utils

import android.view.WindowManager
import androidx.fragment.app.DialogFragment

// Dialog 전체 화면으로 나오게 해주는 함수
fun DialogFragment.fullScreenDialogSize() {
    val dialogParams: WindowManager.LayoutParams = dialog!!.window!!.attributes

    dialogParams.width = context?.resources?.displayMetrics?.widthPixels!!
    dialogParams.height = context?.resources?.displayMetrics?.heightPixels!!
}

// DialogFragment 사이즈를 조정해주는 함수
fun DialogFragment.adjustDialogSize(widthRatio: Float, heightRatio: Float) {
    val dialogParams: WindowManager.LayoutParams = dialog!!.window!!.attributes

    dialogParams.width =
        ((this.requireContext().resources.displayMetrics.widthPixels) * widthRatio).toInt()
    dialogParams.height =
        ((this.requireContext().resources.displayMetrics.heightPixels) * heightRatio).toInt()

    this.dialog!!.window!!.apply {
        attributes = dialogParams
    }
}

// DooRiBonDialog 사이즈에 맞게 바꿔주는 함수
fun DialogFragment.setDooRiBonDialogSize(){
    val dialogParams : WindowManager.LayoutParams = this.dialog!!.window!!.attributes

    dialogParams.width = context!!.dpToPixel(310)
    dialogParams.height = context!!.dpToPixel(168)

    this.dialog!!.window!!.apply { attributes = dialogParams }
}