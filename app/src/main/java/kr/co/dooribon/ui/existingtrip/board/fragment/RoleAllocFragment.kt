package kr.co.dooribon.ui.existingtrip.board.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
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
import kr.co.dooribon.api.remote.BoardContentDTO
import kr.co.dooribon.api.remote.InquireTravelBoardRes
import kr.co.dooribon.application.MainApplication
import kr.co.dooribon.databinding.FragmentBoardBottomBinding
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoleAllocFragment : Fragment() {

    private lateinit var binding: FragmentBoardBottomBinding

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
        onAddBtnClickListener()
        getCheckListData(arguments?.getString("groupId").toString())
    }

    /* 서버로부터 데이터 받아오고, 리사이클러뷰 어댑터 호출 */
    private fun getCheckListData(groupId: String) {
        MainApplication.apiModule.boardApi.inquireTravelBoard(groupId, "role").enqueue(object :
            Callback<InquireTravelBoardRes> {
            override fun onResponse(
                call: Call<InquireTravelBoardRes>,
                response: Response<InquireTravelBoardRes>
            ) {
                if (response.isSuccessful) {
                    setBoardAdapter(response.body()?.data ?: emptyList())
                    response.body()?.data.let {
                        makeImageGone()
                    }
                }
            }

            override fun onFailure(call: Call<InquireTravelBoardRes>, t: Throwable) {
                Log.e("getGoalBoardData onFailure", t.message.toString())
            }
        })
    }

    /* 서버에서 수신한 것에 값이 들어있을 때, 디폴트로 들어가있는 값을 지운다. */
    private fun makeImageGone() {
        binding.apply {
            ivTopic.visibility = View.GONE
            tvMainTodo.visibility = View.GONE
            tvSubTodo.visibility = View.GONE
        }
    }

    /* 추가하기 버튼 클릭 이벤트 처리 함수 */
    private fun onAddBtnClickListener() {
        binding.btAdd.setOnClickListener {
            val editDlg = Dialog(requireContext())
            editDlg.apply {
                setContentView(R.layout.dialog_edit_travel)
                findViewById<TextView>(R.id.tv_category).text = "역할 분담"
                findViewById<TextView>(R.id.tv_category_detail).text = "이번 여행에서 나는 이런 역할을 담당하게!"
                findViewById<EditText>(R.id.et_add_content).hint = "Ex. 인생사진은 나한테 맡겨!"
                findViewById<ImageView>(R.id.iv_category).setBackgroundResource(R.drawable.ic_icon_board_role_active)
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
            tvTopic.text = getString(R.string.our_role)
            ivTopic.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_person_with_cloud
                )
            )
            tvMainTodo.text = getString(R.string.set_role_first)
            tvSubTodo.text = getString(R.string.set_role_first_detail)
        }
    }

    private fun setBoardAdapter(data: List<BoardContentDTO>) {
        val boardAdapter = BoardAdapter()
        val boardRV = binding.rvTodoList
        boardAdapter.setItemList(data)
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
            findViewById<TextView>(R.id.tv_add_board_category).text = "역할 분담"
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
}