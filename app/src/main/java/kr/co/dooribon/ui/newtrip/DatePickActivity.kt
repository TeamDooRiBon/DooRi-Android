package kr.co.dooribon.ui.newtrip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityDatePickBinding

class DatePickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatePickBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_date_pick)
    }

}