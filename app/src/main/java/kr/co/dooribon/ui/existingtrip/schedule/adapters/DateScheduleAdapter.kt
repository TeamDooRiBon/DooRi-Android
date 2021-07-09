package kr.co.dooribon.ui.existingtrip.schedule.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemScheduleDdayBinding

class DateScheduleAdapter : RecyclerView.Adapter<DateScheduleAdapter.DateScheduleViewHolder>() {

    private var dates = mutableListOf<TravelDate>()
    private lateinit var itemClickListener: ItemClickListener

    // 클릭 interface
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateScheduleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemScheduleDdayBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_schedule_dday, parent, false)
        return DateScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateScheduleViewHolder, position: Int) {
        holder.bind(dates[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)

        }
    }

    override fun getItemCount(): Int = dates.size


    fun setItemList(newList: List<TravelDate>) {
        dates.clear()
        dates.addAll(newList)
        notifyDataSetChanged()
    }

    class DateScheduleViewHolder(val binding: ItemScheduleDdayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val chkImg = itemView.findViewById<ImageView>(R.id.iv_selected_date)

        fun bind(item: TravelDate) {
            binding.apply {
                tvItemDate.text = item.date.toString()
                tvDday.text = item.dday
            }
        }
    }
}