package kr.co.dooribon.ui.existingtrip.board.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentBoardBottomBinding
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardAdapter
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardListData

class MustKnowFragment : Fragment() {

    private lateinit var binding: FragmentBoardBottomBinding

    //private lateinit var dummyList: List<BoardListData>
    private var dummyList = listOf<BoardListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_board_bottom, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentDetails()
        //setDummyList()
        setBoardAdapter()
        setBgVisibility()
        onAddBtnClickListener()
    }

    /* 추가하기 버튼 클릭 이벤트 처리 함수 */
    private fun onAddBtnClickListener() {
        binding.btAdd.setOnClickListener {
            val editDlg = Dialog(requireContext())
            editDlg.apply {
                setContentView(R.layout.dialog_edit_travel)
                findViewById<TextView>(R.id.tv_category).text = "꼭 알아줘"
                findViewById<TextView>(R.id.tv_category_detail).text =
                    "이번여행에 함께하는 사람들에게\n나에 대해 꼭 알리고 싶은 것을 작성해주세요!"
                findViewById<EditText>(R.id.et_add_content).hint =
                    "Ex. 밝으면 잘 못 자기 때문에 꼭 불은 끄고 잤으면 좋겠어:)"
                findViewById<ImageView>(R.id.iv_category).setBackgroundResource(R.drawable.ic_icon_board_aim_active)
                findViewById<Button>(R.id.bt_edit_travel_cancel).setOnClickListener {
                    dismiss()
                }
                findViewById<Button>(R.id.bt_edit_travel_ok).setOnClickListener {
                    dismiss()
                }
                show()
            }
        }
    }

    /***
     * 한 프래그먼트를 재활용해서 사용하기 때문에
     * 각 탭 레이아웃에 맞는 텍스트와 이미지를 넣어줘야 한다.
     */
    private fun setFragmentDetails() {
        binding.apply {
            tvTopic.text = getString(R.string.please_know_this)
            ivTopic.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_circle_check
                )
            )
            tvMainTodo.text = getString(R.string.share_first)
            tvSubTodo.text = getString(R.string.share_first_detail)
        }
    }

    private fun setBgVisibility() {
        if (dummyList.isNotEmpty()) {
            binding.apply {
                ivTopic.visibility = View.GONE
                tvMainTodo.visibility = View.GONE
                tvSubTodo.visibility = View.GONE
            }
        }
    }

    private fun setBoardAdapter() {
        val boardAdapter = BoardAdapter()
        val boardRV = binding.rvTodoList
        //boardAdapter.setItemList(dummyList)
        boardRV.adapter = boardAdapter
        onBoardItemClickListener(boardAdapter)
    }

    private fun onBoardItemClickListener(adapter: BoardAdapter) {
        adapter.setItemClickListener(object : BoardAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                itemClickListener(
                    view.findViewById<TextView>(R.id.tv_board_main).text.toString(),
                    view.findViewById<TextView>(R.id.tv_board_writer).text.toString()
                )
            }
        })
    }

    /* 리사이클러뷰 아이템 클릭 리스너
    * todoText : 다이얼로그에 뜰 내용
    * writer : 작성자
    *  */
    private fun itemClickListener(todoText: String, writer: String) {
        val bsDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
        val sheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottomsheet_add_board_list,
            requireActivity().findViewById(R.id.cl_add_board_bottom_sheet_root)
        )
        sheetView.apply {
            findViewById<Button>(R.id.btn_add_board_delete).setOnClickListener { // TODO 삭제하는 기능 추가해야함.
                bsDialog.dismiss()
            }
            findViewById<TextView>(R.id.tv_add_board_main_todo).text = todoText
            findViewById<TextView>(R.id.tv_add_board_writer).text = writer
            findViewById<TextView>(R.id.tv_add_board_category).text = "꼭 알아줘"
            findViewById<Button>(R.id.btn_add_board_edit).setOnClickListener {
                val editDlg = Dialog(requireContext())
                editDlg.apply {
                    setContentView(R.layout.dialog_edit_travel)
                    findViewById<TextView>(R.id.tv_category).text = "여행 목표"
                    findViewById<TextView>(R.id.tv_category_detail).text =
                        "이번 여행의 목표를 함께 공유하세요!"
                    findViewById<EditText>(R.id.et_add_content).hint = "Ex. 인생사진 찍어오기!"
                    findViewById<ImageView>(R.id.iv_category).setBackgroundResource(R.drawable.ic_icon_board_goal_active)
                    findViewById<Button>(R.id.bt_edit_travel_cancel).setOnClickListener {
                        dismiss()
                    }
                    findViewById<Button>(R.id.bt_edit_travel_ok).setOnClickListener {
                        dismiss()
                    }
                    show()
                }
            }
        }
        bsDialog.setContentView(sheetView)
        bsDialog.show()
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