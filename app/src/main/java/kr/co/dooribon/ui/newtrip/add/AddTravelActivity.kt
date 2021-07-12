package kr.co.dooribon.ui.newtrip.add

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityNewTravelBinding
import kr.co.dooribon.dialog.TripTendencyTestExitDialog
import kr.co.dooribon.domain.entity.PickDatePair
import kr.co.dooribon.ui.newtrip.TravelPlanDoneActivity
import kr.co.dooribon.ui.newtrip.adapter.ImageData
import kr.co.dooribon.ui.newtrip.adapter.RecoImgAdapter
import kr.co.dooribon.ui.newtrip.add.contract.DatePickerActivityContract
import kr.co.dooribon.utils.RVItemDeco

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTravelBinding
    val tempImgs = mutableListOf<ImageData>()

    // ActivityContract
    private val datePickLauncher =
        registerForActivityResult(DatePickerActivityContract()) { result: PickDatePair? ->
            // 데이터가 반환되어서 왔을 때, 어떤 로직을 실행할지를 적어놓습니다.
            binding.apply {
                tvStartDate.text = result?.startDate ?: ""
                tvEndDate.text = result?.endDate ?: ""
            }
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
    }

    private fun imgAdapter(imgList: List<ImageData>) {
        val imgAdapter = RecoImgAdapter()
        val imgRV = binding.rvPreparedPhotos
        imgRV.adapter = imgAdapter
        imgRV.addItemDecoration(RVItemDeco(10, 10, 10, 10))
        imgAdapter.setItemList(imgList)
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

    private fun setQuestionDialog(){
        val bsDialog = Dialog(this)
        val sheetView = LayoutInflater.from(this).inflate(
            R.layout.dialog_trip_tendency_test_exit,
            this.findViewById(R.id.cl_exit_dialog_root)
        )
        sheetView.findViewById<TextView>(R.id.tv_exit_sub_description).text = "지금까지의 수정 정보는 저장되지 않습니다."
        sheetView.findViewById<TextView>(R.id.tv_exit_sub_description2).text = "수정을 취소하려면 오른쪽 버튼을 눌러주세요.ㅤㅤ"
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
}