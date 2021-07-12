package kr.co.dooribon.ui.newtrip

import android.app.Dialog
import android.content.Context
import android.widget.ImageView
import kr.co.dooribon.R
import java.util.*

class DoneCopyDialog(context: Context) {
    private val dlg = Dialog(context)
    private var chk1 = true
    private var chk2 = false
    private var chk3 = false
    fun start() {
        dlg.setContentView(R.layout.dialog_done_copy) // 다이얼로그에 사용할 xml 파일 불러옴
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                when {
                    chk1 -> {
                        dlg.findViewById<ImageView>(R.id.iv_indi_1)
                            .setImageResource(R.drawable.shape_selected_blue_circle)
                        dlg.findViewById<ImageView>(R.id.iv_indi_2)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dlg.findViewById<ImageView>(R.id.iv_indi_3)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        chk1 = false
                        chk2 = true
                        chk3 = false
                    }
                    chk2 -> {
                        dlg.findViewById<ImageView>(R.id.iv_indi_1)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dlg.findViewById<ImageView>(R.id.iv_indi_2)
                            .setImageResource(R.drawable.shape_selected_blue_circle)
                        dlg.findViewById<ImageView>(R.id.iv_indi_3)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        chk1 = false
                        chk2 = false
                        chk3 = true
                    }
                    chk3 -> {
                        dlg.findViewById<ImageView>(R.id.iv_indi_1)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dlg.findViewById<ImageView>(R.id.iv_indi_2)
                            .setImageResource(R.drawable.shape_unselected_blue_circle)
                        dlg.findViewById<ImageView>(R.id.iv_indi_3)
                            .setImageResource(R.drawable.shape_selected_blue_circle)
                        chk1 = true
                        chk2 = false
                        chk3 = false
                    }
                }
            }
        }, 0, 750)
        dlg.setCancelable(false) // 다이얼로그 바깥 부분을 눌렀을 때 다이얼로그가 닫히지 않도록 설정
        dlg.show()
    }

    fun dismiss(){
        dlg.dismiss()
    }
}