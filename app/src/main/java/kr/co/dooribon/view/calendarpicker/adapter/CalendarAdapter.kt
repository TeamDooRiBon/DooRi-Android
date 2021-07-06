package kr.co.dooribon.view.calendarpicker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kr.co.dooribon.R
import kr.co.dooribon.view.calendarpicker.entity.CalendarEntity
import kr.co.dooribon.view.calendarpicker.entity.CalendarType
import kr.co.dooribon.view.calendarpicker.util.CalendarDiffCallback
import kr.co.dooribon.view.calendarpicker.viewholder.BaseCalendarViewHolder
import kr.co.dooribon.view.calendarpicker.viewholder.DayViewHolder
import kr.co.dooribon.view.calendarpicker.viewholder.EmptyViewHolder
import kr.co.dooribon.view.calendarpicker.viewholder.MonthViewHolder

class CalendarAdapter : ListAdapter<CalendarEntity,BaseCalendarViewHolder>(CalendarDiffCallback()){

    var onActionListener : (CalendarEntity , Int) -> Unit = {_,_ -> }

    override fun submitList(list: MutableList<CalendarEntity>?) {
        super.submitList(list?.toMutableList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCalendarViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType){
            CalendarType.MONTH.ordinal -> {
                MonthViewHolder(layoutInflater.inflate(R.layout.calendar_month_view,parent,false))
            }
            CalendarType.DAY.ordinal -> {
                DayViewHolder(layoutInflater.inflate(R.layout.calendar_day_view,parent,false))
            }
            else -> {
                EmptyViewHolder(layoutInflater.inflate(R.layout.calendar_empty_view,parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: BaseCalendarViewHolder, position: Int) {
        holder.onBind(getItem(position),onActionListener)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).calendarType
    }
}