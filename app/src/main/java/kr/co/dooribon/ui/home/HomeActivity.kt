package kr.co.dooribon.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityHomeBinding
import kr.co.dooribon.domain.entity.UpComingTrip
import kr.co.dooribon.ui.home.adapter.UpComingTripAdapter
import kr.co.dooribon.ui.home.viewmodel.HomeViewModel
import kr.co.dooribon.utils.StatusBarUtil

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this

        StatusBarUtil.changeColor(this,ContextCompat.getColor(this,
            R.color.doo_ri_bon_home_tool_bar_blue_color
        ))

        val upComingTripAdapter = UpComingTripAdapter()

        binding.vpHomeUpcomingTravelContents.apply {
            adapter = upComingTripAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        upComingTripAdapter.submitItem(viewModel.upComingTripDummyList)
    }
}