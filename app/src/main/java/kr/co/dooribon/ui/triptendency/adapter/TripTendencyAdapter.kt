package kr.co.dooribon.ui.triptendency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTestTripTendencyBinding
import kr.co.dooribon.domain.entity.TripTendency
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel

class TripTendencyAdapter(
    private val viewModel: TripTendencyViewModel
) : RecyclerView.Adapter<TripTendencyAdapter.TripTendencyViewHolder>() {

    private val questionList = mutableListOf<TripTendency>()

    class TripTendencyViewHolder(val binding: ViewTestTripTendencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TripTendency) {
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
        val tripTendencyQuestionAdapter = TripTendencyQuestionAdapter(onItemClicked = { idx ->
            onItemClicked(idx)
        }, viewModel)
        holder.binding.rvQuestion.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = tripTendencyQuestionAdapter
        }
        tripTendencyQuestionAdapter.submitItem(questionList[position].questionList)
    }

    override fun getItemCount(): Int = questionList.size

    fun submitItem(list: List<TripTendency>) {
        questionList.clear()
        questionList.addAll(list)
        notifyDataSetChanged()
    }

    private fun onItemClicked(idx: Int) {
        viewModel.selectQuestion(idx)
    }
}