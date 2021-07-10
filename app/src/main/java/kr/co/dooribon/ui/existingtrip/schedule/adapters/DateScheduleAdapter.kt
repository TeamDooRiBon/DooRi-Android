package kr.co.dooribon.ui.existingtrip.schedule.adapters

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

    private lateinit var itemClickListener: ItemClickListener
    private var lastSelectedPosition = -1

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
        if (position != lastSelectedPosition) modifyPrevClickedView(holder)
    }

    override fun getItemCount(): Int = dates.size

    /**
     * 이전에 클릭되었던 뷰를 원래 뷰로 원상복귀 시키는 함수입니다.
     * */
    private fun modifyPrevClickedView(holder: DateScheduleViewHolder) {
        holder.itemView.findViewById<TextView>(R.id.tv_item_date).setTextColor(
            ContextCompat.getColor(
                holder.itemView.findViewById<TextView>(R.id.tv_item_date).context,
                R.color.main_point_blue
            )
        )
        holder.itemView.findViewById<ImageView>(R.id.iv_selected_date).visibility =
            View.INVISIBLE
    }

    fun setItemList(newList: List<TravelDate>) {
        dates.clear()
        dates.addAll(newList)
        notifyDataSetChanged()
    }

    /**
     * 외부에서 item click 시 notifyitemchanged를
     * 이용할 수 있게 하기 위해 만든 함수입니다.
     * */
    fun dateScheduleNotifyItemChanged(clickedPos: Int) {
        notifyItemChanged(lastSelectedPosition) // 마지막으로 클릭됐던 뷰에서 클릭 처리 삭제해주기
        lastSelectedPosition = clickedPos // 마지막으로 클릭된 뷰 갱신시키기
    }

    inner class DateScheduleViewHolder(val binding: ItemScheduleDdayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TravelDate) {
            binding.apply {
                tvItemDate.text = item.date.toString()
                tvDday.text = item.dday
            }
        }

    }
}