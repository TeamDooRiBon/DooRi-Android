package kr.co.dooribon.view.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewCalendarMonthBinding
import kr.co.dooribon.view.calendar.entity.CalendarMonthEntity

/**
 * 7월      2021
 * 1 2 3 4 5 6 -> 이런 데이터들을 보여주기 위한 Adapter
 *
 * 즉 년도와 달 그리고 날짜를 보여줄 수 있는 Adapter
 */
class CalendarMonthAdapter : RecyclerView.Adapter<CalendarMonthAdapter.CalendarViewHolder>(){

    private val dateOfMonth = mutableListOf<CalendarMonthEntity>()

    class CalendarViewHolder(val binding : ViewCalendarMonthBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(calendarMonthEntity : CalendarMonthEntity){
            binding.calendarMonthEntity = calendarMonthEntity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewCalendarMonthBinding.inflate(layoutInflater,parent,false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(dateOfMonth[position])
        val dateAdapter = DateAdapter()
        holder.binding.rvMonthContainer.apply {
            layoutManager = GridLayoutManager(this.context,7)
            adapter = dateAdapter
        }
        dateAdapter.submitItem(dateOfMonth[position].list)
    }

    override fun getItemCount(): Int = dateOfMonth.size

    fun submitList(dateList : List<CalendarMonthEntity>){
        dateOfMonth.clear()
        dateOfMonth.addAll(dateList)
        notifyDataSetChanged()
    }
}
