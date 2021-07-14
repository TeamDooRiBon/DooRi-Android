package kr.co.dooribon.ui.triptendency.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ViewTestTripTendencyQuestionBinding
import kr.co.dooribon.domain.entity.ParentTravelTendency
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel

/**
 * Select되는 로직은 문제 없이 제대로 포지션을 저장해놓고 있는데 , inflate될 때 문제가 생기는게 있다. 어떻게 하면 할 수 있을까..
 */
class TripTendencyQuestionAdapter(
    private val onItemClicked: (idx: Int) -> Unit,
    private val viewModel: TripTendencyViewModel
) : RecyclerView.Adapter<TripTendencyQuestionAdapter.TripTendencyQuestionViewHolder>() {

    private val childQuestionList = mutableListOf<ParentTravelTendency.ChildTravelTendencyQuestion>()

    private val childQuestionNumberList = listOf(1,2,3,4)

    private val lastSelectedPosition
        get() = viewModel.lastQuestionSelectedPosition.value!![viewModel.getQuestionPosition()!!]

    inner class TripTendencyQuestionViewHolder(val binding: ViewTestTripTendencyQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.onClick = {
                notifyItemRangeChanged(0, childQuestionList.size)
                onItemClicked(adapterPosition)
            }
        }

        fun bind(item: ParentTravelTendency.ChildTravelTendencyQuestion) {
            binding.item = item
            binding.childQuestionNumber = childQuestionNumberList[adapterPosition]
            if (lastSelectedPosition == adapterPosition)
                selectQuestion(binding)
            else
                unSelectQuestion(binding)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripTendencyQuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewTestTripTendencyQuestionBinding.inflate(layoutInflater, parent, false)
        return TripTendencyQuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripTendencyQuestionViewHolder, position: Int) {
        holder.bind(childQuestionList[position])
    }

    override fun getItemCount(): Int = childQuestionList.size

    fun submitItem(list: List<ParentTravelTendency.ChildTravelTendencyQuestion>) {
        childQuestionList.clear()
        childQuestionList.addAll(list)
        notifyDataSetChanged()
    }

    private fun selectQuestion(binding: ViewTestTripTendencyQuestionBinding) {
        binding.card.setCardBackgroundColor(Color.parseColor("#6B8FF9"))
        binding.tvTestTripTendencyProblemNumber.apply {
            setBackgroundResource(R.drawable.circle_white)
            setTextColor(Color.parseColor("#6B8FF9"))
        }
        binding.tvTestTripTendencyProblemSubject.setTextColor(Color.parseColor("#FFFFFF"))
    }

    private fun unSelectQuestion(binding: ViewTestTripTendencyQuestionBinding) {
        binding.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
        binding.tvTestTripTendencyProblemNumber.apply {
            setBackgroundResource(R.drawable.circle_sub_orange1)
            setTextColor(Color.parseColor("#FFFFFF"))
        }
        binding.tvTestTripTendencyProblemSubject.setTextColor(Color.parseColor("#000000"))
    }
}