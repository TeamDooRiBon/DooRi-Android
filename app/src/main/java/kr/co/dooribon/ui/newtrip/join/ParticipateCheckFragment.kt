package kr.co.dooribon.ui.newtrip.join

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentParticipateCheckBinding
import kr.co.dooribon.ui.home.HomeActivity
import kr.co.dooribon.ui.newtrip.join.viewmodel.ParticipateGroupViewModel
import kr.co.dooribon.utils.debugSSong
import kotlin.concurrent.fixedRateTimer

// TODO : SSong-develop 여기 작업하던 중임!
class ParticipateCheckFragment : Fragment() {
    private lateinit var binding: FragmentParticipateCheckBinding

    private val viewModel: ParticipateGroupViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentParticipateCheckBinding.inflate(inflater, container, false)
        debugSSong(arguments?.getString("existingGroupContents"))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnParticipateAgain.setOnClickListener {
            navigateToParticipateJoin()
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

    private fun navigateToParticipateJoin() {
        val participateJoinFragment = ParticipateJoinFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.participate_fragment_container_view, participateJoinFragment)
            .commitNow()
    }
}