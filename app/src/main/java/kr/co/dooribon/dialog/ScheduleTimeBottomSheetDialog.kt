package kr.co.dooribon.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.setNumberPickerTextColor

/**
 * TODO : NumberPicker 위에 그 검정 줄을 못없앰;;;
 * TODO : 바텀 시트 둥그렇게 해야함
 */
class ScheduleTimeBottomSheetDialog : BottomSheetDialogFragment() {

    private var binding by AutoClearBinding<DialogScheduleTimeBottomSheetBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogScheduleTimeBottomSheetBinding.inflate(inflater, container, false)
        .also { DialogScheduleTimeBottomSheetBinding ->
            binding = DialogScheduleTimeBottomSheetBinding
        }.root

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.npAmPm.apply {
            // 0이면 오전 1이면 오후
            minValue = 0
            maxValue = 1
            displayedValues = arrayOf("오전", "오후")
            wrapSelectorWheel = false
        }
        binding.npHour.apply { // 얘는 문제가 없어 해봐야 시간이다 보니까
            minValue = 0
            maxValue = 12
            displayedValues = arrayOf(
                "00",
                "01",
                "02",
                "03",
                "04",
                "05",
                "06",
                "07",
                "08",
                "09",
                "10",
                "11",
                "12"
            )
            wrapSelectorWheel = false
        }
        binding.npMinute.apply { // 이 녀석한테서 값을 가져올 때는 무조건 *10 으로 반환을 해주면 됩니다. ex. 10을 선택했다면 1 * 10 = 10 으로 되게끔
            // 이게 10분 단위로 끊다보니까 넘버 피킹에서는 그냥 0~5를 선택하는 것이고 우리가 값을 가져올떈 10을 곱해주면 됩니다.
            minValue = 0
            maxValue = 5
            displayedValues = arrayOf("00", "10", "20", "30", "40", "50")
            wrapSelectorWheel = false
        }
    }
}