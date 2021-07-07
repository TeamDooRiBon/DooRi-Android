package kr.co.dooribon.ui.existingtrip.tendency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.databinding.ViewTendencyDetailChildBinding
import kr.co.dooribon.databinding.ViewTendencyDetailParentBinding
import kr.co.dooribon.domain.entity.ExpandableAnswerQuestion
import kr.co.dooribon.ui.existingtrip.tendency.viewholder.ChildViewHolder
import kr.co.dooribon.ui.existingtrip.tendency.viewholder.ParentViewHolder

class TripDetailAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val testQuestionList = mutableListOf<ExpandableAnswerQuestion>()

    private var isFirstItemExpaned: Boolean = true
    private var actionLock = false

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
        val row = testQuestionList[position]
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

    override fun getItemCount(): Int = testQuestionList.size

    override fun getItemViewType(position: Int): Int = testQuestionList[position].type

    fun submitList(list: List<ExpandableAnswerQuestion>) {
        testQuestionList.clear()
        testQuestionList.addAll(list)
        notifyDataSetChanged()
    }

    private fun expandRow(position: Int) {
        val row = testQuestionList[position]
        var nextPosition = position
        when (row.type) {
            ExpandableAnswerQuestion.PARENT -> {
                for (child in row.questionParent.questionSubject) {
                    testQuestionList.add(
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
        val row = testQuestionList[position]
        var nextPosition = position + 1
        when (row.type) {
            ExpandableAnswerQuestion.PARENT -> {
                outerloop@ while (true) {
                    if (nextPosition == testQuestionList.size || testQuestionList[nextPosition].type == ExpandableAnswerQuestion.PARENT) {
                        break@outerloop
                    }

                    testQuestionList.removeAt(nextPosition)
                }
                notifyDataSetChanged()
            }
        }
    }

}