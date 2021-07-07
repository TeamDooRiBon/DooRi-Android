package kr.co.dooribon.ui.triptendency.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTestTripTendencyBinding
import kr.co.dooribon.domain.entity.TripTendency

class TripTendencyAdapter : RecyclerView.Adapter<TripTendencyAdapter.TripTendencyViewHolder>() {

    private val questionList = mutableListOf<TripTendency>()

    class TripTendencyViewHolder(val binding :ViewTestTripTendencyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TripTendency){
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripTendencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewTestTripTendencyBinding.inflate(layoutInflater,parent,false)
        return TripTendencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripTendencyViewHolder, position: Int) {
        holder.bind(questionList[position])
        val tripTendencyQuestionAdapter = TripTendencyQuestionAdapter()
        holder.binding.rvQuestion.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = tripTendencyQuestionAdapter
        }
        tripTendencyQuestionAdapter.submitItem(questionList[position].questionList)
    }

    // 어차피 문항이 12개여서 12로 고정한 거임
    override fun getItemCount(): Int = questionList.size

    fun submitItem(list : List<TripTendency>){
        questionList.clear()
        questionList.addAll(list)
        notifyDataSetChanged()
    }

    companion object {
        private const val MAX_QUESTION_COUNT = 12
    }
}