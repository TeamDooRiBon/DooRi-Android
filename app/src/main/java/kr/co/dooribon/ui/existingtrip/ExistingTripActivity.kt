package kr.co.dooribon.ui.existingtrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityExistingTripBinding
import kr.co.dooribon.utils.initExistingTripBottomNavigation

class ExistingTripActivity : AppCompatActivity() {

    private lateinit var binding : ActivityExistingTripBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_existing_trip)

        binding.bottomNavExistingTrip.initExistingTripBottomNavigation(supportFragmentManager)
    }
}