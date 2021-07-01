package kr.co.dooribon.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityHomeBinding
import kr.co.dooribon.ui.home.adapter.PreviousTripAdapter
import kr.co.dooribon.ui.home.adapter.UpComingTripAdapter
import kr.co.dooribon.ui.home.viewmodel.HomeViewModel
import kr.co.dooribon.utils.StatusBarUtil

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var previousTripAdapter: PreviousTripAdapter

    private lateinit var upComingTripAdapter: UpComingTripAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.bindViewModel = viewModel

        upComingTripAdapter = UpComingTripAdapter()
        previousTripAdapter = PreviousTripAdapter()

        initializeStatusBar()
        configurePreviousTrip(previousTripAdapter)
        configureUpComingTrip(upComingTripAdapter)
        configureViewPagerIndicator()

    }

    private fun initializeStatusBar() {
        StatusBarUtil.changeColor(
            this, ContextCompat.getColor(
                this,
                R.color.doo_ri_bon_home_tool_bar_blue_color
            )
        )
    }

    private fun configureViewPagerIndicator(){
        TabLayoutMediator(binding.vpiHomeUpcomingTravel, binding.vpHomeUpcomingTravelContents) { _,_ -> }.attach()
    }

    private fun configureUpComingTrip(upComingTripAdapter: UpComingTripAdapter) {
        binding.vpHomeUpcomingTravelContents.apply {
            adapter = upComingTripAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        upComingTripAdapter.submitItem(viewModel.upComingTripDummyList)
    }

    private fun configurePreviousTrip(previousTripAdapter: PreviousTripAdapter) {
        binding.rvHomePreviousTravelContent.apply {
            adapter = previousTripAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            addItemDecoration(DividerItemDecoration(this@HomeActivity, 1))
        }
        previousTripAdapter.submitItem(viewModel.previousTripDummyList)
    }
}