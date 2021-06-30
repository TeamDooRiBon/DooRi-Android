package kr.co.dooribon.presenter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityNewTravelBinding

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewTravelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_travel)

        binding.btStartNewTravel.setOnClickListener {
            val intent = Intent(this, DatePickActivity::class.java)
            startActivity(intent)
        }
    }
}