package kr.co.dooribon.ui.existingtrip.tendency.viewholder

import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTendencyDetailParentBinding
import kr.co.dooribon.domain.entity.ExpandableAnswerQuestion

class ParentViewHolder(val binding: ViewTendencyDetailParentBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ExpandableAnswerQuestion) {
        binding.item = item
    }
}