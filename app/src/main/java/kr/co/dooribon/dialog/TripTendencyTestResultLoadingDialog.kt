package kr.co.dooribon.dialog

import android.content.Intent
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
import kr.co.dooribon.ui.traveltendencyresult.TravelTendencyResultActivity
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.constant.Constant
import kr.co.dooribon.utils.debugE
import kr.co.dooribon.utils.getIntent

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

        debugE(arguments?.getString(Constant.RESULT_IMAGE_URL))
        lifecycleScope.launch {
            delay(2 * SECOND)
            dismiss()
            startActivity(
                requireContext().getIntent<TravelTendencyResultActivity>().apply {
                putExtra(
                    Constant.TRAVEL_TENDENCY_RESULT_IMAGE_URL,
                    arguments?.getString(Constant.RESULT_IMAGE_URL)
                )
                putExtra(
                    Constant.TRAVEL_TENDENCY_RESULT_IMAGE_NAME,
                    arguments?.getString(Constant.RESULT_IMAGE_NAME)
                )
                putExtra(
                    Constant.TRAVEL_TENDENCY_USER_NAME,
                    arguments?.getString(Constant.RESULT_USER_NAME)
                )
            })
            requireActivity().finish()
        }
    }

    companion object {
        private const val SECOND = 1000L
    }
}