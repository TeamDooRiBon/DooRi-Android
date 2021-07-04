package kr.co.dooribon.ui.newtrip

import android.app.Dialog
import android.content.Context
import kr.co.dooribon.R

class DoneCopyDialog(context: Context) {
    private val dlg = Dialog(context)
    fun start() {
        dlg.setContentView(R.layout.dialog_done_copy) // 다이얼로그에 사용할 xml 파일 불러옴
        dlg.setCancelable(false) // 다이얼로그 바깥 부분을 눌렀을 때 다이얼로그가 닫히지 않도록 설정
        dlg.show()
    }
}