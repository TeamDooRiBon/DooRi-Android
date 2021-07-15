package kr.co.dooribon.ui.newtrip.join

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticipateJoinBinding

class ParticipateJoinFragment : Fragment() {
    private lateinit var binding: FragmentParticipateJoinBinding
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParticipateJoinBinding.inflate(inflater, container, false)
        return binding.root

    }

    // TODO : edittext 포커스 이동
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCode1.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode1.setBackgroundResource(R.drawable.text_focus)
            } else {
                binding.etCode1.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode2.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode2.setBackgroundResource(R.drawable.text_focus)
            } else {
                binding.etCode2.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode3.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode3.setBackgroundResource(R.drawable.text_focus)
            } else {
                binding.etCode3.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode4.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode4.setBackgroundResource(R.drawable.text_focus)
            } else {
                binding.etCode4.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode5.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode5.setBackgroundResource(R.drawable.text_focus)
            } else {
                binding.etCode5.setBackgroundResource(R.drawable.text_round)
            }

        }
        binding.etCode6.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.etCode6.setBackgroundResource(R.drawable.text_focus)
            } else {
                binding.etCode6.setBackgroundResource(R.drawable.text_round)
            }

        }
        btnFullCheck()
    }

    // todo : 버튼 다시 수정 진행
    private fun btnFullCheck(){
        binding.etCode6.setOnClickListener {
            if(binding.etCode1.text.isNotEmpty() && binding.etCode2.text.isNotEmpty() && binding.etCode3.text.isNotEmpty() && binding.etCode4.text.isNotEmpty() && binding.etCode5.text.isNotEmpty()){
                count ++
                Log.e("tmp", "count 되는지 확인")
            }
            if(count == 1){
                binding.btnParticipatePut.apply {
                    isEnabled = true
                    text = "입력하기"
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_white_8))
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.doo_ri_bon_orange
                        )
                    )
                }
            }
        }

        binding.btnParticipatePut.setOnClickListener {
            val participatecheckFragment = ParticipateCheckFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.participate_fragment_container_view, participatecheckFragment)
                .commitNow()
        }

    }
}
