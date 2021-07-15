package kr.co.dooribon.ui.triptendency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTestTripTendencyBinding
import kr.co.dooribon.domain.entity.ParentTravelTendency
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel

class TripTendencyAdapter(
    private val viewModel: TripTendencyViewModel
) : RecyclerView.Adapter<TripTendencyAdapter.TripTendencyViewHolder>() {

    private val questionList = mutableListOf<ParentTravelTendency>()

    private val parentQuestionNumber = listOf(1,2,3,4,5,6,7,8,9,10)

    private val adapterList = MutableList(10) { _ ->
        TripTendencyQuestionAdapter(onItemClicked = { idx ->
            onItemClicked(idx)
        }, viewModel)
    }

    private val isSubmitItemCheckList = BooleanArray(10) { _ -> false }

    inner class TripTendencyViewHolder(val binding: ViewTestTripTendencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ParentTravelTendency) {
            binding.questionListNumber = parentQuestionNumber[adapterPosition]
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripTendencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewTestTripTendencyBinding.inflate(layoutInflater, parent, false)
        return TripTendencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripTendencyViewHolder, position: Int) {
        holder.bind(questionList[position])

        holder.binding.rvQuestion.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = adapterList[position]
        }
        if (!isSubmitItemCheckList[position]) {
            adapterList[position].submitItem(questionList[position].childQuestions)
            isSubmitItemCheckList[position] = true
        } else {
            adapterList[position].notifyItemRangeChanged(0, adapterList[position].itemCount)
        }
    }

    override fun getItemCount(): Int = questionList.size

    fun submitItem(list: List<ParentTravelTendency>) {
        questionList.clear()
        questionList.addAll(list)
        notifyDataSetChanged()
    }

    private fun onItemClicked(idx: Int) {
        viewModel.selectQuestionForServer(idx)
        viewModel.selectQuestion(idx)
    }

}