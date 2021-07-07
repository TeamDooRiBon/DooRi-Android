package kr.co.dooribon.ui.existingtrip.schedule.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.BottomsheetAddScheduleBinding

class AddScheduleBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding : BottomsheetAddScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottomsheet_add_schedule, container, false)
        return binding.root
    }


}