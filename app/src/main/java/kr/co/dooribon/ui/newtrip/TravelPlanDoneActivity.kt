package kr.co.dooribon.ui.newtrip

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTravelPlanDoneBinding
import kr.co.dooribon.ui.existingtrip.ExistingTripActivity
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.concurrent.timerTask

class TravelPlanDoneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTravelPlanDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel_plan_done)

        backBtnClickListener()

        binding.btnLater.setOnClickListener {
            val intent = Intent(this, ExistingTripActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // activity back stack 모두 제거
            finish() // 현재 액티비티 종료
            startActivity(intent)
        }

        binding.btnCopyCodes.setOnClickListener {
            val dlg = DoneCopyDialog(this)
            dlg.start()
            Timer().schedule(timerTask { moveToExistingTripActivity(dlg) }, 3500)
        }
    }

    private fun moveToExistingTripActivity(dlg : DoneCopyDialog){
        val intent = Intent(this, ExistingTripActivity::class.java)
        dlg.dismiss() // 다이얼로그 종료
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // activity back stack 모두 제거
        finish() // 현재 액티비티 종료
        startActivity(intent)
    }

    private fun changeIndicator() {
        var curIndi = 0
        val timer = Timer()
        val indi1 = findViewById<ImageView>(R.id.iv_indi_1)
        val indi2 = findViewById<ImageView>(R.id.iv_indi_2)
        val indi3 = findViewById<ImageView>(R.id.iv_indi_3)

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                when (curIndi % 3) {
                    0 -> {
                        indi1.setBackgroundResource(R.drawable.shape_selected_blue_circle)
                        indi2.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi3.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                    }
                    1 -> {
                        indi1.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi2.setBackgroundResource(R.drawable.shape_selected_blue_circle)
                        indi3.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                    }
                    2 -> {
                        indi1.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi2.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi3.setBackgroundResource(R.drawable.shape_selected_blue_circle)
                    }
                }
                curIndi++
            }
        }, 0, 1000)
    }

    private fun backBtnClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}