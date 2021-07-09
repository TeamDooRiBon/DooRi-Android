package kr.co.dooribon.ui.existingtrip.board.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentCheckListBinding
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardAdapter
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardListData

class CheckListFragment : Fragment() {

    private lateinit var binding: FragmentCheckListBinding
    private lateinit var dummyList: List<BoardListData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_list, container, false)
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
            BoardListData("먹을 것 챙기기", "김민영"),
            BoardListData("여권 챙기기", "김민영"),
            BoardListData("필름 카메라", "김민영"),
            BoardListData("여행사 전화하기", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영"),
            BoardListData("인생 사진 찍어오기!", "김민영")
        )
    }
}