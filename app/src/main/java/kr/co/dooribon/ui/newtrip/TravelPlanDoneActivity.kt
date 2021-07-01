package kr.co.dooribon.ui.newtrip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTravelPlanDoneBinding
import kr.co.dooribon.ui.home.HomeActivity

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
            dlg.start("temp")
        }

    }
}