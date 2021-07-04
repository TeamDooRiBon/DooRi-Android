package kr.co.dooribon.ui.newtrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemRecommendedPhotosBinding

class RecoImgAdapter : RecyclerView.Adapter<RecoImgAdapter.ImgViewHolder>() {

    private var imgs = mutableListOf<ImageData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecommendedPhotosBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recommended_photos, parent, false)
        return ImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        holder.bind(imgs[position])
    }

    override fun getItemCount(): Int = imgs.size

    fun setItemList(newList : List<ImageData>){
        imgs.clear()
        imgs.addAll(newList)
        notifyDataSetChanged()
    }

    class ImgViewHolder(private val binding: ItemRecommendedPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(img: ImageData) {
            Glide
                .with(binding.ivRecoImage.context)
                .load(img.img)
                .into(binding.ivRecoImage)
        }
    }

}

