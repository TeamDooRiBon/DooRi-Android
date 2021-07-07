package kr.co.dooribon.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding
import kr.co.dooribon.utils.AutoClearBinding

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

    }
}