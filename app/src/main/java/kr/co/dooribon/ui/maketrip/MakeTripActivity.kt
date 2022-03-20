package kr.co.dooribon.ui.maketrip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
//import androidx.lifecycle.repeatOnLifecycle
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityMakeTripBinding
import kr.co.dooribon.utils.base.BaseActivity

class MakeTripActivity : BaseActivity<ActivityMakeTripBinding>(R.layout.activity_make_trip) {

    private val viewModel: MakeTripViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        collectFlows()
    }

    private fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
        val pagerAdapter = SlidePagerAdapter(this)
        binding.vpMain.adapter = pagerAdapter
    }

    private fun collectFlows() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentPagerPosition.collectLatest { currentPagerPosition ->
                    binding.vpMain.currentItem = currentPagerPosition
                }
            }
        }
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, MakeTripActivity::class.java)
    }
}