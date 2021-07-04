package kr.co.dooribon.ui.newtrip

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityNewTravelBinding
import kr.co.dooribon.ui.newtrip.adapter.ImageData
import kr.co.dooribon.ui.newtrip.adapter.RecoImgAdapter

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewTravelBinding
    val tempImgs = mutableListOf<ImageData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_travel)

        binding.btStartNewTravel.setOnClickListener {
            val intent = Intent(this, DatePickActivity::class.java)
            startActivity(intent)
        }

        addData()
        setImgAdapter(tempImgs)
    }

    private fun setImgAdapter(imgList : List<ImageData>){
        val imgAdapter = RecoImgAdapter()
        val imgRV = binding.rvPreparedPhotos
        imgRV.adapter = imgAdapter
        imgRV.setHasFixedSize(false) // scroll view 적용 여부에 따라 변경
        //imgRV.addItemDecoration(RVItemDeco(10,10,10,10))
        imgAdapter.setItemList(imgList)
    }

    private fun addData(){
        for(i in 0 until 16){
            tempImgs.add(ImageData(R.drawable.ic_launcher_background))
        }
    }
}