package kr.co.dooribon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewPreviousTripBinding
import kr.co.dooribon.domain.entity.PreviousTravel

class PreviousTripAdapter(
    private val onItemClicked: (idx: Int, item: PreviousTravel) -> Unit
) : RecyclerView.Adapter<PreviousTripAdapter.PreviousTripViewHolder>() {

    private val itemList = mutableListOf<PreviousTravel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousTripViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPreviousTripBinding.inflate(layoutInflater, parent, false)
        return PreviousTripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviousTripViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun submitItem(list: List<PreviousTravel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    inner class PreviousTripViewHolder(private val binding: ViewPreviousTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClick = {
                onItemClicked(adapterPosition, itemList[adapterPosition])
            }
        }

        fun bind(item: PreviousTravel) {
            binding.item = item
        }
    }

}