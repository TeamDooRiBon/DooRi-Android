package kr.co.dooribon.ui.existingtrip.tendency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.databinding.FragmentMemberBinding
import kr.co.dooribon.domain.entity.MemberTripType
import kr.co.dooribon.ui.existingtrip.tendency.adapter.MemberTripTypeAdapter
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.addChip

class MemberFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentMemberBinding>()

    private lateinit var memberTripTypeAdapter : MemberTripTypeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMemberBinding.inflate(inflater,container,false).also { FragmentMemberBinding ->
        binding = FragmentMemberBinding
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.liChips.addChip("#안녕")
        binding.liChips.addChip("#반가워")
        binding.liChips.addChip("#무슨일 있어?")

        memberTripTypeAdapter = MemberTripTypeAdapter()

        binding.rvMemberTripOther.apply {
            adapter = memberTripTypeAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        memberTripTypeAdapter.submitList(listOf(
            MemberTripType("응애", listOf("#안녕","#반가워","#화이팅"),"송훈기","1"),
            MemberTripType("응애", listOf("#안녕","#반가워","#화이팅"),"송훈기","1"),
            MemberTripType("응애", listOf("#안녕","#반가워","#화이팅"),"송훈기","1")
        ))
    }
}