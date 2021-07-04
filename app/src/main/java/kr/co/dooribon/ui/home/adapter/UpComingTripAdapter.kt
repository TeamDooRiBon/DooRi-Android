package kr.co.dooribon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kr.co.dooribon.databinding.ViewUpComingTripBinding
import kr.co.dooribon.domain.entity.UpComingTrip

class UpComingTripAdapter(
    private val onItemClicked : (idx : Int , item : UpComingTrip) -> Unit
) : RecyclerView.Adapter<UpComingTripAdapter.UpComingTripViewHolder>() {

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

    inner class UpComingTripViewHolder(private val binding: ViewUpComingTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClick = {
                onItemClicked(adapterPosition,itemList[adapterPosition])
            }
        }
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

@BindingAdapter("up_coming_trip_item")
fun ViewPager2.setUpComingTripItem(items : List<UpComingTrip>){
    (adapter as? UpComingTripAdapter)?.run {
        submitItem(items)
    }
}