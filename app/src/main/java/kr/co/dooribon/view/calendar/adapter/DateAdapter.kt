package kr.co.dooribon.view.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewCalendarDateBinding

/**
 * 날짜 1개 1개 아이템을 위한 Adapter
 */
class DateAdapter(
    private val onDateClick: (idx: Int, date: String) -> Unit
) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private val dateList = mutableListOf<String>()

    inner class DateViewHolder(private val binding: ViewCalendarDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.onClick = {
                onDateClick(adapterPosition, dateList[adapterPosition])
            }
        }

        fun bind(dateItem: String) {
            binding.date = dateItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewCalendarDateBinding.inflate(layoutInflater, parent, false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(dateList[position])
    }

    override fun getItemCount(): Int = dateList.size

    fun submitItem(list: List<String>) {
        dateList.clear()
        dateList.addAll(list)
        notifyDataSetChanged()
    }
}