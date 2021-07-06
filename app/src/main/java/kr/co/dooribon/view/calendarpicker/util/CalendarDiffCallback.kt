package kr.co.dooribon.view.calendarpicker.util

import androidx.recyclerview.widget.DiffUtil
import kr.co.dooribon.view.calendarpicker.entity.CalendarEntity

class CalendarDiffCallback : DiffUtil.ItemCallback<CalendarEntity>() {
    override fun areItemsTheSame(oldItem: CalendarEntity, newItem: CalendarEntity): Boolean {
        return oldItem::class == newItem::class
    }

    override fun areContentsTheSame(oldItem: CalendarEntity, newItem: CalendarEntity): Boolean {
        return if(oldItem is CalendarEntity.Day && newItem is CalendarEntity.Day){
            oldItem.selection == newItem.selection && oldItem.isRange == newItem.isRange
        }else{
            oldItem.selectionType == newItem.selectionType
        }
    }
}