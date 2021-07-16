package kr.co.dooribon.ui.newtrip.join

import android.app.Dialog
import android.widget.ImageView
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import java.util.*

class DoneJoinDialog(fragment: Fragment) {
    private val dl = Dialog(fragment.requireContext()) // Dialog를 fragment 위에 띄울 때
    private var chk1 = true
    private var chk2 = false
    private var chk3 = false
    fun start() {
        dl.setContentView(R.layout.dialog_done_join) // 다이얼로그에 사용할 xml 파일 불러옴
        Timer().scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                when {
                    chk1 -> {
                        dl.findViewById<ImageView>(R.id.iv_indi_1)
                            .setImageResource(R.drawable.shape_selected_blue_circle)
                        dl.findViewById<ImageView>(R.id.iv_indi_2)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dl.findViewById<ImageView>(R.id.iv_indi_3)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        chk1 = false
                        chk2 = true
                        chk3 = false
                    }
                    chk2 -> {
                        dl.findViewById<ImageView>(R.id.iv_indi_1)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dl.findViewById<ImageView>(R.id.iv_indi_2)
                            .setImageResource(R.drawable.shape_selected_blue_circle)
                        dl.findViewById<ImageView>(R.id.iv_indi_3)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        chk1 = false
                        chk2 = false
                        chk3 = true
                    }
                    chk3 -> {
                        dl.findViewById<ImageView>(R.id.iv_indi_1)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dl.findViewById<ImageView>(R.id.iv_indi_2)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dl.findViewById<ImageView>(R.id.iv_indi_3)
                            .setImageResource(R.drawable.shape_selected_blue_circle)
                        chk1 = true
                        chk2 = false
                        chk3 = false
                    }
                }
            }
        }, 0, 750)
        dl.setCancelable(false) // 다이얼로그 바깥 부분을 눌렀을 때 다이얼로그가 닫히지 않도록 설정
        dl.show()
    }
    fun dismiss(){
        dl.dismiss()
    }
}