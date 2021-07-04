package kr.co.dooribon.view.calendar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
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
class CalendarMonthAdapter : RecyclerView.Adapter<CalendarMonthAdapter.CalendarViewHolder>() {

    private val dateOfMonth = mutableListOf<CalendarMonthEntity>()

    class CalendarViewHolder(val binding: ViewCalendarMonthBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(calendarMonthEntity: CalendarMonthEntity) {
            binding.calendarMonthEntity = calendarMonthEntity
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewCalendarMonthBinding.inflate(layoutInflater, parent, false)
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(dateOfMonth[position])
        val dateAdapter = DateAdapter(onDateClick = { idx, Date ->
            onDateClick(idx, Date)
        })
        holder.binding.rvMonthContainer.apply {
            layoutManager = GridLayoutManager(this.context, 7)
            adapter = dateAdapter
        }
        dateAdapter.submitItem(dateOfMonth[position].list)
    }

    override fun getItemCount(): Int = dateOfMonth.size

    fun submitList(dateList: List<CalendarMonthEntity>) {
        dateOfMonth.clear()
        dateOfMonth.addAll(dateList)
        notifyDataSetChanged()
    }

    private fun onDateClick(idx: Int, date: String) {
        // TODO : 여기서 어떻게 하면 그 달의 날짜임을 알 수 있을지??? 그걸 생각해봐야할거 같습니다.
        Log.d("DateClicked!!!", "$dateOfMonth")
    }
}
