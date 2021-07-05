package kr.co.dooribon.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

/**
 * Created by SSong-develop 2021.07.05
 *
 * Loading View 혹은 , 이벤트나 화면이동 같은 비즈니스 로직이 없는 다이얼로그의 경우 이 클래스를 사용해 띄워주면 효과적일 수 있습니다.
 */
class MultiLayoutDialog(
    @LayoutRes private val layoutResId : Int,
    private val widthRatio : Float,
    private val heightRatio : Float
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}