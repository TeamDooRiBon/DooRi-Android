package kr.co.dooribon.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding
import kr.co.dooribon.utils.AutoClearBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.npAmPm.apply {
            minValue = 0
            maxValue = 1
            displayedValues = arrayOf("오전", "오후")
        }
        binding.npHour.apply {
            minValue = 0
            maxValue = 12
            value = 1
        }

        binding.npMinute.apply {
            minValue = 0
            maxValue = 50
            value = 10
        }
    }
}