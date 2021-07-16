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
import kr.co.dooribon.api.remote.*
import kr.co.dooribon.application.MainApplication
import kr.co.dooribon.databinding.FragmentBoardBottomBinding
import kr.co.dooribon.ui.existingtrip.board.fragment.adapter.BoardAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MustKnowFragment : Fragment() {

    private lateinit var binding: FragmentBoardBottomBinding
    private var dataList = listOf<BoardContentDTO>()

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
                    sendData(findViewById<EditText>(R.id.et_add_content)?.text.toString())
                    dismiss()
                }
                show()
            }
        }
    }

    /* 편집 데이터 전송 */
    private fun sendEditData(sendText: String, position: Int) {
        MainApplication.apiModule.boardApi.editTravelBoard(
            EditTravelBoardReq(sendText),
            arguments?.getString("groupId").toString(),
            "know",
            dataList[position].boardId
        ).enqueue(object : Callback<EditTravelBoardRes> {
            override fun onResponse(
                call: Call<EditTravelBoardRes>,
                response: Response<EditTravelBoardRes>
            ) {
                if (response.isSuccessful) {
                    Log.e("response Success", response.body()?.message.toString())
                } else {
                    Log.e("response Fail", response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<EditTravelBoardRes>, t: Throwable) {
                Log.e("sendEditData onFailure", t.message.toString())
            }

        })
    }

    private fun sendData(sendText : String){
        MainApplication.apiModule.boardApi.createTravelBoard(
            arguments?.getString("groupId").toString(),
            "know",
            CreateTravelBoardReq(
                sendText
            )
        ).enqueue(object : Callback<CreateTravelBoardRes>{
            override fun onResponse(
                call: Call<CreateTravelBoardRes>,
                response: Response<CreateTravelBoardRes>
            ) {
                if(response.isSuccessful){
                    Log.e("success", "must know ${response.body()?.message.toString()}")
                }else{
                    Log.e("createTravel", "Not Success")
                }
            }

            override fun onFailure(call: Call<CreateTravelBoardRes>, t: Throwable) {
                Log.e("sendData", t.message.toString())
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

    private fun getCheckListData(groupId: String) {
        MainApplication.apiModule.boardApi.inquireTravelBoard(groupId, "know").enqueue(object :
            Callback<InquireTravelBoardRes> {
            override fun onResponse(
                call: Call<InquireTravelBoardRes>,
                response: Response<InquireTravelBoardRes>
            ) {
                if (response.isSuccessful) {
                    setBoardAdapter(response.body()?.data ?: emptyList())
                    dataList = dataList.plus(response.body()?.data ?: emptyList())
                    if (response.body()?.data?.isNotEmpty() == true) {
                        makeImageGone()
                    }
                }
            }

            override fun onFailure(call: Call<InquireTravelBoardRes>, t: Throwable) {
                Log.e("getGoalBoardData onFailure", t.message.toString())
            }
        })
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
                    view.findViewById<TextView>(R.id.tv_board_writer).text.toString(),
                    position
                )
            }
        })
    }

    /* 리사이클러뷰 아이템 클릭 리스너
    * todoText : 다이얼로그에 뜰 내용
    * writer : 작성자
    *  */
    private fun itemClickListener(todoText: String, writer: String, position : Int) {
        val bsDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
        val sheetView = LayoutInflater.from(requireContext()).inflate(
            R.layout.bottomsheet_add_board_list,
            requireActivity().findViewById(R.id.cl_add_board_bottom_sheet_root)
        )
        sheetView.apply {
            findViewById<Button>(R.id.btn_add_board_delete).setOnClickListener { // TODO 삭제하는 기능 추가해야함.
                deleteData(position)
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
                        sendEditData(
                            findViewById<EditText>(R.id.et_add_content).text.toString(),
                            position
                        )
                        dismiss()
                    }
                    show()
                }
            }
        }
        bsDialog.setContentView(sheetView)
        bsDialog.show()
    }

    private fun deleteData(position: Int) {
        MainApplication.apiModule.boardApi.deleteTravelBoard(
            arguments?.getString("groupId").toString(),
            "know",
            dataList[position].boardId
        ).enqueue(object : Callback<DeleteTravelBoardRes> {
            override fun onResponse(
                call: Call<DeleteTravelBoardRes>,
                response: Response<DeleteTravelBoardRes>
            ) {
                if (response.isSuccessful) {
                    Log.e("isNotSuccessful", response.body()?.message.toString())
                } else {
                    Log.e("isNotSuccessful", response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<DeleteTravelBoardRes>, t: Throwable) {
                Log.e("deleteData onFailure", t.message.toString())
            }
        })
    }
}