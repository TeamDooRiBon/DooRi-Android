package kr.co.dooribon.view.calendarpicker.viewholder

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.view.calendarpicker.entity.CalendarEntity
import kr.co.dooribon.view.calendarpicker.entity.DateState
import kr.co.dooribon.view.calendarpicker.entity.SelectionType

abstract class BaseCalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: CalendarEntity, actionListener: (CalendarEntity, Int) -> Unit)
}

class MonthViewHolder(private val view: View) : BaseCalendarViewHolder(view) {

    private val name by lazy { view.findViewById<TextView>(R.id.tv_month_name) }
    override fun onBind(item: CalendarEntity, actionListener: (CalendarEntity, Int) -> Unit) {
        if (item is CalendarEntity.Month) {
            name.text = item.label
        }
    }
}

class DayViewHolder(private val view: View) : BaseCalendarViewHolder(view) {

    private val name by lazy { view.findViewById<TextView>(R.id.tv_day_name) }
    private val halfLeftBg by lazy { view.findViewById<View>(R.id.vHalfLeftBg) }
    private val halfRightBg by lazy { view.findViewById<View>(R.id.vHalfRightBg) }

    override fun onBind(item: CalendarEntity, actionListener: (CalendarEntity, Int) -> Unit) {
        if (item is CalendarEntity.Day) {
            name.text = item.label
            when (item.selection) {
                SelectionType.START -> {
                    name.select()
                    halfLeftBg.dehighlight()
                    if (item.isRange) halfRightBg.highlight()
                    else halfRightBg.dehighlight()
                }
                SelectionType.END -> {
                    name.select()
                    halfLeftBg.highlight()
                    halfRightBg.dehighlight()
                }
                SelectionType.BETWEEN -> {
                    name.deSelect()
                    halfRightBg.highlight()
                    halfLeftBg.highlight()
                }
                SelectionType.NONE -> {
                    halfLeftBg.dehighlight()
                    halfRightBg.dehighlight()
                    name.deSelect()
                }
            }

            name.setTextColor(getFontColor(item))
            if (item.state != DateState.DISABLED) {
                itemView.setOnClickListener {
                    actionListener.invoke(
                        item,
                        adapterPosition
                    )
                }
            } else {
                itemView.setOnClickListener(null)
            }
        }
    }

    private fun getFontColor(item: CalendarEntity.Day): Int {
        return if (item.selection == SelectionType.START || item.selection == SelectionType.END) {
            // range Click의 시작과 끝점의 textColor는 white로
            ContextCompat.getColor(itemView.context, R.color.doo_ri_bon_calendar_day_selected_font)
        } else if (item.selection == SelectionType.BETWEEN && item.state != DateState.WEEKEND) {
            ContextCompat.getColor(itemView.context,R.color.main_point_blue)
        } else {
            val color = when (item.state) {
                DateState.DISABLED -> R.color.gray_gray_7_line
                DateState.WEEKEND -> R.color.doo_ri_bon_orange
                else -> R.color.black
            }
            ContextCompat.getColor(itemView.context, color)
        }
    }

    private fun View.select() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bg_select_day)
        background = drawable
    }

    private fun View.deSelect() {
        background = null
    }

    private fun View.dehighlight() {
        val color = ContextCompat.getColor(context, R.color.doo_ri_bon_calendar_day_unselected_bg)
        setBackgroundColor(color)
    }

    private fun View.highlight() {
        val color = ContextCompat.getColor(context, R.color.doo_ri_bon_calendar_highlight_background_color)
        setBackgroundColor(color)
    }
}

class EmptyViewHolder(view: View) : BaseCalendarViewHolder(view) {
    override fun onBind(item: CalendarEntity, actionListener: (CalendarEntity, Int) -> Unit) {
    }
}
