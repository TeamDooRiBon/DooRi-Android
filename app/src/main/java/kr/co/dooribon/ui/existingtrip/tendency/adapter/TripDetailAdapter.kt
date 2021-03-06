package kr.co.dooribon.ui.existingtrip.tendency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTendencyDetailChildBinding
import kr.co.dooribon.databinding.ViewTendencyDetailParentBinding
import kr.co.dooribon.domain.entity.ExpandableAnswerQuestion

// TODO : ListAdapter로 바꿔도 똑같을거 같긴 한데 일단 오케이
// TODO : 이거 숫자로 보여줘야 하는데 그게 안됨
class TripDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val questionList = mutableListOf<ExpandableAnswerQuestion>()

    inner class ChildViewHolder(val binding: ViewTendencyDetailChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExpandableAnswerQuestion) {
            binding.item = item
        }
    }

    inner class ParentViewHolder(val binding: ViewTendencyDetailParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ExpandableAnswerQuestion) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ExpandableAnswerQuestion.PARENT -> {
                val parentBinding =
                    ViewTendencyDetailParentBinding.inflate(layoutInflater, parent, false)
                ParentViewHolder(parentBinding)
            }
            ExpandableAnswerQuestion.CHILD -> {
                val childBinding =
                    ViewTendencyDetailChildBinding.inflate(layoutInflater, parent, false)
                ChildViewHolder(childBinding)
            }
            else -> {
                throw IllegalArgumentException("how dare you....")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val row = questionList[position]
        when (row.type) {
            ExpandableAnswerQuestion.PARENT -> {
                (holder as ParentViewHolder).bind(row)
                holder.binding.ivTendencyDetailShow.setOnClickListener {
                    if (row.isExpanded) {
                        row.isExpanded = false
                        collapseRow(position)
                    } else {
                        row.isExpanded = true
                        holder.binding.ivTendencyDetailClose.visibility = View.VISIBLE
                        holder.binding.ivTendencyDetailShow.visibility = View.GONE
                        expandRow(position)
                    }
                }
                holder.binding.ivTendencyDetailClose.setOnClickListener {
                    if (row.isExpanded) {
                        row.isExpanded = false
                        collapseRow(position)
                        holder.binding.apply {
                            ivTendencyDetailClose.visibility = View.GONE
                            ivTendencyDetailShow.visibility = View.VISIBLE
                        }
                    }
                }
            }
            ExpandableAnswerQuestion.CHILD -> {
                (holder as ChildViewHolder).bind(row)
            }
        }
    }

    override fun getItemCount(): Int = questionList.size

    override fun getItemViewType(position: Int): Int = questionList[position].type

    fun submitList(list: List<ExpandableAnswerQuestion>) {
        questionList.clear()
        questionList.addAll(list)
        notifyDataSetChanged()
    }

    private fun expandRow(position: Int) {
        val row = questionList[position]
        var nextPosition = position
        when (row.type) {
            ExpandableAnswerQuestion.PARENT -> {
                for (child in row.questionParent.questionSubject) {
                    questionList.add(
                        ++nextPosition,
                        ExpandableAnswerQuestion(ExpandableAnswerQuestion.CHILD, child)
                    )
                }
                notifyDataSetChanged()
            }
            ExpandableAnswerQuestion.CHILD -> {
                notifyDataSetChanged()
            }
        }
    }

    private fun collapseRow(position: Int) {
        val row = questionList[position]
        var nextPosition = position + 1
        when (row.type) {
            ExpandableAnswerQuestion.PARENT -> {
                outerloop@ while (true) {
                    if (nextPosition == questionList.size || questionList[nextPosition].type == ExpandableAnswerQuestion.PARENT) {
                        break@outerloop
                    }

                    questionList.removeAt(nextPosition)
                }
                notifyDataSetChanged()
            }
        }
    }

}