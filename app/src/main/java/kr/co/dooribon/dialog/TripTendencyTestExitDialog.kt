package kr.co.dooribon.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.dooribon.databinding.DialogTripTendencyTestExitBinding
import kr.co.dooribon.utils.AutoClearBinding

class TripTendencyTestExitDialog : DialogFragment() {

    private var binding by AutoClearBinding<DialogTripTendencyTestExitBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogTripTendencyTestExitBinding.inflate(inflater, container, false)
        .also { DialogTripTendencyTestExitBinding ->
            binding = DialogTripTendencyTestExitBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dialog = this

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun navigateExit() {
        dismiss()
        requireActivity().finish()
    }

    fun navigateKeepGoing() {
        dismiss()
    }
}