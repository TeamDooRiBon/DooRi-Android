package kr.co.dooribon.ui.existingtrip

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityExistingTripBinding
import kr.co.dooribon.utils.initExistingTripBottomNavigation

class ExistingTripActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExistingTripBinding
    var groupCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_existing_trip)
        binding.activity = this
        binding.lifecycleOwner = this
        groupCode = intent.getStringExtra("groupId").toString()

        configureBackButton()
        configureBottomNavigation()
    }

    fun configureBackButton() {
        binding.ivExistingTripBack.setOnClickListener {
            finish()
        }
    }

    private fun configureBottomNavigation() {
        binding.bottomNavExistingTrip.initExistingTripBottomNavigation(supportFragmentManager)
    }
}