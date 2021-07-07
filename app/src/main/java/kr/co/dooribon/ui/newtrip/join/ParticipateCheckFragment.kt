package kr.co.dooribon.ui.newtrip.join

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticipateCheckBinding
import kr.co.dooribon.ui.home.HomeActivity
import kotlin.concurrent.fixedRateTimer

class ParticipateCheckFragment : Fragment() {
    private lateinit var binding: FragmentParticipateCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParticipateCheckBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnParticipateAgain.setOnClickListener {
            val participatejoinFragment = ParticipateJoinFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.participate_fragment_container_view, participatejoinFragment)
                .commitNow()
        }
        binding.btnParticipateYes.setOnClickListener {
            val dl = DoneJoinDialog(this)
            dl.start()
            fixedRateTimer("Change Indicator", false, 0L, 60 * 1000) {

            }
            Handler(Looper.getMainLooper()).postDelayed({
                //changeIndicator()
                val intent = Intent(getActivity(), HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // activity back stack 모두 제거
                startActivity(intent)
            }, 3000)
        }

    }


}