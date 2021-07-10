package kr.co.dooribon.ui.existingtrip.schedule.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemScheduleDdayBinding

class DateScheduleAdapter : RecyclerView.Adapter<DateScheduleAdapter.DateScheduleViewHolder>() {

    private var dates = mutableListOf<TravelDate>()

    //    private lateinit var itemClickListener: ItemClickListener
    private var lastSelectedPosition = -1

    // 클릭 interface
//    interface ItemClickListener {
//        fun onClick(view: View, position: Int)
//    }
//
//    fun setItemClickListener(itemClickListener: ItemClickListener) {
//        this.itemClickListener = itemClickListener
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemScheduleDdayBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_schedule_dday, parent, false)
        return DateScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateScheduleViewHolder, position: Int) {
        holder.bind(dates[position])
        if (position != lastSelectedPosition) {
//            holder.itemView.findViewById<ImageView>(R.id.iv_selected_date).visibility =
//                View.INVISIBLE
            holder.itemView.apply {
                findViewById<ImageView>(R.id.iv_selected_date).visibility = View.INVISIBLE
                findViewById<TextView>(R.id.tv_item_date).setTextColor(ContextCompat.getColor(holder.binding.tvItemDate.context, R.color.main_point_blue))
            }
        }else{
//            holder.itemView.findViewById<ImageView>(R.id.iv_selected_date).visibility =
//                View.VISIBLE
            holder.itemView.apply {
                findViewById<ImageView>(R.id.iv_selected_date).visibility = View.VISIBLE
                findViewById<TextView>(R.id.tv_item_date).setTextColor(ContextCompat.getColor(holder.binding.tvItemDate.context, R.color.gray_white_pure_9))
            }
        }
//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it, position)
//        }
    }

    override fun getItemCount(): Int = dates.size


    fun setItemList(newList: List<TravelDate>) {
        dates.clear()
        dates.addAll(newList)
        notifyDataSetChanged()
    }

    //Log.e("inLastSelectedPosition", lastSelectedPosition.toString())

    inner class DateScheduleViewHolder(val binding: ItemScheduleDdayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TravelDate) {
            binding.apply {
                tvItemDate.text = item.date.toString()
                tvDday.text = item.dday
            }

            binding.root.setOnClickListener {
                binding.ivSelectedDate.visibility = View.VISIBLE
                notifyItemChanged(lastSelectedPosition) // 마지막으로 클릭됐던 뷰에서 클릭 처리 삭제해주기
                lastSelectedPosition = adapterPosition // 마지막으로 클릭된 뷰 갱신시키기
            }
        }
    }
}