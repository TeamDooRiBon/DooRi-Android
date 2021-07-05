package kr.co.dooribon.ui.home

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import kr.co.dooribon.dialog.NewTripDialog
import kr.co.dooribon.domain.entity.PreviousTrip
import kr.co.dooribon.domain.entity.UpComingTrip
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
        binding.navigateNewTrip = { navigateNewTripDialog() }

        initializeStatusBar()
        configurePreviousTrip()
        configureUpComingTrip()
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

    private fun configureViewPagerIndicator() {
        TabLayoutMediator(
            binding.vpiHomeUpcomingTravel,
            binding.vpHomeUpcomingTravelContents
        ) { _, _ -> }.attach()
    }

    private fun configureUpComingTrip() {
        upComingTripAdapter = UpComingTripAdapter(onItemClicked = { idx, upComingTripItem ->
            onUpComingTripItemClick(idx, upComingTripItem)
        })

        binding.vpHomeUpcomingTravelContents.apply {
            adapter = upComingTripAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun configurePreviousTrip() {
        previousTripAdapter = PreviousTripAdapter(onItemClicked = { idx, previousTripItem ->
            onPreviousTripItemClick(idx, previousTripItem)
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

    private fun onPreviousTripItemClick(index: Int, item: PreviousTrip) {
        Toast.makeText(this, "$index , ${item.previousTripPlace}", Toast.LENGTH_SHORT).show()
    }

    private fun onUpComingTripItemClick(index: Int, item: UpComingTrip) {

        Toast.makeText(this, "$index , ${item.upComingTripLocation}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val NEW_TRIP_DIALOG_TAG = "newTripDialog"
    }
}