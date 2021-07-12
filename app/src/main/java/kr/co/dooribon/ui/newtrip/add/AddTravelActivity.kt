package kr.co.dooribon.ui.newtrip.add

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityNewTravelBinding
import kr.co.dooribon.domain.entity.PickDatePair
import kr.co.dooribon.ui.newtrip.TravelPlanDoneActivity
import kr.co.dooribon.ui.newtrip.adapter.ImageData
import kr.co.dooribon.ui.newtrip.adapter.RecoImgAdapter
import kr.co.dooribon.ui.newtrip.add.contract.DatePickerActivityContract
import kr.co.dooribon.utils.RVItemDeco

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTravelBinding
    val tempImgs = mutableListOf<ImageData>()

    //아래 변수들이 모두 true가 돼야 새로운 여행 시작하기 버튼 활성화 된다.
    private var etTravelNameNotEmpty = false // 여행 이름
    private var etTravelPlaceNotEmpty = false // 여행 위치
    private var tvCalendarNotEmpty = false // 여행 날짜
    private var ivChecked = false // 추천 이미지 체크

    // ActivityContract
    private val datePickLauncher =
        registerForActivityResult(DatePickerActivityContract()) { result: PickDatePair? ->
            // 데이터가 반환되어서 왔을 때, 어떤 로직을 실행할지를 적어놓습니다.
            binding.apply {
                tvStartDate.text = result?.startDate ?: ""
                tvEndDate.text = result?.endDate ?: ""
            }
            setCalendarData() // 캘린더에서 받은 값 액티비티에 반영
            tvCalendarNotEmpty = true // 여행 날짜 입력 플래그 true로 설정
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_travel)

        backBtnClickListener()

        binding.btStartNewTravel.setOnClickListener {
            val intent = Intent(this, TravelPlanDoneActivity::class.java)
            startActivity(intent)
        }

        binding.btAddDate.setOnClickListener {
            val intent = Intent(this, DatePickActivity::class.java)
            datePickLauncher.launch(intent)
        }

        resetData(-1) // 수정할 값이 없으므로 -1 대입
        imgAdapter(tempImgs)
        chkEditTextInput()
    }

    /* 캘린더에서 데이터 주면 뷰에 받아서 뷰에 적용함 */
    private fun setCalendarData() {
        if (chkDateSelected()) {
            Log.e("c체크", "체크")
            binding.btAddDate.apply {
                setBackgroundResource(R.drawable.bg_add_date_gray_btn)
                text = "+ 날짜 수정하기"
            }
            binding.tvStartDate.setBackgroundResource(R.drawable.bg_text_gray_stroke)
            binding.tvEndDate.setBackgroundResource(R.drawable.bg_text_gray_stroke)
        }
    }

    /* 날짜 텍스트가 잘 들어와있는지 확인하는 부분 */
    private fun chkDateSelected() =
        binding.tvStartDate.text.isNotEmpty() && binding.tvEndDate.text.isNotEmpty()

    private fun imgAdapter(imgList: List<ImageData>) {
        val imgAdapter = RecoImgAdapter()
        val imgRV = binding.rvPreparedPhotos
        imgRV.adapter = imgAdapter
        imgRV.addItemDecoration(RVItemDeco(10, 10, 10, 10))
        imgAdapter.setItemList(imgList)
        onImageItemClickListener(imgAdapter)
    }

    private fun resetData(pos: Int) {
        tempImgs.clear()
        for (i in 0 until 16) {
            if (pos == i) {
                tempImgs.add(ImageData(R.drawable.ic_launcher_background, true))
            } else {
                tempImgs.add(ImageData(R.drawable.ic_launcher_background))
            }
        }
    }

    private fun backBtnClickListener() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onBackPressed() {
        setQuestionDialog()
    }

    private fun setQuestionDialog() {
        val bsDialog = Dialog(this)
        val sheetView = LayoutInflater.from(this).inflate(
            R.layout.dialog_trip_tendency_test_exit,
            this.findViewById(R.id.cl_exit_dialog_root)
        )
        sheetView.findViewById<TextView>(R.id.tv_exit_sub_description).text =
            "지금까지의 수정 정보는 저장되지 않습니다."
        sheetView.findViewById<TextView>(R.id.tv_exit_sub_description2).text =
            "수정을 취소하려면 오른쪽 버튼을 눌러주세요.ㅤㅤ"
        sheetView.findViewById<Button>(R.id.btn_no_exit).setOnClickListener {
            bsDialog.dismiss()
        }
        sheetView.findViewById<Button>(R.id.btn_exit).setOnClickListener {
            bsDialog.dismiss()
            finish()
        }
        bsDialog.setContentView(sheetView)
        bsDialog.show()
    }

    /* 여행 이름과 위치를 적었는지 확인하는 함수 */
    private fun chkEditTextInput() {
        binding.apply {
            etTravelName.addTextChangedListener {
                etTravelNameNotEmpty = etTravelName.text.isNotEmpty()
            }
            etTravelPlace.addTextChangedListener {
                etTravelPlaceNotEmpty = etTravelName.text.isNotEmpty()
            }
        }
    }

    private fun onImageItemClickListener(adapter: RecoImgAdapter) {
        adapter.setItemClickListener(object : RecoImgAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                ivChecked = true
                adapter.notifyItemChanged(adapter.prevClickedImgPos)
                adapter.isRemoveBgBinding = true
                adapter.modifyImgBg(view, false)
                adapter.prevClickedImgPos = position
            }
        })
    }
}