package kr.co.dooribon.ui.existingtrip.schedule.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.BaseTravelScheduleDTO
import kr.co.dooribon.databinding.ItemDateScheduleBinding

class TimeScheduleAdapter : RecyclerView.Adapter<TimeScheduleAdapter.PlanViewHolder>() {

    private var plans = mutableListOf<PlanData>()
    private lateinit var itemClickListener: ItemClickListener

    // 클릭 interface
    interface ItemClickListener {
        fun onTimeScheduleClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDateScheduleBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_date_schedule, parent, false)
        return PlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(plans[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onTimeScheduleClick(it, position)
        }
    }

    override fun getItemCount(): Int = plans.size

    fun setItemList(newList: List<PlanData>) {
        plans.clear()
        plans.addAll(newList)
        notifyDataSetChanged()
    }

    class PlanViewHolder(val binding: ItemDateScheduleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlanData) {
            binding.apply {
                tvTime.text = item.time
                tvTimelineMain.text = item.mainTodo
                tvTimelineSub.text = item.subTodo
            }

            when (item.viewType) {
                PlanData.FIRST_DATE_PLAN -> {
                    binding.llTopLine.visibility = View.INVISIBLE
                }
                PlanData.LAST_DATE_PLAN -> {
                    binding.llBottomLine.visibility = View.INVISIBLE
                }
            }
        }
    }
}