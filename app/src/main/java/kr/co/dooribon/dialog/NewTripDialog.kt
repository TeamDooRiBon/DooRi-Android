package kr.co.dooribon.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.dooribon.databinding.DialogNewTripBinding
import kr.co.dooribon.ui.newtrip.AddTravelActivity
import kr.co.dooribon.ui.newtrip.join.ParticipateActivity
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.fullScreenDialogSize
import kr.co.dooribon.utils.getIntent
import kr.co.dooribon.utils.shortToast

class NewTripDialog : DialogFragment() {

    private var binding by AutoClearBinding<DialogNewTripBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DialogNewTripBinding.inflate(inflater, container, false).also { DialogNewTripBinding ->
            binding = DialogNewTripBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.dialogFrament = this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        fullScreenDialogSize()
    }

    fun navigateNewTrip() {
        startActivity(requireContext().getIntent<AddTravelActivity>())
        dismiss()
    }

    fun navigateJoinTrip() {
        // 참여코드를 적을 수 있는 화면으로 넘어간다.
        startActivity(requireContext().getIntent<ParticipateActivity>())
        dismiss()
    }

}