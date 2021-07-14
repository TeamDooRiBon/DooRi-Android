package kr.co.dooribon.ui.existingtrip

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityExistingTripBinding
import kr.co.dooribon.ui.existingtrip.viewmodel.ExistingTripViewModel
import kr.co.dooribon.utils.initExistingTripBottomNavigation

class ExistingTripActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExistingTripBinding
    var groupCode = ""

    private val viewModel by viewModels<ExistingTripViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_existing_trip)
        binding.activity = this
        binding.lifecycleOwner = this

        configureBackButton()
        configureBottomNavigation()
        setGroupId()
    }

    private fun setGroupId() {
        groupCode = intent.getStringExtra("groupId").toString()
        viewModel.setGroupId(groupCode)
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