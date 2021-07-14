package kr.co.dooribon.ui.existingtrip.tendency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.FragmentTendencyBinding
import kr.co.dooribon.ui.existingtrip.tendency.extension.initializeTendencyNavigation
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.TendencyViewModel
import kr.co.dooribon.ui.existingtrip.viewmodel.ExistingTripViewModel
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.debugE
import kr.co.dooribon.utils.initializeTab

class TendencyFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentTendencyBinding>()

    private val viewModel by viewModels<TendencyViewModel>{
        viewModelModule.provideTendencyViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentTendencyBinding.inflate(inflater, container, false)
        .also { FragmentTendencyBinding ->
            binding = FragmentTendencyBinding
            arguments?.getString("tendency_groupId")?.let { GroupId ->
                viewModel.initializeMemberTendencyGroupId(
                    GroupId
                )
            }
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureTabNavigation()
        observeTendencyGroupId()
    }

    private fun observeTendencyGroupId() {
        viewModel.memberTendencyGroupId.observe(viewLifecycleOwner){
            // 여기서 api Call들을 해주면 될거같음
            viewModel.fetchGroupTravelTendency()
        }
    }

    private fun configureTabNavigation() {
        binding.tabTendency.initializeTab(listOf("우리들", "살펴보기"))
        binding.tabTendency.initializeTendencyNavigation(childFragmentManager)
    }
}