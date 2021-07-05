package kr.co.dooribon.ui.newtrip.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticiCheckBinding

class ParticiCheckFragment :Fragment() {
    private lateinit var binding: FragmentParticiCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParticiCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forCheckClickEvent()
    }
    private fun forCheckClickEvent(){
        binding.btnParticiAgain.setOnClickListener {
            val participutFragment = ParticiPutFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.partici_fragment_container_view, participutFragment).commitNow()
        }
        // Todo : 맞아요! 버튼 누를 경우 화면 전환
    }

}