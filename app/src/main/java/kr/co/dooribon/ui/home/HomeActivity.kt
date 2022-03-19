package kr.co.dooribon.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kakao.sdk.common.util.Utility
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.ActivityHomeBinding
import kr.co.dooribon.dialog.NewTripDialog
import kr.co.dooribon.ui.existingtrip.ExistingTripActivity
import kr.co.dooribon.ui.home.adapter.PreviousTripAdapter
import kr.co.dooribon.ui.home.adapter.UpComingTripAdapter
import kr.co.dooribon.ui.home.viewmodel.HomeViewModel
import kr.co.dooribon.ui.triptendency.TripTendencyActivity
import kr.co.dooribon.utils.extension.resizeHomeProgressTripImageView
import kr.co.dooribon.utils.getIntent

/**
 *
 * TODO by SSong-develop : 현재 onResume을 탈때 마다 Server의 데이터를 갱신하는 데, 이를 Service로 변경하거나 다른 식으로 변경해야할 것 같음.
 */
class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    private val viewModel by viewModels<HomeViewModel> {
        viewModelModule.provideHomeViewModelFactory()
    }

    private var _previousTripAdapter: PreviousTripAdapter? = null
    private val previousTripAdapter: PreviousTripAdapter?
        get() = _previousTripAdapter

    private var _upComingTripAdapter: UpComingTripAdapter? = null
    private val upComingTripAdapter: UpComingTripAdapter?
        get() = _upComingTripAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@HomeActivity
            bindViewModel = viewModel
            homeActivity = this@HomeActivity
            navigateNewTrip = { navigateNewTripDialog() }
        }

        lifecycle.addObserver(viewModel)
        initialize()

        observeHomeProceedingTravel()
        observeHomeUpComingTravel()
        observeHomePreviousTravel()
        configureImageViewSize()
        configurePreviousTrip()
        configureUpComingTrip()
        configureViewPagerIndicator()
    }

    private fun initialize() {
        _upComingTripAdapter = UpComingTripAdapter(onItemClicked = { idx ->
            onUpComingTripItemClick(idx)
        })

        _previousTripAdapter = PreviousTripAdapter(onItemClicked = { idx ->
            onPreviousTripItemClick(idx)
        })
    }

    private fun observeHomeProceedingTravel() {
        viewModel.homeProceedingTravel.observe(this) {
            viewModel.initializeHomeImage()
        }
    }

    private fun observeHomeUpComingTravel() {
        viewModel.homeUpComingTravel.observe(this) {
            upComingTripAdapter?.submitItem(it)
        }
    }

    private fun observeHomePreviousTravel() {
        viewModel.homePreviousTravel.observe(this) {
            previousTripAdapter?.submitItem(it)
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
        binding.vpHomeUpcomingTravelContents.apply {
            adapter = upComingTripAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun configurePreviousTrip() {
        binding.rvHomePreviousTravelContent.apply {
            adapter = previousTripAdapter
            layoutManager = LinearLayoutManager(this@HomeActivity)
            addItemDecoration(DividerItemDecoration(this@HomeActivity, 1))
        }
    }

    private fun navigateNewTripDialog() {
        NewTripDialog().show(supportFragmentManager, NEW_TRIP_DIALOG_TAG)
    }

    // 추억 속의 여행 아이템 클릭
    private fun onPreviousTripItemClick(index: Int) {
        startActivity(getIntent<ExistingTripActivity>().apply {
            putExtra(GROUP_ID, viewModel.homePreviousTravel.value?.get(index)!!.previousTravelId)
            putExtra(PREVIOUS_TRAVEL_CONTENTS, viewModel.homePreviousTravel.value?.get(index))
        })
    }

    // 두근두근 다가오는 여행 아이템 클릭
    private fun onUpComingTripItemClick(index: Int) {
        startActivity(getIntent<ExistingTripActivity>().apply {
            putExtra(GROUP_ID, viewModel.homeUpComingTravel.value?.get(index)!!.upComingTravelId)
            putExtra(UPCOMING_TRAVEL_CONTENTS, viewModel.homeUpComingTravel.value?.get(index))
        })
    }

    // Proceeding
    fun navigateExistingTrip() {
        startActivity(getIntent<ExistingTripActivity>().apply {
            putExtra(GROUP_ID, viewModel.homeProceedingTravel.value?.id)
            intent.putExtra(PROCEEDING_TRAVEL_CONTENTS, viewModel.homeProceedingTravel.value)
        })
    }

    // 성향테스트로 가는 Navigate 함수
    fun navigateTripTendencyTest() {
        startActivity(getIntent<TripTendencyActivity>())
    }

    companion object {
        const val NEW_TRIP_DIALOG_TAG = "newTripDialog"

        private const val GROUP_ID = "groupId"

        private const val UPCOMING_TRAVEL_CONTENTS = "upComingTravelContents"

        private const val PROCEEDING_TRAVEL_CONTENTS = "proceedingTravelContents"

        private const val PREVIOUS_TRAVEL_CONTENTS = "previousTravelContents"
    }
}