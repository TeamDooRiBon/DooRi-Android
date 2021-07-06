package kr.co.dooribon.utils

import android.view.View
import androidx.databinding.BindingAdapter

// typealias는 함수에 별명을 지어주는 기능
// 즉 저 View를 인자로 받아 Unit을 반환하는 함수의 별명을 OnClickListener라고 해주는 것이다.
typealias OnClickListener = (View) -> Unit

class OnDebounceClickListener(private val listener : OnClickListener) : View.OnClickListener {
    override fun onClick(v: View?) {
        val now = System.currentTimeMillis() // click이벤트가 들어온 순간을 저장
        if(now < lastTime + INTERVAL) return // 순간의 시간동안에 다른 입력이 들어왔을 때 지정된 시간보다 작다면 반환
        lastTime = now // 아니라면 listener를 실행시켜준다.
        v?.run(listener)
    }

    companion object {
        private const val INTERVAL : Long = 500L
        private var lastTime : Long = 0
    }
}

infix fun View.setOnDebounceClickListener(listener : OnClickListener){
    this.setOnClickListener(OnDebounceClickListener {
        it.run(listener)
    })
}

@BindingAdapter("android:onDebounceClick")
fun View.setOnDebounceClickListener(listener : View.OnClickListener) {
    if(listener == null){
        this.setOnClickListener(null)
    }else{
        this.setOnClickListener(OnDebounceClickListener{
            listener.onClick(it)
        })
    }
}