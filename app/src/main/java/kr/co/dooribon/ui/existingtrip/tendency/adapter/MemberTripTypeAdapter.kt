package kr.co.dooribon.ui.existingtrip.tendency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.api.remote.GroupTravelTendencyDTO
import kr.co.dooribon.databinding.ViewOtherMemberTripTypeBinding
import kr.co.dooribon.utils.addChip

class MemberTripTypeAdapter(
    private val onItemDetailClicked: (imageUrl: String) -> Unit
) :
    RecyclerView.Adapter<MemberTripTypeAdapter.MemberTripTypeViewHolder>() {

    private val memberTripTypeList = mutableListOf<GroupTravelTendencyDTO>()

    inner class MemberTripTypeViewHolder(private val binding: ViewOtherMemberTripTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.onClick = {
                onItemDetailClicked(memberTripTypeList[adapterPosition].tendencyResultImageUrl)
            }
        }

        fun bind(item: GroupTravelTendencyDTO) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberTripTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewOtherMemberTripTypeBinding.inflate(layoutInflater, parent, false)
        return MemberTripTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemberTripTypeViewHolder, position: Int) {
        holder.bind(memberTripTypeList[position])
    }

    override fun getItemCount(): Int = memberTripTypeList.size

    fun submitList(list: List<GroupTravelTendencyDTO>) {
        memberTripTypeList.clear()
        memberTripTypeList.addAll(list)
        notifyDataSetChanged()
    }
}

@BindingAdapter("chip_adapter_item")
fun LinearLayout.setChips(items: List<String>) {
    items.forEach {
        addChip(it)
    }
}
