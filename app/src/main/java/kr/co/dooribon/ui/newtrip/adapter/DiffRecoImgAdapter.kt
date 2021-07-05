package kr.co.dooribon.ui.newtrip.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemRecommendedPhotosBinding

class DiffRecoImgAdapter : RecyclerView.Adapter<DiffRecoImgAdapter.RecoImgViewHolder>() {

    private var oldImgList = emptyList<ImageData>()
    private lateinit var itemClickListener : ItemClickListener

    class RecoImgViewHolder(val binding: ItemRecommendedPhotosBinding) :
        RecyclerView.ViewHolder(binding.root){
            fun bind(img : ImageData){
                Glide
                    .with(binding.ivRecoImage.context)
                    .load(img.img)
                    .into(binding.ivRecoImage)
                if(img.isChecked){
                    binding.ivRecoImage.setBackgroundResource(R.drawable.bg_selected_img_stroke)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecoImgViewHolder =
        RecoImgViewHolder(
            ItemRecommendedPhotosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecoImgViewHolder, position: Int) {
        //holder.binding.ivRecoImage.setImageResource(oldImgList[position].img)
        holder.bind(oldImgList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }

    }

    override fun getItemCount(): Int = oldImgList.size

    fun setImgData(newImgList : List<ImageData>){
        val diffUtil = RecoImgDiffUtil(oldImgList, newImgList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldImgList = newImgList
        diffResults.dispatchUpdatesTo(this)
    }

    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener : ItemClickListener){
        this.itemClickListener = itemClickListener
    }
}