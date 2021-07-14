package kr.co.dooribon.ui.newtrip.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemRecommendedPhotosBinding

class RecoImgAdapter : RecyclerView.Adapter<RecoImgAdapter.ImgViewHolder>() {

    private var imgUrls = mutableListOf<String>()
    var prevClickedImgPos = -1 // 이전에 클릭된 이미지 위치
    var isRemoveBgBinding = false // 뒤에 배경을 지울 때 이 변수를 true로 바꿔 배경을 지운다
    private lateinit var itemClickListener: ItemClickListener

    // 클릭 interface
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecommendedPhotosBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recommended_photos, parent, false)
        return ImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        holder.bind(imgUrls[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

        // 이전에 클릭한 뷰를 지우는 부분
        // position!=prevClickedImgPos는 같은 곳을 두 번 클릭했을 때 bg가 지워지지 않게 해준다.
        if (isRemoveBgBinding && position != prevClickedImgPos) {
            modifyImgBg(holder.itemView, true) // 뒤에 배경 제거
            isRemoveBgBinding = false
        }
    }

    /**
     * 클릭 시 배경을 넣고 빼는 것을 구현하는 함수
     * isRemove가 true면 뒤 배경을 빼고,
     * false면 뒤에 배경을 넣는다.
     * */
    fun modifyImgBg(view: View, isRemove: Boolean) {
        if (isRemove) {
            Log.e("igRemove", isRemove.toString())
            view.findViewById<ImageView>(R.id.iv_reco_image).setBackgroundResource(0)
            view.findViewById<ImageView>(R.id.iv_selected_img).visibility = View.INVISIBLE
        } else {
            Log.e("igRemove", isRemove.toString())
            view.findViewById<ImageView>(R.id.iv_reco_image)
                .setBackgroundResource(R.drawable.bg_selected_img_stroke)
            view.findViewById<ImageView>(R.id.iv_selected_img).visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int = imgUrls.size

    fun setItemList(newList: List<String>) {
        imgUrls.clear()
        imgUrls.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ImgViewHolder(private val binding: ItemRecommendedPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imgData: String) {
            Log.e("imgData", imgData)
            Glide
                .with(binding.ivRecoImage.context)
                .load(imgData)
                .centerCrop()
                .into(binding.ivRecoImage)
        }
    }
}
