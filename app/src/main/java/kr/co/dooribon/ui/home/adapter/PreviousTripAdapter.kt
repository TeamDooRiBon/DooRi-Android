package kr.co.dooribon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewPreviousTripBinding
import kr.co.dooribon.domain.entity.PreviousTrip

class PreviousTripAdapter(
    private val onItemClicked : (idx : Int , item : PreviousTrip) -> Unit
) : RecyclerView.Adapter<PreviousTripAdapter.PreviousTripViewHolder>() {

    private val itemList = mutableListOf<PreviousTrip>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousTripViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewPreviousTripBinding.inflate(layoutInflater, parent, false)
        return PreviousTripViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviousTripViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun submitItem(list: List<PreviousTrip>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    // 이걸 inner class로 하게 되면 의존성이 생성되는데 그게 코드에 과연 큰 문제가 발생할까?
    inner class PreviousTripViewHolder(private val binding: ViewPreviousTripBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClick = {
                onItemClicked(adapterPosition,itemList[adapterPosition])
            }
        }
        fun bind(item: PreviousTrip) {
            binding.item = item
        }
    }

}

@BindingAdapter("previous_trip_item")
fun RecyclerView.setPreviousTripItem(items : List<PreviousTrip>){
    (adapter as? PreviousTripAdapter)?.run {
        submitItem(items)
    }
}