package kr.co.dooribon.ui.existingtrip.tendency.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.FragmentDetailBinding
import kr.co.dooribon.domain.entity.AnswerQuestion
import kr.co.dooribon.domain.entity.ExpandableAnswerQuestion
import kr.co.dooribon.ui.existingtrip.tendency.adapter.TripDetailAdapter
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.DetailViewModel
import kr.co.dooribon.utils.AutoClearBinding

/**
 * Expandable RecyclerView 사용
 */
class DetailFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentDetailBinding>()

    private lateinit var detailAdapter: TripDetailAdapter

    private val viewModel by viewModels<DetailViewModel> {
        viewModelModule.provideDetailViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDetailBinding.inflate(inflater, container, false).also { FragmentDetailBinding ->
            binding = FragmentDetailBinding
            arguments?.getString("tendency_groupId")?.let { GroupId ->
                viewModel.initializeMemberTendencyGroupId(
                    GroupId
                )
            }
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        detailAdapter = TripDetailAdapter()
        configureRecyclerView()
        observeMemberTravelTendencyResult()
        viewModel.fetchGroupUserTravelTendencyCount()
    }

    private fun observeMemberTravelTendencyResult() {
        viewModel.membersTravelTendencyResult.observe(viewLifecycleOwner){
            val expandableList = mutableListOf<ExpandableAnswerQuestion>()
            synchronized(expandableList){
                it.forEach { AnswerQuestion ->
                    expandableList.add(ExpandableAnswerQuestion(ExpandableAnswerQuestion.PARENT,AnswerQuestion))
                }
            }
            detailAdapter.submitList(expandableList)
        }
    }

    private fun configureRecyclerView(){
        binding.rvDetail.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
        }
    }
}