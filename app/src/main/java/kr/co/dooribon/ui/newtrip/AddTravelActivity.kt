package kr.co.dooribon.ui.newtrip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityNewTravelBinding
import kr.co.dooribon.ui.newtrip.adapter.DiffRecoImgAdapter
import kr.co.dooribon.ui.newtrip.adapter.ImageData
import kr.co.dooribon.utils.RVItemDeco

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTravelBinding
    val tempImgs = mutableListOf<ImageData>()
    private var prevPos = -1 // 이전에 체크된 position 값 들고 있음, 이전에 체크된 곳은 해제해주기 위함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_travel)

        intent.extras?.get("startDate")?.let {
            binding.tvStartDate.text = it.toString()
        }
        intent.extras?.get("endDate")?.let {
            binding.tvEndDate.text = it.toString()
        }

        backBtnClickListener()

        binding.btStartNewTravel.setOnClickListener {
            val intent = Intent(this, TravelPlanDoneActivity::class.java)
            startActivity(intent)
        }

        binding.btAddDate.setOnClickListener {
            val intent = Intent(this, DatePickActivity::class.java)
            startActivity(intent)
        }

        resetData(-1) // 수정할 값이 없으므로 -1 대입
        setDiffImgAdapter(tempImgs)
    }

    private fun setDiffImgAdapter(imgList: List<ImageData>) {
        val imgAdapter = DiffRecoImgAdapter()
        val imgRV = binding.rvPreparedPhotos
        imgRV.adapter = imgAdapter
        imgRV.addItemDecoration(RVItemDeco(10, 10, 10, 10))
        imgAdapter.setImgData(imgList)

        imgAdapter.setItemClickListener(object : DiffRecoImgAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.e("Clicked!!", "Clicked!!")
                view.setBackgroundResource(R.drawable.bg_selected_img_stroke)
//                prevPos = position
//                resetData(position)
//                imgAdapter.setImgData(tempImgs)
            }
        })
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
}