package kr.co.dooribon.ui.existingtrip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityExistingTripBinding
import kr.co.dooribon.utils.initExistingTripBottomNavigation

class ExistingTripActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExistingTripBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_existing_trip)

        configureBackButton()
        configureBottomNavigation()
    }

    private fun configureBackButton(){
        binding.ivExistingTripBack.setOnClickListener {
            finish()
        }
    }

    private fun configureBottomNavigation(){
        binding.bottomNavExistingTrip.initExistingTripBottomNavigation(supportFragmentManager)
    }
}