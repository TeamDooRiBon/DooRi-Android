package kr.co.dooribon.ui.existingtrip.tendency.viewholder

import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTendencyDetailChildBinding
import kr.co.dooribon.domain.entity.ExpandableAnswerQuestion

class ChildViewHolder(val binding: ViewTendencyDetailChildBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ExpandableAnswerQuestion) {
        binding.item = item
    }
}