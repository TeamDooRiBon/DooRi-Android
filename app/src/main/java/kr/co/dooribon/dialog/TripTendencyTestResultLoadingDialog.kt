package kr.co.dooribon.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.dooribon.databinding.DialogTripTendencyTestResultLoadingBinding
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.debugE

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

        // ResultImage가 뜨도록 함
        Log.d("fuckingShit", arguments?.getString("resultImageUrl").toString())
        lifecycleScope.launch {
            delay(2000)
            dismiss()
            // 지금 여기서는 끝나게 해놨는데
            requireActivity().finish()
        }
    }
}