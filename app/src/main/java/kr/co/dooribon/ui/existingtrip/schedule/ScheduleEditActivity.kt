package kr.co.dooribon.ui.existingtrip.schedule

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.EditTravelScheduleReq

import kr.co.dooribon.api.remote.EditTravelScheduleRes
import kr.co.dooribon.application.MainApplication.Companion.apiModule
import kr.co.dooribon.databinding.ActivityScheduleEditBinding
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleEditBinding
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBasicText()
        timePickerClickListener3()
        timePickerClickListener4()
        notEditClickListener()
        scheduleEditBtnClickListener()
        onEditBtnClick()
    }

    /* 이전 화면에서 넘어온 값들로 텍스트를 설정해둡니다 */
    private fun setBasicText() {
        val (startTime, startMin) = intent.getStringExtra("time").toString()
            .split(" ")[1].split(":")
        val (endTime, endMin) = intent.getStringExtra("time").toString().split(" ")[4].split(":")
        val year = intent.getStringExtra("year").toString()
        val month = intent.getStringExtra("month").toString()
        val date = intent.getStringExtra("date").toString()
        binding.apply {
            etScheduleAddWhat.setText(intent.getStringExtra("mainTodo").toString())
            tvTimepickerHour3.text = startTime
            tvTimepickerMinute3.text = startMin
            tvTimepickerHour4.text = endTime
            tvTimepickerMinute4.text = endMin
            tvScheduleTimeStart1.text = (year).plus(".")
                .plus(addZero(month)).plus(".")
                .plus(addZero(date)).plus(
                    getDayOfWeek(
                        year.plus("-").plus(addZero(month)).plus("-").plus(addZero(date))
                    )
                )
            tvScheduleTimeEnd1.text = (year).plus(".")
                .plus(addZero(month)).plus(".")
                .plus(addZero(date)).plus(
                    getDayOfWeek(
                        year.plus("-").plus(addZero(month)).plus("-").plus(addZero(date))
                    )
                )
            etScheduleAddLocation.setText(intent.getStringExtra("place").toString())
            etScheduleAddMemo.setText(intent.getStringExtra("memo").toString())
        }
    }

    private fun onEditBtnClick() {
        val year = intent.getStringExtra("year").toString()
        val month = intent.getStringExtra("month").toString()
        val date = intent.getStringExtra("date").toString()
        binding.btnScheduleEdit.setOnClickListener {
            val startTime =
                year.plus("-").plus(addZero(month)).plus("-").plus(addZero(date))
                    .plus(" ").plus(
                        if (binding.tvTimepickerAmpm3.text == "오후") {
                            (binding.tvTimepickerHour3.text.toString().toInt() + 12).toString()
                        } else { // 오전일 때는 그냥 12더하지 않고 추가
                            binding.tvTimepickerHour3.text.toString()
                        }
                    ).plus(":").plus(binding.tvTimepickerMinute3.text.toString())
            val endTime =
                year.plus("-").plus(addZero(month)).plus("-").plus(addZero(date))
                    .plus(" ").plus(
                        if (binding.tvTimepickerAmpm4.text == "오후") {
                            (binding.tvTimepickerHour4.text.toString().toInt() + 12).toString()
                        } else { // 오전일 때는 그냥 12더하지 않고 추가
                            binding.tvTimepickerHour4.text.toString()
                        }
                    ).plus(":").plus(binding.tvTimepickerMinute4.text.toString())

            apiModule.scheduleApi.editTravelSchedule(
                intent.getStringExtra("groupId").toString(),
                intent.getStringExtra("scheduleId").toString(),
                EditTravelScheduleReq(
                    binding.etScheduleAddWhat.text.toString(),
                    startTime,
                    endTime,
                    binding.etScheduleAddLocation.text.toString(),
                    binding.etScheduleAddMemo.text.toString()
                )
            ).enqueue(object : Callback<EditTravelScheduleRes> {
                override fun onResponse(
                    call: Call<EditTravelScheduleRes>,
                    response: Response<EditTravelScheduleRes>
                ) {
                    if (response.isSuccessful) {
                        finish()
                    } else {
                        Log.e("responseFail", response.message())
                    }
                }

                override fun onFailure(call: Call<EditTravelScheduleRes>, t: Throwable) {
                    Log.e("onEditBtnClick onFailure", t.message.toString())
                }
            })
        }
    }

    private fun addZero(n: String) =
        if (n.toInt() < 10) {
            "0".plus(n)
        } else {
            n
        }

    private fun getDayOfWeek(fStr: String): String {
        val dayOfWeek = LocalDate.parse(fStr).dayOfWeek // 라이브러리 통해 요일 가져오는 코드
        return when (dayOfWeek.toString()) {
            "MONDAY" -> {
                "(월)"
            }
            "TUESDAY" -> {
                "(화)"
            }
            "WEDNESDAY" -> {
                "(수)"
            }
            "THURSDAY" -> {
                "(목)"
            }
            "FRIDAY" -> {
                "(금)"
            }
            "SATURDAY" -> {
                "(토)"
            }
            "SUNDAY" -> {
                "(일)"
            }
            else -> {
                Log.e("ScheduleFragment", "DateParsingError")
                ""
            }
        }
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

                if (sheetView.npHour.value == 0) {
                    binding.tvTimepickerHour3.text = "00"
                } else if (sheetView.npHour.value == 1) {
                    binding.tvTimepickerHour3.text = "01"
                } else if (sheetView.npHour.value == 2) {
                    binding.tvTimepickerHour3.text = "02"
                } else if (sheetView.npHour.value == 3) {
                    binding.tvTimepickerHour3.text = "03"
                } else if (sheetView.npHour.value == 4) {
                    binding.tvTimepickerHour3.text = "04"
                } else if (sheetView.npHour.value == 5) {
                    binding.tvTimepickerHour3.text = "05"
                } else if (sheetView.npHour.value == 6) {
                    binding.tvTimepickerHour3.text = "06"
                } else if (sheetView.npHour.value == 7) {
                    binding.tvTimepickerHour3.text = "07"
                } else if (sheetView.npHour.value == 8) {
                    binding.tvTimepickerHour3.text = "08"
                } else if (sheetView.npHour.value == 9) {
                    binding.tvTimepickerHour3.text = "09"
                } else {
                    binding.tvTimepickerHour3.text = sheetView.npHour.value.toString()
                }

                if (sheetView.npMinute.value == 0) {
                    binding.tvTimepickerMinute3.text = "00"
                } else {
                    binding.tvTimepickerMinute3.text = (sheetView.npMinute.value * 10).toString()
                }
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

                if (sheetView.npHour.value == 0) {
                    binding.tvTimepickerHour4.text = "00"
                } else if (sheetView.npHour.value == 1) {
                    binding.tvTimepickerHour4.text = "01"
                } else if (sheetView.npHour.value == 2) {
                    binding.tvTimepickerHour4.text = "02"
                } else if (sheetView.npHour.value == 3) {
                    binding.tvTimepickerHour4.text = "03"
                } else if (sheetView.npHour.value == 4) {
                    binding.tvTimepickerHour4.text = "04"
                } else if (sheetView.npHour.value == 5) {
                    binding.tvTimepickerHour4.text = "05"
                } else if (sheetView.npHour.value == 6) {
                    binding.tvTimepickerHour4.text = "06"
                } else if (sheetView.npHour.value == 7) {
                    binding.tvTimepickerHour4.text = "07"
                } else if (sheetView.npHour.value == 8) {
                    binding.tvTimepickerHour4.text = "08"
                } else if (sheetView.npHour.value == 9) {
                    binding.tvTimepickerHour4.text = "09"
                } else {
                    binding.tvTimepickerHour4.text = sheetView.npHour.value.toString()
                }

                if (sheetView.npMinute.value == 0) {
                    binding.tvTimepickerMinute4.text = "00"
                } else {
                    binding.tvTimepickerMinute4.text = (sheetView.npMinute.value * 10).toString()
                }
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

//    private fun editBackBtnClickListener() {
//        binding.ivScheduleEditBack.setOnClickListener {
//            finish()
//        }
//    }

    override fun onBackPressed() {
        setQuestionDialog()
    }

    private fun setQuestionDialog() {
        val bsDialog = Dialog(this)
        val sheetView = LayoutInflater.from(this).inflate(
            R.layout.dialog_trip_tendency_test_exit,
            this.findViewById(R.id.cl_exit_dialog_root)
        )
        sheetView.findViewById<TextView>(R.id.tv_exit_sub_description).text =
            "지금까지의 수정 정보는 저장되지 않습니다."
        sheetView.findViewById<TextView>(R.id.tv_exit_sub_description2).text =
            "수정을 취소하려면 오른쪽 버튼을 눌러주세요.ㅤㅤ"
        sheetView.findViewById<Button>(R.id.btn_no_exit).setOnClickListener {
            bsDialog.dismiss()
        }
        sheetView.findViewById<Button>(R.id.btn_exit).setOnClickListener {
            bsDialog.dismiss()
            finish()
        }
        bsDialog.setContentView(sheetView)
        bsDialog.show()
    }
}