package kr.co.dooribon.ui.newtrip.join

import android.app.Dialog
import androidx.fragment.app.Fragment
import kr.co.dooribon.R

class DoneJoinDialog(fragment: Fragment) {
    private val dl = Dialog(fragment.requireContext()) // Dialog를 fragment 위에 띄울 때
    fun start() {
        dl.setContentView(R.layout.dialog_done_join) // 다이얼로그에 사용할 xml 파일 불러옴
        dl.setCancelable(false) // 다이얼로그 바깥 부분을 눌렀을 때 다이얼로그가 닫히지 않도록 설정
        dl.show()
    }
}