package kr.co.dooribon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewPreviousTripBinding
import kr.co.dooribon.domain.entity.PreviousTrip

class PreviousTripAdapter : RecyclerView.Adapter<PreviousTripAdapter.PreviousTripViewHolder>() {

    private val itemList = mutableListOf<PreviousTrip>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousTripViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPreviousTripBinding.inflate(layoutInflater,parent,false)
        return PreviousTripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviousTripViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun submitItem(list : List<PreviousTrip>){
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    class PreviousTripViewHolder(private val binding : ViewPreviousTripBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : PreviousTrip){
            binding.item = item
        }
    }

}