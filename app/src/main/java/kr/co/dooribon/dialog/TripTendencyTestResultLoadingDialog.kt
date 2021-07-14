package kr.co.dooribon.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.dooribon.databinding.DialogTripTendencyTestResultLoadingBinding
import kr.co.dooribon.utils.AutoClearBinding

class TripTendencyTestResultLoadingDialog : DialogFragment() {

    private var binding by AutoClearBinding<DialogTripTendencyTestResultLoadingBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogTripTendencyTestResultLoadingBinding.inflate(inflater, container, false)
        .also { DialogTripTendencyTestResultLoadingBinding ->
            binding = DialogTripTendencyTestResultLoadingBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        lifecycleScope.launch {
            delay(2000)
            dismiss()
        }
    }
}