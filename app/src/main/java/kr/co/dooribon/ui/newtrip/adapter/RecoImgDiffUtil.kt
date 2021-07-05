package kr.co.dooribon.ui.newtrip.adapter

import androidx.recyclerview.widget.DiffUtil

class RecoImgDiffUtil(
    private val oldList: List<ImageData>,
    private val newList: List<ImageData>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].img == newList[newItemPosition].img


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        when{
            oldList[oldItemPosition].img != newList[newItemPosition].img->{
                false
            }
            else->{
                true
            }
        }

}