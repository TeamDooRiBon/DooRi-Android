package kr.co.dooribon.ui.maketrip

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityMakeTripBinding
import kr.co.dooribon.utils.base.BaseActivity

class MakeTripActivity : BaseActivity<ActivityMakeTripBinding>(R.layout.activity_make_trip) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagerAdapter = SlidePagerAdapter(this)
        binding.vpMain.adapter = pagerAdapter
        binding.btnNext.setOnClickListener {
            binding.vpMain.currentItem = 1
        }
    }

    companion object {
        fun intent(context: Context): Intent = Intent(context, MakeTripActivity::class.java)
    }
}