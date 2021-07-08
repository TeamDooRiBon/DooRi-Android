package kr.co.dooribon.ui.existingtrip.schedule.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.BottomsheetAddScheduleBinding

// 추후에 서버랑 연결하고 하기
//class AddScheduleBottomSheet(
//    writer: String,
//    writtenTime: String,
//    mainStr: String,
//    time: String,
//    place: String,
//    memo: String
//) : BottomSheetDialogFragment() {
class AddScheduleBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomsheetAddScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.bottomsheet_add_schedule, container, false)
        //dialog.window.attributes.windowAnimations =
        return binding.root
    }


}