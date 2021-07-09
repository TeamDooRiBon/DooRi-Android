package kr.co.dooribon.ui.newtrip.join

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticipateJoinBinding

class ParticipateJoinFragment : Fragment() {
    private lateinit var binding: FragmentParticipateJoinBinding
    var count =0
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
        // TODO : Edittext 6개 모두 채워졌을 경우 버튼 변경
        fullEditEvent()
    }

    private fun fullEditEvent() {
        val code1 = binding.etCode1.text
        val code2 = binding.etCode2.text
        val code3 = binding.etCode3.text
        val code4 = binding.etCode4.text
        val code5 = binding.etCode5.text
        val code6 = binding.etCode6.text
        binding.etCode1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                count ++
                if (count == 6) {
                    binding.btnParticipatePut.apply {
                        text = "입력완료"
                        setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.doo_ri_bon_orange
                            )
                        )
                    }
                } else {

                }
            }
        })
        binding.etCode2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                count ++
                if (count == 6) {
                    binding.btnParticipatePut.apply {
                        text = "입력완료"
                        setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.doo_ri_bon_orange
                            )
                        )
                    }
                } else {

                }
            }
        })
        binding.etCode3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                count ++
                if (count == 6) {
                    binding.btnParticipatePut.apply {
                        text = "입력완료"
                        setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.doo_ri_bon_orange
                            )
                        )
                    }
                } else {

                }
            }
        })
        binding.etCode4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                count ++
                if (count == 6) {
                    binding.btnParticipatePut.apply {
                        text = "입력완료"
                        setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.doo_ri_bon_orange
                            )
                        )
                    }
                } else {

                }
            }
        })
        binding.etCode5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                count ++
                if (count == 6) {
                    binding.btnParticipatePut.apply {
                        text = "입력완료"
                        setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.doo_ri_bon_orange
                            )
                        )
                    }
                } else {

                }
            }
        })
        binding.etCode6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                count ++
                if (count == 6) {
                    binding.btnParticipatePut.apply {
                        text = "입력완료"
                        setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.doo_ri_bon_orange
                            )
                        )
                    }
                } else {

                }
            }
        })
    }
}
