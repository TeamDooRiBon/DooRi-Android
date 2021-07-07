package kr.co.dooribon.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.dooribon.databinding.DialogScheduleBottomSheetBinding
import kr.co.dooribon.utils.AutoClearBinding

class ScheduleBottomSheetDialog : BottomSheetDialogFragment() {

    private var binding by AutoClearBinding<DialogScheduleBottomSheetBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogScheduleBottomSheetBinding.inflate(inflater, container, false)
        .also { DialogScheduleBottomSheetBinding ->
            binding = DialogScheduleBottomSheetBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}