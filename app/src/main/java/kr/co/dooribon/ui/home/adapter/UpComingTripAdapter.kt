package kr.co.dooribon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewUpComingTripBinding
import kr.co.dooribon.domain.entity.UpComingTrip

class UpComingTripAdapter : RecyclerView.Adapter<UpComingTripAdapter.UpComingTripViewHolder>() {

    private val itemList = mutableListOf<UpComingTrip>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingTripViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewUpComingTripBinding.inflate(layoutInflater, parent, false)
        return UpComingTripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpComingTripViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    class UpComingTripViewHolder(private val binding: ViewUpComingTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewItem: UpComingTrip) {
            binding.item = viewItem
        }
    }

    fun submitItem(list: List<UpComingTrip>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}