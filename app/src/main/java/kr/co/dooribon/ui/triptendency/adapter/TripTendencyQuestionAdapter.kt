package kr.co.dooribon.ui.triptendency.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ViewTestTripTendencyQuestionBinding
import kr.co.dooribon.domain.entity.TripTendency
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel

/**
 * 클릭 로직을 처리해야하는건 이쪽부분에서
 *
 * TODO :클릭 시 notifyItemRangeChange 때문에 깜빡이는 현상이 너무 심함
 *
 * TODO : 2번째 아이템에서는 왜그런지 모르겠는데 클릭 이벤트가 진행되지 않음 이유 모르겠음;;;
 */
class TripTendencyQuestionAdapter(
    private val onItemClicked: (idx: Int) -> Unit,
    private val viewModel: TripTendencyViewModel
) :
    RecyclerView.Adapter<TripTendencyQuestionAdapter.TripTendencyQuestionViewHolder>() {

    private val problemList = mutableListOf<TripTendency.TripTendencyQuestion>()

    private val lastSelectedPosition
        get() = viewModel.lastQuestionSelectedPosition.value!![viewModel.questionPosition.value!!]

    inner class TripTendencyQuestionViewHolder(private val binding: ViewTestTripTendencyQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.onClick = {
                notifyItemRangeChanged(0, problemList.size)
                onItemClicked(adapterPosition)
            }
        }

        fun bind(item: TripTendency.TripTendencyQuestion) {
            binding.item = item
            if (lastSelectedPosition == adapterPosition) {
                binding.card.setCardBackgroundColor(Color.parseColor("#6B8FF9"))
                binding.tvTestTripTendencyProblemNumber.apply {
                    setBackgroundResource(R.drawable.circle_white)
                    setTextColor(Color.parseColor("#6B8FF9"))
                }
                binding.tvTestTripTendencyProblemSubject.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.tvTestTripTendencyProblemNumber.apply {
                    setBackgroundResource(R.drawable.circle_sub_orange1)
                    setTextColor(Color.parseColor("#FFFFFF"))
                }
                binding.tvTestTripTendencyProblemSubject.setTextColor(Color.parseColor("#000000"))
            }
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
        holder.bind(problemList[position])
    }

    override fun getItemCount(): Int = problemList.size

    fun submitItem(list: List<TripTendency.TripTendencyQuestion>) {
        problemList.clear()
        problemList.addAll(list)
        notifyDataSetChanged()
    }
}