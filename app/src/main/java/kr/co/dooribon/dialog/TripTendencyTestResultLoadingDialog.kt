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

        debugE(arguments?.getString("resultImageUrl"))
        val travelTendencyResultIntent =
            Intent(requireContext(), TravelTendencyResultActivity::class.java)
        travelTendencyResultIntent.putExtra(
            "travelTendencyResultImageUrl",
            arguments?.getString("resultImageUrl")
        )
        travelTendencyResultIntent.putExtra(
            "travelTendencyResultImageName",
            arguments?.getString("resultImageName")
        )
        travelTendencyResultIntent.putExtra(
            "travelTendencyUserName",
            arguments?.getString("resultUserName")
        )
        lifecycleScope.launch {
            delay(2000)
            dismiss()
            startActivity(travelTendencyResultIntent)
            requireActivity().finish()
        }
    }
}