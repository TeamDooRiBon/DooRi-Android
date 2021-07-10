package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityScheduleEditBinding
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding

class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleEditBinding
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timePickerClickListener3()
        timePickerClickListener4()
        notEditClickListener()
        scheduleEditBtnClickListener()
        editBackBtnClickListener()
    }

    private fun timePickerClickListener3() {
        binding.clTimepicker3.setOnClickListener {
            val bsDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val sheetView = DialogScheduleTimeBottomSheetBinding.inflate(layoutInflater)

            sheetView.npAmPm.apply {
                // 0이면 오전 1이면 오후
                minValue = 0
                maxValue = 1
                displayedValues = arrayOf("오전", "오후")
                wrapSelectorWheel = false
            }
            sheetView.npHour.apply { // 얘는 문제가 없어 해봐야 시간이다 보니까
                minValue = 0
                maxValue = 12
                displayedValues = arrayOf(
                    "00",
                    "01",
                    "02",
                    "03",
                    "04",
                    "05",
                    "06",
                    "07",
                    "08",
                    "09",
                    "10",
                    "11",
                    "12"
                )
                wrapSelectorWheel = false
            }
            sheetView.npMinute.apply { // 이 녀석한테서 값을 가져올 때는 무조건 *10 으로 반환을 해주면 됩니다. ex. 10을 선택했다면 1 * 10 = 10 으로 되게끔
                // 이게 10분 단위로 끊다보니까 넘버 피킹에서는 그냥 0~5를 선택하는 것이고 우리가 값을 가져올떈 10을 곱해주면 됩니다.
                minValue = 0
                maxValue = 5
                displayedValues = arrayOf("00", "10", "20", "30", "40", "50")
                wrapSelectorWheel = false
            }

            bsDialog.setContentView(sheetView.root)
            bsDialog.show()

            sheetView.btnok.setOnClickListener {
                if (sheetView.npAmPm.value == 0) {
                    binding.tvTimepickerAmpm3.text = "오전"
                } else {
                    binding.tvTimepickerAmpm3.text = "오후"
                }
                binding.tvTimepickerHour3.text = sheetView.npHour.value.toString()
                binding.tvTimepickerMinute3.text = (sheetView.npMinute.value * 10).toString()
                bsDialog.dismiss()
            }
            sheetView.btncancel.setOnClickListener {
                bsDialog.dismiss()
            }
        }
    }

    private fun timePickerClickListener4() {
        binding.clTimepicker4.setOnClickListener {
            val bsDialog = BottomSheetDialog(this, R.style.BottomSheetTheme)
            val sheetView = DialogScheduleTimeBottomSheetBinding.inflate(layoutInflater)

            sheetView.npAmPm.apply {
                // 0이면 오전 1이면 오후
                minValue = 0
                maxValue = 1
                displayedValues = arrayOf("오전", "오후")
                wrapSelectorWheel = false
            }
            sheetView.npHour.apply { // 얘는 문제가 없어 해봐야 시간이다 보니까
                minValue = 0
                maxValue = 12
                displayedValues = arrayOf(
                    "00",
                    "01",
                    "02",
                    "03",
                    "04",
                    "05",
                    "06",
                    "07",
                    "08",
                    "09",
                    "10",
                    "11",
                    "12"
                )
                wrapSelectorWheel = false
            }
            sheetView.npMinute.apply { // 이 녀석한테서 값을 가져올 때는 무조건 *10 으로 반환을 해주면 됩니다. ex. 10을 선택했다면 1 * 10 = 10 으로 되게끔
                // 이게 10분 단위로 끊다보니까 넘버 피킹에서는 그냥 0~5를 선택하는 것이고 우리가 값을 가져올떈 10을 곱해주면 됩니다.
                minValue = 0
                maxValue = 5
                displayedValues = arrayOf("00", "10", "20", "30", "40", "50")
                wrapSelectorWheel = false
            }

            bsDialog.setContentView(sheetView.root)
            bsDialog.show()

            sheetView.btnok.setOnClickListener {
                if (sheetView.npAmPm.value == 0) {
                    binding.tvTimepickerAmpm4.text = "오전"
                } else {
                    binding.tvTimepickerAmpm4.text = "오후"
                }
                binding.tvTimepickerHour4.text = sheetView.npHour.value.toString()
                binding.tvTimepickerMinute4.text = (sheetView.npMinute.value * 10).toString()
                bsDialog.dismiss()
            }
            sheetView.btncancel.setOnClickListener {
                bsDialog.dismiss()
            }
        }
    }

    private fun notEditClickListener() {
        binding.ivScheduleNotadd.setOnClickListener {
            if (isClickable) {
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd_active)
                binding.etScheduleAddLocation.apply {
                    setText(null)
                    isClickable = false
                    isEnabled = false
                    setBackgroundResource(R.drawable.bg_edit_gray)
                    hint = ""
                }
                isClickable = false
            } else if (!isClickable) {
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd)
                binding.etScheduleAddLocation.apply {
                    isEnabled = true
                    setBackgroundResource(R.drawable.bg_edit_text_gray)
                }
                isClickable = true
            }

        }
    }

    private fun scheduleEditBtnClickListener() {
        binding.btnScheduleEdit.setOnClickListener {
            finish()
        }
    }

    private fun editBackBtnClickListener() {
        binding.ivScheduleEditBack.setOnClickListener {
            finish()
        }
    }
}