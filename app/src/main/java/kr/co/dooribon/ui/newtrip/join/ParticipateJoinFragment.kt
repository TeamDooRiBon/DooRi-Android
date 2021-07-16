package kr.co.dooribon.ui.newtrip.join

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticipateJoinBinding
import kr.co.dooribon.ui.newtrip.join.extension.*
import kr.co.dooribon.ui.newtrip.join.viewmodel.ParticipateGroupViewModel
import kr.co.dooribon.utils.debugSSong
import java.util.*

class ParticipateJoinFragment : Fragment() {
    private lateinit var binding: FragmentParticipateJoinBinding
    var count = 0

    private val viewModel: ParticipateGroupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParticipateJoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etCode1.apply {
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.etCode1.setBackgroundResource(R.drawable.text_focus)
                } else {
                    binding.etCode1.setBackgroundResource(R.drawable.text_round)
                }
            }
            initializeCode1Listener(viewModel)
        }
        binding.etCode2.apply {
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.etCode2.setBackgroundResource(R.drawable.text_focus)
                } else {
                    binding.etCode2.setBackgroundResource(R.drawable.text_round)
                }
            }
            initializeCode2Listener(viewModel)
        }
        binding.etCode3.apply {
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.etCode3.setBackgroundResource(R.drawable.text_focus)
                } else {
                    binding.etCode3.setBackgroundResource(R.drawable.text_round)
                }
            }
            initializeCode3Listener(viewModel)
        }
        binding.etCode4.apply {
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.etCode4.setBackgroundResource(R.drawable.text_focus)
                } else {
                    binding.etCode4.setBackgroundResource(R.drawable.text_round)
                }
            }
            initializeCode4Listener(viewModel)
        }
        binding.etCode5.apply {
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.etCode5.setBackgroundResource(R.drawable.text_focus)
                } else {
                    binding.etCode5.setBackgroundResource(R.drawable.text_round)
                }
            }
            initializeCode5Listener(viewModel)
        }
        binding.etCode6.apply {
            setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    binding.etCode6.setBackgroundResource(R.drawable.text_focus)
                } else {
                    binding.etCode6.setBackgroundResource(R.drawable.text_round)
                }
            }
            initializeCode6Listener(viewModel)
        }

        fullEditText()

        binding.btnParticipatePut.setOnClickListener {

        }
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

    companion object {
        private const val TAG = "ParticipateJoinFragment"
    }
}
