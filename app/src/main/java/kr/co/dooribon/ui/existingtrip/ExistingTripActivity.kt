package kr.co.dooribon.ui.existingtrip

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityExistingTripBinding
import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.Travel
import kr.co.dooribon.domain.entity.UpComingTravel
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
        intent?.getStringExtra("groupId")?.let { viewModel.setGroupId(it) }
        intent?.apply {
            getParcelableExtra<Travel>("proceedingTravelContents")?.let { viewModel.initializeHomeProceedingTravel(it) }
            getParcelableExtra<UpComingTravel>("upComingTravelContents")?.let { viewModel.initializeUpComingTravel(it) }
            getParcelableExtra<PreviousTravel>("previousTravelContents")?.let { viewModel.initializePreviousTravel(it) }
        }

        observeProceedingTravelContents()
        observeUpComingTravelContents()
        observePreviousTravelContents()
        configureBackButton()
        configureBottomNavigation()
        setGroupId()
    }

    private fun setGroupId() {
        groupCode = intent.getStringExtra("groupId").toString()
        viewModel.setGroupId(groupCode)
    }

    private fun observePreviousTravelContents() {
        viewModel.homePreviousTravelContents.observe(this){
            binding.apply {
                tvExistingTripStartDate.text = it.previousTravelDate
                tvExistingTripEndDate.text = it.previousTravelDate
                tvExistingTripTitle.text = it.previousTravelTitle
                tvExistingTripPeople.text = it.previousTravelPeople.toString()
                tvExistingTripPlace.text = it.previousTravelPlace
            }
        }
    }

    private fun observeUpComingTravelContents() {
        viewModel.homeUpComingTravelContents.observe(this){
            binding.apply {
                tvExistingTripStartDate.text = it.upComingTravelStartDate
                tvExistingTripEndDate.text = it.upComingTravelEndDate
                tvExistingTripTitle.text = it.upComingTravelTitle
                tvExistingTripPeople.text = it.upComingTravelPersonCount.toString()
                tvExistingTripPlace.text = it.upComingTravelLocation
            }
        }
    }

    private fun observeProceedingTravelContents() {
        viewModel.homeProceedingTravelContents.observe(this){
            binding.apply {
                tvExistingTripStartDate.text = it.travelStartDate
                tvExistingTripEndDate.text = it.travelEndDate
                tvExistingTripTitle.text = it.travelTitle
                tvExistingTripPeople.text = it.travelMembers.size.toString()
                tvExistingTripPlace.text = it.travelDestination
            }
        }
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