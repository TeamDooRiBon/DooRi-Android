package kr.co.dooribon.ui.triptendency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTestTripTendencyQuestionBinding
import kr.co.dooribon.domain.entity.TripTendency

/**
 * 클릭 로직을 처리해야하는건 이쪽부분에서
 */
class TripTendencyQuestionAdapter :
    RecyclerView.Adapter<TripTendencyQuestionAdapter.TripTendencyQuestionViewHolder>() {

    private val problemList = mutableListOf<TripTendency.TripTendencyQuestion>()

    class TripTendencyQuestionViewHolder(private val binding: ViewTestTripTendencyQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TripTendency.TripTendencyQuestion) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TripTendencyQuestionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewTestTripTendencyQuestionBinding.inflate(layoutInflater,parent,false)
        return TripTendencyQuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripTendencyQuestionViewHolder, position: Int) {
        holder.bind(problemList[position])
    }

    override fun getItemCount(): Int = problemList.size

    fun submitItem(list : List<TripTendency.TripTendencyQuestion>){
        problemList.clear()
        problemList.addAll(list)
        notifyDataSetChanged()
    }
}