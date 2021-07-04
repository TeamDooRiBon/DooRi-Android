package kr.co.dooribon.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.DialogNewTripBinding
import kr.co.dooribon.utils.AutoClearBinding

class NewTripDialog : DialogFragment() {

    private var binding by AutoClearBinding<DialogNewTripBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogNewTripBinding.inflate(inflater,container,false).also { DialogNewTripBinding ->
        binding = DialogNewTripBinding
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
    }
}