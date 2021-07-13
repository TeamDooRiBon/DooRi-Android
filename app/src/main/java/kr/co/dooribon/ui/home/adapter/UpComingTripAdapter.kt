package kr.co.dooribon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewUpComingTripBinding
import kr.co.dooribon.domain.entity.UpComingTravel

class UpComingTripAdapter(
    private val onItemClicked: (idx: Int, item: UpComingTravel) -> Unit
) : RecyclerView.Adapter<UpComingTripAdapter.UpComingTripViewHolder>() {

    private val itemList = mutableListOf<UpComingTravel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingTripViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewUpComingTripBinding.inflate(layoutInflater, parent, false)
        return UpComingTripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpComingTripViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class UpComingTripViewHolder(private val binding: ViewUpComingTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClick = {
                onItemClicked(adapterPosition, itemList[adapterPosition])
            }
        }

        fun bind(viewItem: UpComingTravel) {
            binding.item = viewItem
        }
    }

    fun submitItem(list: List<UpComingTravel>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}