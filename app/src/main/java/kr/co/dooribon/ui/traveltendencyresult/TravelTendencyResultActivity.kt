package kr.co.dooribon.ui.traveltendencyresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTravelTendencyResultBinding

class TravelTendencyResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTravelTendencyResultBinding

    private val viewModel by viewModels<TravelTendencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_travel_tendency_result)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        Log.d("fuck", intent.getStringExtra("travelTendencyResultImageUrl").toString())

        intent.getStringExtra("travelTendencyResultImageUrl")?.let {
            viewModel.initializeTravelTendencyResultImageUrl(it)
        }
    }
}