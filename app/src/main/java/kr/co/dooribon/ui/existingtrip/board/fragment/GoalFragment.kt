package kr.co.dooribon.ui.existingtrip.board.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentGoalBinding
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardAdapter
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardListData

class GoalFragment : Fragment() {

    private lateinit var binding: FragmentGoalBinding
    private lateinit var dummyList: List<BoardListData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_goal, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDummyList()
        setBoardAdapter()
        setBgVisibility()
    }

    private fun setBgVisibility() {
        if (dummyList.isNotEmpty()) {
            binding.apply {
                ivFlag.visibility = View.GONE
                tvShareGoal.visibility = View.GONE
                tvGoalDetail.visibility = View.GONE
            }
        }
    }

    private fun setBoardAdapter() {
        val boardAdapter = BoardAdapter()
        val boardRV = binding.rvGoal
        boardAdapter.setItemList(dummyList)
        boardRV.adapter = boardAdapter
    }

    private fun setDummyList() {
        dummyList = listOf(
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("제주도 한라산 등산하기! 아침에 일찍 일어나서 꼭 갈거야 한라산...", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영")
        )
    }
}