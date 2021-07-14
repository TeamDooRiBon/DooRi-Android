package kr.co.dooribon.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.ActivityHomeBinding
import kr.co.dooribon.dialog.NewTripDialog
import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.UpComingTravel
import kr.co.dooribon.ui.existingtrip.ExistingTripActivity
import kr.co.dooribon.ui.home.adapter.PreviousTripAdapter
import kr.co.dooribon.ui.home.adapter.UpComingTripAdapter
import kr.co.dooribon.ui.home.viewmodel.HomeViewModel
import kr.co.dooribon.ui.triptendency.TripTendencyActivity
import kr.co.dooribon.utils.extension.resizeHomeProgressTripImageView
import kr.co.dooribon.utils.getIntent

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel by viewModels<HomeViewModel> {
        viewModelModule.provideHomeViewModelFactory()
    }

    private lateinit var previousTripAdapter: PreviousTripAdapter

    private lateinit var upComingTripAdapter: UpComingTripAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.bindViewModel = viewModel
        binding.homeActivity = this
        binding.navigateNewTrip = { navigateNewTripDialog() }
        lifecycle.addObserver(viewModel)

        observeHomeProceedingTravel()
        observeHomeUpComingTravel()
        observeHomePreviousTravel()
        configureImageViewSize()
        configurePreviousTrip()
        configureUpComingTrip()
        configureViewPagerIndicator()
    }

    private fun observeHomeProceedingTravel() {
        viewModel.homeProceedingTravel.observe(this) {
            viewModel.initializeHomeImage()
        }
    }

    private fun observeHomeUpComingTravel() {
        viewModel.homeUpComingTravel.observe(this) {
            upComingTripAdapter.submitItem(it)
        }
    }

    private fun observeHomePreviousTravel() {
        viewModel.homePreviousTravel.observe(this) {
            previousTripAdapter.submitItem(it)
        }
    }

    private fun configureImageViewSize() {
        binding.ivHomeProceedingTravel.resizeHomeProgressTripImageView(0.4f)
    }

    private fun configureViewPagerIndicator() {
        TabLayoutMediator(
            binding.vpiHomeUpcomingTravel,
            binding.vpHomeUpcomingTravelContents
        ) { _, _ -> }.attach()
    }

    private fun configureUpComingTrip() {
        upComingTripAdapter = UpComingTripAdapter(onItemClicked = { idx ->
            onUpComingTripItemClick(idx)
        })

        binding.vpHomeUpcomingTravelContents.apply {
            adapter = upComingTripAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun configurePreviousTrip() {
        previousTripAdapter = PreviousTripAdapter(onItemClicked = { idx ->
            onPreviousTripItemClick(idx)
        })

        binding.rvHomePreviousTravelContent.apply {
            adapter = previousTripAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            addItemDecoration(DividerItemDecoration(this@HomeActivity, 1))
        }
    }

    private fun navigateNewTripDialog() {
        NewTripDialog().show(supportFragmentManager, NEW_TRIP_DIALOG_TAG)
    }

    private fun onPreviousTripItemClick(index: Int) {
        val previousIntent = Intent(this, ExistingTripActivity::class.java)
        previousIntent.putExtra(
            "groupId",
            viewModel.homePreviousTravel.value?.get(index)!!.previousTravelId
        )
    }

    private fun onUpComingTripItemClick(index: Int) {
        val upComingIntent = Intent(this, ExistingTripActivity::class.java)
        upComingIntent.putExtra(
            "groupId",
            viewModel.homeUpComingTravel.value?.get(index)!!.upComingTravelId
        )
        startActivity(upComingIntent)
    }

    // Proceeding
    fun navigateExistingTrip() {
        val existingTravelIntent = Intent(this, ExistingTripActivity::class.java)
        existingTravelIntent.putExtra("groupId", viewModel.homeProceedingTravel.value?.id)
        startActivity(existingTravelIntent)
    }

    // 성향테스트로 가는 Navigate 함수
    fun navigateTripTendencyTest() {
        startActivity(getIntent<TripTendencyActivity>())
    }

    companion object {
        const val NEW_TRIP_DIALOG_TAG = "newTripDialog"
    }
}