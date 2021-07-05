package kr.co.dooribon.ui.newtrip.add

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticiPutBinding

class ParticiPutFragment : Fragment() {
    private lateinit var binding: FragmentParticiPutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParticiPutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCode1.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode1.setBackgroundColor(Color.parseColor("#ecf2ff"))
            }

        }
        forPutClickEvent()
    }

    private fun forPutClickEvent() {
        binding.btnParticipatePut.setOnClickListener {
            val code1 = binding.etCode1.text
            val code2 = binding.etCode2.text
            val code3 = binding.etCode3.text
            val code4 = binding.etCode4.text
            val code5 = binding.etCode5.text
            val code6 = binding.etCode6.text
            if (code1.isNullOrBlank() || code2.isNullOrBlank() || code3.isNullOrBlank() || code4.isNullOrBlank() || code5.isNullOrBlank() || code6.isNullOrBlank()) {

            } else {
                binding.btnParticipatePut.setBackgroundColor(Color.parseColor("#FF7B51"))
                val particicheckFragment = ParticiCheckFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.partici_fragment_container_view, particicheckFragment).commitNow()
            }
        }
    }
}