package kr.co.dooribon.ui.existingtrip.board.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ItemBoardBinding

class BoardAdapter : RecyclerView.Adapter<BoardAdapter.ListViewHolder>() {
    private var lists = mutableListOf<BoardListData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemBoardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_board, parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(lists[position])
    }

    override fun getItemCount(): Int = lists.size

    fun setItemList(newList: List<BoardListData>) {
        lists.clear()
        lists.addAll(newList)
        notifyDataSetChanged()
    }

    class ListViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BoardListData) {
            binding.data = item
        }
    }
}