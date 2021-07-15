package kr.co.dooribon.ui.traveltendencyresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTravelTendencyResultBinding
import kr.co.dooribon.utils.debugSSong
import kr.co.dooribon.utils.dpToPixel
import kr.co.dooribon.utils.shortToast

class TravelTendencyResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTravelTendencyResultBinding

    private val viewModel by viewModels<TravelTendencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_travel_tendency_result)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        observeTravelTendencyResult()
        debugSSong(intent.getStringExtra("travelTendencyResultImageUrl"))

        intent.getStringExtra("travelTendencyResultImageUrl")?.let {
            viewModel.initializeTravelTendencyResultImageUrl(it)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.clSaveTravelTendencyResult.setOnClickListener {
            shortToast("저장 클릭!!!")
        }
    }

    private fun observeTravelTendencyResult() {
        viewModel.travelTendencyResultImageUrl.observe(this){
            debugSSong("heelo")
            Glide.with(this)
                .asBitmap()
                .load(it)
                .skipMemoryCache(true)
                .override(this.resources.displayMetrics.widthPixels,this.dpToPixel(1531))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.ivTravelTendencyResult)
        }
    }
}