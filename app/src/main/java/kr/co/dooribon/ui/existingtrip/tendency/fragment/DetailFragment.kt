package kr.co.dooribon.ui.existingtrip.tendency.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.databinding.FragmentDetailBinding
import kr.co.dooribon.domain.entity.AnswerQuestion
import kr.co.dooribon.domain.entity.ExpandableAnswerQuestion
import kr.co.dooribon.ui.existingtrip.tendency.adapter.TripDetailAdapter
import kr.co.dooribon.utils.AutoClearBinding

/**
 * Expandable RecyclerView 사용
 */
class DetailFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentDetailBinding>()

    private lateinit var detailAdapter: TripDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentDetailBinding.inflate(inflater, container, false).also { FragmentDetailBinding ->
            binding = FragmentDetailBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        detailAdapter = TripDetailAdapter()

        binding.rvDetail.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = detailAdapter
            addItemDecoration(DividerItemDecoration(requireContext(),1))
        }

        // Dummy Data
        detailAdapter.submitList(
            listOf(
                ExpandableAnswerQuestion(
                    ExpandableAnswerQuestion.PARENT, AnswerQuestion.Question(
                        1, "훈기를살려줄까?",
                        listOf(AnswerQuestion.Question.ChildQuestion(1, "살려줄까?", 3),
                            AnswerQuestion.Question.ChildQuestion(2, "살리지말자!", 4),
                            AnswerQuestion.Question.ChildQuestion(3, "중립이야", 2))
                    )
                ),
                ExpandableAnswerQuestion(
                    ExpandableAnswerQuestion.PARENT, AnswerQuestion.Question(
                        2, "예진이살려줄까?",
                        listOf(AnswerQuestion.Question.ChildQuestion(1, "살려줄까?", 8),
                            AnswerQuestion.Question.ChildQuestion(2, "살리지말자!", 9),
                            AnswerQuestion.Question.ChildQuestion(3, "중립이야", 10))
                    )
                ),ExpandableAnswerQuestion(
                    ExpandableAnswerQuestion.PARENT, AnswerQuestion.Question(
                        3, "원중이살려줄까?",
                        listOf(AnswerQuestion.Question.ChildQuestion(1, "살려줄까?", 100),
                            AnswerQuestion.Question.ChildQuestion(2, "살리지말자!", 101),
                            AnswerQuestion.Question.ChildQuestion(3, "중립이야", 102))
                    )
                ),
                ExpandableAnswerQuestion(
                    ExpandableAnswerQuestion.PARENT, AnswerQuestion.Question(
                        4, "왜 다죽일까?",
                        listOf(AnswerQuestion.Question.ChildQuestion(1, "살려줄까?", 3),
                            AnswerQuestion.Question.ChildQuestion(2, "살리지말자!", 4),
                            AnswerQuestion.Question.ChildQuestion(3, "중립이야", 2))
                    )
                )
            )
        )

        Log.d("helll",detailAdapter.itemCount.toString())
    }
}