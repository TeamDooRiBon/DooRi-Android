package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.CreateTravelReq
import kr.co.dooribon.api.remote.CreateTravelScheduleReq
import kr.co.dooribon.api.remote.CreateTravelScheduleRes
import kr.co.dooribon.application.MainApplication.Companion.apiModule
import kr.co.dooribon.databinding.ActivityScheduleAddBinding
import kr.co.dooribon.databinding.DialogScheduleTimeBottomSheetBinding
import kr.co.dooribon.utils.DateUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.*

class ScheduleAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleAddBinding
    private var isClickable = true
    private var isAddLoc = true // 위치를 추가할 것인지 true면 추가하고 false면 추가하지 않는
    private var isStartTimeChk = false // 시작 시간 체크되었는지
    private var isEndTimeChk = false // 끝 시간 체크되었는지
    private var isBtnActivated = false // 버튼 활성화 되었는지 여부, true면 활성화, false면 비활성화

    private var passedYear = ""
    private var passedMonth = ""
    private var passedDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timePickerClickListener()
        timePickerClickListener2()
        notAddClickListener()
        scheduleAddBtnClickListener()
        addBackBtnClickListener()
        chkInputData()
        setTravelDate()

        onScheduleAddEtClick()
        onScheduleAddLocClick()
        onScheduleAddMemoClick()
    }

    /* 시간 선택하는 부분 날짜 선택 */
    private fun setTravelDate() {
        var startDateStr = ""
        passedYear = intent.getStringExtra("year").toString()
        passedMonth = intent.getStringExtra("month").toString()
        passedDate = intent.getStringExtra("date").toString()
        Log.e("passedYear", passedYear)
        val dayOfWeek = getDayOfWeek(
            startDateStr.plus(passedYear).plus("-").plus(addZero(passedMonth)).plus("-")
                .plus(addZero(passedDate))
        )
        startDateStr = startDateStr.plus(passedYear).plus(".").plus(addZero(passedMonth)).plus(".")
            .plus(addZero(passedDate)).plus(dayOfWeek)
        binding.tvScheduleTimeStartDate.text = startDateStr
        binding.tvScheduleTimeEndDate.text = startDateStr
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

    private fun addZero(n: String) =
        if (n.toInt() < 10) {
            "0".plus(n)
        } else {
            n
        }

    private fun onScheduleAddEtClick() {
        binding.etScheduleAddWhat.addTextChangedListener {
            chkBtnActivate()
        }
    }

    private fun onScheduleAddLocClick() {
        binding.etScheduleAddLocation.addTextChangedListener {
            chkBtnActivate()
        }
    }

    private fun onScheduleAddMemoClick() {
        binding.etScheduleAddMemo.addTextChangedListener {
            chkBtnActivate()
        }
    }

    private fun chkBtnActivate() {
        if (!isBtnActivated && chkInputData()) { // 활성화되어 있지 않은 상황에서 확인
            isBtnActivated = !isBtnActivated
            binding.btnScheduleAdd.apply {
                isEnabled = true
                setTextColor(getColor(R.color.gray_white_8))
                setBackgroundColor(getColor(R.color.doo_ri_bon_orange))
            }
        }
        /* 시간될 때 추가 구현 */
//        else if(isBtnActivated && !chkInputData()){
//            isBtnActivated = !isBtnActivated
//            binding.btnScheduleAdd.apply {
//                isEnabled = false
//                setTextColor(getColor(R.color.gray_gray_black_4))
//                setBackgroundColor(getColor(R.color.gray_white_8))
//            }
//        }
    }

    /* 버튼을 활성화할 수 있는지 확인하는 함수 */
    private fun chkInputData() =
        binding.etScheduleAddWhat.text.isNotEmpty() && (binding.etScheduleAddLocation.text.isNotEmpty() || !isAddLoc) && (binding.etScheduleAddMemo.text.isNotEmpty()) && isStartTimeChk && isEndTimeChk

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
                isStartTimeChk = true
                chkBtnActivate()
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

                when (sheetView.npHour.value) {
                    0 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "00"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    1 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "01"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    2 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "02"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    3 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "03"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    4 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "04"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    5 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "05"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    6 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "06"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    7 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "07"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    8 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "08"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    9 -> {
                        binding.tvTimepickerHour1.apply {
                            text = "09"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    else -> {
                        binding.tvTimepickerHour1.apply {
                            text = sheetView.npHour.value.toString()
                            setTextColor(getColor(R.color.gray_black_1))
                        }
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
                isEndTimeChk = true
                chkBtnActivate()
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

                when (sheetView.npHour.value) {
                    0 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "00"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    1 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "01"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    2 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "02"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    3 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "03"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    4 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "04"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    5 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "05"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    6 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "06"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    7 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "07"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    8 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "08"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    9 -> {
                        binding.tvTimepickerHour2.apply {
                            text = "09"
                            setTextColor(getColor(R.color.gray_black_1))
                        }
                    }
                    else -> {
                        binding.tvTimepickerHour2.apply {
                            text = sheetView.npHour.value.toString()
                            setTextColor(getColor(R.color.gray_black_1))
                        }
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
            isAddLoc = !isAddLoc
            chkBtnActivate()
            if (isClickable) {
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd_active)
                binding.etScheduleAddLocation.apply {
                    text = null
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
            sendScheduleData()
            finish()
        }
    }

    private fun sendScheduleData() {
        val startTime =
            passedYear.plus("-").plus(addZero(passedMonth)).plus("-").plus(addZero(passedDate))
                .plus(" ").plus(
                    if (binding.tvTimepickerAmpm1.text == "오후") {
                        (binding.tvTimepickerHour1.text.toString().toInt() + 12).toString()
                    } else { // 오전일 때는 그냥 12더하지 않고 추가
                        binding.tvTimepickerHour1.text.toString()
                    }
                ).plus(":").plus(binding.tvTimepickerMinute1.text.toString())
        val endTime =
            passedYear.plus("-").plus(addZero(passedMonth)).plus("-").plus(addZero(passedDate))
                .plus(" ").plus(
                    if (binding.tvTimepickerAmpm2.text == "오후") {
                        (binding.tvTimepickerHour2.text.toString().toInt() + 12).toString()
                    } else { // 오전일 때는 그냥 12더하지 않고 추가
                        binding.tvTimepickerHour2.text.toString()
                    }
                ).plus(":").plus(binding.tvTimepickerMinute2.text.toString())
        Log.e("groupId", intent.getStringExtra("groupId").toString())
        Log.e("1", binding.etScheduleAddWhat.text.toString())
        Log.e("2", startTime)
        Log.e("3", endTime)
        Log.e("4", binding.etScheduleAddLocation.text.toString())
        Log.e("5", binding.etScheduleAddMemo.text.toString())
        apiModule.scheduleApi.createTravelSchedule(
            intent.getStringExtra("groupId").toString(),
            CreateTravelScheduleReq(
                binding.etScheduleAddWhat.text.toString(),
                startTime,
                endTime,
                binding.etScheduleAddLocation.text.toString(),
                binding.etScheduleAddMemo.text.toString()
            )
        ).enqueue(object : Callback<CreateTravelScheduleRes> {
            override fun onResponse(
                call: Call<CreateTravelScheduleRes>,
                response: Response<CreateTravelScheduleRes>
            ) {
                if (response.isSuccessful) {
                    Log.e("isSuccess", response.body()!!.message)
                } else {
                    Log.e("not", response.body()?.data.toString())
                    Log.e("notSuccess", response.message())
                    Log.e("notSuccess", response.body()?.message.toString())
                    Log.e("notSuccess", response.errorBody().toString())
                    Log.e("notSuccess", response.toString())
                }
            }

            override fun onFailure(call: Call<CreateTravelScheduleRes>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    private fun addBackBtnClickListener() {
        binding.ivScheduleAddBack.setOnClickListener {
            finish()
        }
    }
}