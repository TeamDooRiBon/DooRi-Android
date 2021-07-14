package kr.co.dooribon.ui.existingtrip

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityExistingTripBinding
import kr.co.dooribon.ui.existingtrip.viewmodel.ExistingTripViewModel
import kr.co.dooribon.utils.initExistingTripBottomNavigation

class ExistingTripActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExistingTripBinding

    private val viewModel by viewModels<ExistingTripViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_existing_trip)
        binding.activity = this
        binding.lifecycleOwner = this
        intent?.getStringExtra("groupId")?.let { viewModel.setGroupId(it) }

        configureBackButton()
        configureBottomNavigation()
    }

    fun configureBackButton() {
        binding.ivExistingTripBack.setOnClickListener {
            finish()
        }
    }

    private fun configureBottomNavigation() {
        val tendencyBundle = Bundle()
        tendencyBundle.apply {
            putString("tendency_groupId",viewModel.getGroupId())
        }
        binding.bottomNavExistingTrip.initExistingTripBottomNavigation(supportFragmentManager,tendencyBundle)
    }
}