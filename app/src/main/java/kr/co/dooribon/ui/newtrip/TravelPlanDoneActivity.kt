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
import kr.co.dooribon.ui.home.HomeActivity
import java.util.*
import kotlin.concurrent.fixedRateTimer

class TravelPlanDoneActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTravelPlanDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_travel_plan_done)

        binding.btnLater.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }

        binding.btnCopyCodes.setOnClickListener {
            val dlg = DoneCopyDialog(this)
            dlg.start()
            fixedRateTimer("Change Indicator", false, 0L, 60*1000 ){

            }
            Handler(Looper.getMainLooper()).postDelayed({
                //changeIndicator()
                val intent = Intent(this, HomeActivity::class.java)
                finish()
                startActivity(intent)
            }, 3000 )

        }
    }

    private fun changeIndicator(){
        var curIndi = 0
        val timer = Timer()
        val indi1 = findViewById<ImageView>(R.id.iv_indi_1)
        val indi2 = findViewById<ImageView>(R.id.iv_indi_2)
        val indi3 = findViewById<ImageView>(R.id.iv_indi_3)

        timer.scheduleAtFixedRate(object: TimerTask(){
            override fun run() {
                when(curIndi%3){
                    0->{
                        indi1.setBackgroundResource(R.drawable.shape_selected_blue_circle)
                        indi2.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi3.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                    }
                    1->{
                        indi1.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi2.setBackgroundResource(R.drawable.shape_selected_blue_circle)
                        indi3.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                    }
                    2->{
                        indi1.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi2.setBackgroundResource(R.drawable.shape_unselected_blue_circle)
                        indi3.setBackgroundResource(R.drawable.shape_selected_blue_circle)
                    }
                }
                curIndi++
            }
        },0, 1000)
    }
}