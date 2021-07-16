package kr.co.dooribon.ui.newtrip.join

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.CreateTravelScheduleReq
import kr.co.dooribon.api.remote.CreateTravelScheduleRes
import kr.co.dooribon.application.MainApplication.Companion.apiModule
import kr.co.dooribon.databinding.FragmentParticipateJoinBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ParticipateJoinFragment : Fragment() {
    private lateinit var binding: FragmentParticipateJoinBinding
    var count = 0
    val TAG = "ParticipateJoinFragment"

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
        fullEditText()
        // groupId 받아서 마무리
        // TODO 수정 필요
//        apiModule.scheduleApi.createTravelSchedule(
//            "Test",
//            CreateTravelScheduleReq("1", Date(1020L), Date(1020L), "대한민국", "살려주세여")
//        )
//            .enqueue(object : Callback<CreateTravelScheduleRes> {
//                override fun onResponse(
//                    call: Call<CreateTravelScheduleRes>,
//                    response: Response<CreateTravelScheduleRes>
//                ) {
//                    if (response.isSuccessful) {
//                        Log.d(TAG, "일정 추가 완료")
//                    }
//                }
//
//                override fun onFailure(call: Call<CreateTravelScheduleRes>, t: Throwable) {
//
//                }
//            })
    }

    private fun fullEditText() {
        val code1 = binding.etCode1.text
        val code2 = binding.etCode2.text
        val code3 = binding.etCode3.text
        val code4 = binding.etCode4.text
        val code5 = binding.etCode5.text
        val code6 = binding.etCode6.text

        binding.etCode6.setOnClickListener {
            if (code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty() && code4.isNotEmpty() && code5.isNotEmpty() && code6.isNotEmpty()) {
                binding.btnParticipatePut.apply {
                    text = "입력하기"
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_white_8))
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.doo_ri_bon_orange
                        )
                    )
                }
                binding.btnParticipatePut.setOnClickListener {
                    val participatecheckFragment = ParticipateCheckFragment()
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.participate_fragment_container_view, participatecheckFragment)
                        .commitNow()
                }
            }
        }

    }
}
