package kr.co.dooribon.ui.existingtrip.tendency.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.api.remote.StoreTravelTendencyDTO
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.FragmentMemberBinding
import kr.co.dooribon.ui.existingtrip.tendency.adapter.MemberTripTypeAdapter
import kr.co.dooribon.ui.existingtrip.tendency.contract.TravelTendencyContract
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.MemberViewModel
import kr.co.dooribon.ui.traveltendencyresult.TravelTendencyResultActivity
import kr.co.dooribon.ui.triptendency.TripTendencyActivity
import kr.co.dooribon.utils.AutoClearBinding

/**
 * Data가 존재하는지 안하는지에 따라서 visibility를 바꾸면 된다
 * card_member_trip_type_test -> 내 여행성향이 아무것도 없을 때 보여져야 할 뷰
 * card_my_trip_type -> 내 여행 성향이 존재하는 경우 보여져야할 뷰
 * rv_member_trip_type -> 다른 이들의 여행 성향이 존재하는 경우 보여져야 할 뷰
 * cl_member_trip_type_nothing -> 다른 이들의 여행 성향이 존재하지 않는 경우 보여져야 할 뷰
 */
class MemberFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentMemberBinding>()

    private lateinit var memberTripTypeAdapter: MemberTripTypeAdapter

    private val viewModel by viewModels<MemberViewModel> {
        viewModelModule.provideMemberViewModelFactory()
    }

    private val travelTendencyLauncher =
        registerForActivityResult(TravelTendencyContract()) { result: StoreTravelTendencyDTO? ->
            result?.let { viewModel.initializeTravelTendencyResult(it) }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentMemberBinding.inflate(inflater, container, false).also { FragmentMemberBinding ->
            binding = FragmentMemberBinding
            arguments?.getString("tendency_groupId")?.let { GroupId ->
                viewModel.initializeMemberTendencyGroupId(
                    GroupId
                )
            }
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        binding.memberFragment = this
        memberTripTypeAdapter = MemberTripTypeAdapter(onItemDetailClicked = {
            onItemDetailClicked(it)
        })
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        observeMemberTendencyGroupId()
        observeOtherTravelTendencyResult()
        configureRecyclerView()

    }

    private fun configureRecyclerView() {
        binding.rvMemberTripOther.apply {
            adapter = memberTripTypeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeOtherTravelTendencyResult() {
        viewModel.otherTravelTendencyResult.observe(viewLifecycleOwner) {
            memberTripTypeAdapter.submitList(it)
        }
    }

    private fun observeMemberTendencyGroupId() {
        viewModel.memberTendencyGroupId.observe(viewLifecycleOwner) {
            viewModel.fetchGroupTravelTendency()
        }
    }

    fun navigateTravelTendencyTest() {
        val intent = Intent(requireContext(), TripTendencyActivity::class.java)
        intent.putExtra("groupId", viewModel.memberTendencyGroupId.value)
        travelTendencyLauncher.launch(intent)
    }

    private fun onItemDetailClicked(imageUrl: String) {
        val travelTendencyResultIntent =
            Intent(requireContext(), TravelTendencyResultActivity::class.java)
        travelTendencyResultIntent.putExtra("travelTendencyResultImageUrl", imageUrl)
        startActivity(travelTendencyResultIntent)
    }
}