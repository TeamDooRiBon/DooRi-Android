package kr.co.dooribon.ui.existingtrip.tendency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.FragmentMemberBinding
import kr.co.dooribon.domain.entity.MemberTripType
import kr.co.dooribon.ui.existingtrip.tendency.adapter.MemberTripTypeAdapter
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.MemberViewModel
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.addChip

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

        binding.liChips.addChip("#안녕")
        binding.liChips.addChip("#반가워")
        binding.liChips.addChip("#무슨일 있어?")

        memberTripTypeAdapter = MemberTripTypeAdapter()

        binding.rvMemberTripOther.apply {
            adapter = memberTripTypeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        memberTripTypeAdapter.submitList(
            listOf(
                MemberTripType("응애", listOf("#안녕", "#반가워", "#화이팅"), "송훈기", "1"),
                MemberTripType("응애", listOf("#안녕", "#반가워", "#화이팅"), "송훈기", "1"),
                MemberTripType("응애", listOf("#안녕", "#반가워", "#화이팅"), "송훈기", "1")
            )
        )
    }
}