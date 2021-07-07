package kr.co.dooribon.ui.triptendency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTripTendencyBinding

class TripTendencyActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTripTendencyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_trip_tendency)
    }
}