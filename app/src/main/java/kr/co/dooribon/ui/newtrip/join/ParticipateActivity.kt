package kr.co.dooribon.ui.newtrip.join

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.ActivityParticipateBinding
import kr.co.dooribon.ui.newtrip.join.viewmodel.ParticipateGroupViewModel
import kr.co.dooribon.utils.debugSSong

class ParticipateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticipateBinding

    private val viewModel by viewModels<ParticipateGroupViewModel> {
        viewModelModule.provideParticipateGroupViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        debugSSong(viewModel.code1Text)
        backBtnClickListener()
    }

    private fun backBtnClickListener() {
        binding.ivParticiBack.setOnClickListener {
            finish()
        }
    }
}