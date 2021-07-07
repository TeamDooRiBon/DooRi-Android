package kr.co.dooribon.ui.newtrip.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemRecommendedPhotosBinding

class RecoImgAdapter : RecyclerView.Adapter<RecoImgAdapter.ImgViewHolder>() {

    private var imgs = mutableListOf<ImageData>()
//    private lateinit var itemClickListener : ItemClickListener // Item click Listener
    private var selectedIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRecommendedPhotosBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_recommended_photos, parent, false)
        return ImgViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
        holder.bind(imgs[position])

        holder.itemView.setOnClickListener {
            selectedIndex = position
            notifyDataSetChanged()
        }

        if(selectedIndex == position){
            holder.itemView.setBackgroundResource(R.drawable.bg_selected_img_stroke)
        }else {
            holder.itemView.setBackgroundResource(0)
        }

        //view에 클릭 리스너를 달고, itemclicklistener와 연결한다
//        holder.itemView.setOnClickListener{
//            itemClickListener.onClick(it, position)
//        }
    }

    override fun getItemCount(): Int = imgs.size

    fun setItemList(newList: List<ImageData>) {
        imgs.clear()
        imgs.addAll(newList)
        notifyDataSetChanged()
    }

    /* 클릭 리스너 interface */
//    interface ItemClickListener{
//        fun onClick(view : View, position: Int)
//    }

//    fun setItemClickListener(itemClickListener: ItemClickListener){
//        this.itemClickListener = itemClickListener
//    }

    class ImgViewHolder(private val binding: ItemRecommendedPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(img: ImageData) {
            Glide
                .with(binding.ivRecoImage.context)
                .load(img.img)
                .into(binding.ivRecoImage)
            //imgClickListener() // 여기 말고 다른 곳으로 넣어둬야 할
        }

        private fun imgClickListener(){
            binding.ivRecoImage.setOnClickListener {
                //binding.ivRecoImage.etBackgroundResource(R.drawable.bg_selected_img_stroke)
            }
        }
    }

}

