package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityScheduleAddBinding
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding

class ScheduleAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleAddBinding
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullChangeBtn()
        timePickerClickListener()
        timePickerClickListener2()
        notAddClickListener()
        scheduleAddBtnClickListener()
        addBackBtnClickListener()
    }

    private fun fullChangeBtn() {
        val code1 = binding.etScheduleAddWhat.text
        val code2 = binding.etScheduleAddLocation.text
        val code3 = binding.etScheduleAddMemo.text
        binding.etScheduleAddMemo.setOnClickListener {
            if (code1.isNotEmpty() && code2.isNotEmpty() && code3.isNotEmpty()) {
                binding.btnScheduleAdd.apply {
                    setTextColor(getColor(R.color.gray_white_8))
                    setBackgroundColor(getColor(R.color.doo_ri_bon_orange))
                }
            } else if (code1.isNotEmpty() && code2.isNotEmpty() && binding.etScheduleAddLocation.isEnabled == false) {
                binding.btnScheduleAdd.apply {
                    setTextColor(getColor(R.color.gray_white_8))
                    setBackgroundColor(getColor(R.color.doo_ri_bon_orange))
                }
            } // TODO : notadd active 버튼 눌렀을 때도 버튼 활성화하기
        }
    }

    private fun timePickerClickListener() {
        binding.clTimepicker1.setOnClickListener {
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
                    binding.tvTimepickerAmpm1.apply {
                        text = "오전"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else {
                    binding.tvTimepickerAmpm1.apply {
                        text = "오후"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                }

                if (sheetView.npHour.value == 0) {
                    binding.tvTimepickerHour1.apply {
                        text = "00"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 1) {
                    binding.tvTimepickerHour1.apply {
                        text = "01"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 2) {
                    binding.tvTimepickerHour1.apply {
                        text = "02"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 3) {
                    binding.tvTimepickerHour1.apply {
                        text = "03"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 4) {
                    binding.tvTimepickerHour1.apply {
                        text = "04"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 5) {
                    binding.tvTimepickerHour1.apply {
                        text = "05"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 6) {
                    binding.tvTimepickerHour1.apply {
                        text = "06"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 7) {
                    binding.tvTimepickerHour1.apply {
                        text = "07"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 8) {
                    binding.tvTimepickerHour1.apply {
                        text = "08"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 9) {
                    binding.tvTimepickerHour1.apply {
                        text = "09"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else {
                    binding.tvTimepickerHour1.apply {
                        text = sheetView.npHour.value.toString()
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                }

                if (sheetView.npMinute.value == 0) {
                    binding.tvTimepickerMinute1.apply {
                        text = "00"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else {
                    binding.tvTimepickerMinute1.apply {
                        text = (sheetView.npMinute.value * 10).toString()
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                }
                binding.tvTimepickerColon1.setTextColor(getColor(R.color.gray_black_1))
                bsDialog.dismiss()
            }
            sheetView.btncancel.setOnClickListener {
                bsDialog.dismiss()
            }
        }
    }

    private fun timePickerClickListener2() {
        binding.clTimepicker2.setOnClickListener {
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
                    binding.tvTimepickerAmpm2.apply {
                        text = "오전"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else {
                    binding.tvTimepickerAmpm2.apply {
                        text = "오후"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                }

                if (sheetView.npHour.value == 0) {
                    binding.tvTimepickerHour2.apply {
                        text = "00"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 1) {
                    binding.tvTimepickerHour2.apply {
                        text = "01"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 2) {
                    binding.tvTimepickerHour2.apply {
                        text = "02"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 3) {
                    binding.tvTimepickerHour2.apply {
                        text = "03"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 4) {
                    binding.tvTimepickerHour2.apply {
                        text = "04"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 5) {
                    binding.tvTimepickerHour2.apply {
                        text = "05"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 6) {
                    binding.tvTimepickerHour2.apply {
                        text = "06"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 7) {
                    binding.tvTimepickerHour2.apply {
                        text = "07"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 8) {
                    binding.tvTimepickerHour2.apply {
                        text = "08"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else if (sheetView.npHour.value == 9) {
                    binding.tvTimepickerHour2.apply {
                        text = "09"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else {
                    binding.tvTimepickerHour2.apply {
                        text = sheetView.npHour.value.toString()
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                }

                if (sheetView.npMinute.value == 0) {
                    binding.tvTimepickerMinute2.apply {
                        text = "00"
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                } else {
                    binding.tvTimepickerMinute2.apply {
                        text = (sheetView.npMinute.value * 10).toString()
                        setTextColor(getColor(R.color.gray_black_1))
                    }
                }
                binding.tvTimepickerColon2.setTextColor(getColor(R.color.gray_black_1))
                bsDialog.dismiss()
            }
            sheetView.btncancel.setOnClickListener {
                bsDialog.dismiss()
            }
        }
    }

    private fun notAddClickListener() {
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

    private fun scheduleAddBtnClickListener() {
        binding.btnScheduleAdd.setOnClickListener {
            finish()
        }
    }

    private fun addBackBtnClickListener() {
        binding.ivScheduleAddBack.setOnClickListener {
            finish()
        }
    }
}