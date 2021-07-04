package kr.co.dooribon.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RVItemDeco(
    private val mTop: Int,
    private val mRight: Int,
    private val mBottom: Int,
    private val mLeft: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            val curPos = parent.getChildAdapterPosition(view)
            right = mRight
            if((curPos+1)<=12){ // 맨 마지막 줄이 아니면 bottom padding 추가
                bottom = mBottom
            }
        }
    }
}