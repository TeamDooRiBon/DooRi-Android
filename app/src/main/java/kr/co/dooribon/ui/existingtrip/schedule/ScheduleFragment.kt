package kr.co.dooribon.ui.existingtrip.schedule

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.*
import kr.co.dooribon.application.MainApplication.Companion.apiModule
import kr.co.dooribon.databinding.FragmentScheduleBinding
import kr.co.dooribon.ui.existingtrip.schedule.adapters.DateScheduleAdapter
import kr.co.dooribon.ui.existingtrip.schedule.adapters.PlanData
import kr.co.dooribon.ui.existingtrip.schedule.adapters.TimeScheduleAdapter
import kr.co.dooribon.ui.existingtrip.schedule.adapters.TravelDate
import kr.co.dooribon.ui.existingtrip.viewmodel.ExistingTripViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var datesList: List<TravelDate>
    private var onceDone = false // 날짜 리사이클러뷰 아이템 클릭하면 true로 변경.

    private val viewModel by activityViewModels<ExistingTripViewModel>()

    // 날짜 리사이클러 뷰에서 두 번째 클릭부터는 리사이클러 뷰 첫번째 날짜를 다시
    // 바꿔줄 필요가 없어 true로 변경해서 다시 접근하지 않도록 해준다.
    private lateinit var travelData: TravelScheduleDTO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)

        onAddScheduleBtnClick()
        getDateScheduleList()
        return binding.root
    }

    /*
    * 처음 프래그먼트에 들어왔을 때 보여질 뷰입니다.
    * 리사이클러뷰에 첫 날의 일정을 보여줍니다.
    * */
    private fun setFirstBottomRv(curDate: TravelDate) {
        getPlanData(
            viewModel.getGroupId(),
            (curDate.year).toString().plus("-")
                .plus(if (curDate.month < 10) "0".plus(curDate.month) else curDate.month)
                .plus("-")
                .plus(if (curDate.date < 10) "0".plus(curDate.date) else curDate.date)
        ) // 클릭한 날의 데이터를 가져
    }

    /* 처음 화면 로드 시 날짜 설정 추가 */
    private fun setFirstDate(curDate: TravelDate) {
        setBelowDate(curDate)
    }

    private fun getDateScheduleList() {
        apiModule.travelApi.fetchTravelInfo(viewModel.getGroupId())
            .enqueue(object : Callback<TravelInfoRes> {
                override fun onResponse(
                    call: Call<TravelInfoRes>,
                    response: Response<TravelInfoRes>
                ) {
                    if (response.isSuccessful) {
                        val serverStartDateStrs =
                            response.body()?.data?.startDate.toString().split("-")
                        val serverEndDateStrs =
                            response.body()?.data?.endDate.toString().split("-")
                        val days = getDatesBetweenTwoDays(serverStartDateStrs, serverEndDateStrs)
                        setDataAdapter(days)
                        setFirstDate(setTravelDate(days)[0])
                        setFirstBottomRv(setTravelDate(days)[0])// 첫 날 일정을 리사이클러 뷰에 띄워준다.
                        binding.apply {
                            tvYear.text = datesList[0].year.toString()
                            tvMonth.text = datesList[0].month.toString()
                        }
                    } else {
                        Log.e("response", "fail")
                    }
                }

                override fun onFailure(call: Call<TravelInfoRes>, t: Throwable) {
                    Log.e("getDateScheduleListOnFailure", t.message.toString())
                }
            })
    }

    /* 서버에서 날짜 스트링을 넘겨주면 두 날짜 사이의 날짜들을 구하는 함수 */
    private fun getDatesBetweenTwoDays(
        serverStartDateStrs: List<String>,
        serverEndDateStrs: List<String>
    ): MutableList<LocalDate> {
        val startYear = serverStartDateStrs[0]
        val startMonth =
            if (serverStartDateStrs[1].toInt() < 10) "0".plus(serverStartDateStrs[1]) else serverStartDateStrs[1]
        val startDate =
            if (serverStartDateStrs[2].toInt() < 10) "0".plus(serverStartDateStrs[2]) else serverStartDateStrs[2]
        val endYear = serverEndDateStrs[0]
        val endMonth =
            if (serverEndDateStrs[1].toInt() < 10) "0".plus(serverEndDateStrs[1]) else serverEndDateStrs[1]
        val endDate =
            if (serverEndDateStrs[2].toInt() < 10) "0".plus(serverEndDateStrs[2]) else serverEndDateStrs[2]
        val startDateStr =
            startYear.plus("-").plus(startMonth).plus("-").plus(startDate)
        val endDateStr = endYear.plus("-").plus(endMonth).plus("-").plus(endDate)
        var parsedStartDate = LocalDate.parse(startDateStr)
        val parsedEndDate = LocalDate.parse(endDateStr)
        val totalDates = mutableListOf<LocalDate>()
        while (!parsedStartDate.isAfter(parsedEndDate)) {
            totalDates.add(parsedStartDate)
            parsedStartDate = parsedStartDate.plusDays(1)
        }
        return totalDates
    }

    private fun onAddScheduleBtnClick() {
        binding.btAddSchedule.setOnClickListener {
            val intent = Intent(requireContext(), ScheduleAddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTimeScheduleAdapter(list: List<PlanData>) {
        val timeAdapter = TimeScheduleAdapter()
        val timeRV = binding.rvScheduleMain
        timeAdapter.setItemList(list)
        timeRV.adapter = timeAdapter

        onBelowItemClickListener(timeAdapter, list) // 아이템 클릭 리스너
    }

    /**
     * Horizontal Recyclerview adapter 연결하는 부분,
     * 서버에서 data 받기 전까지는 dummy data로 적용시켜봄.
     */
    private fun setDataAdapter(dates: MutableList<LocalDate>) {
        val dateAdapter = DateScheduleAdapter()
        val dateRV = binding.rvDays
        dateRV.adapter = dateAdapter
        datesList =
            setTravelDate(dates) // 서버에서 가져온 데이터들 가공해서 리사이클러뷰에 들어갈 수 있게 해주고, 가공된 값을 itemList에 저장
        dateAdapter.setItemList(datesList)

        // Item Click Listener
        var itemClicked = false
        dateAdapter.setItemClickListener(object : DateScheduleAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                val curDate = datesList[position]
                if (position == 0) {
                    setFirstBottomRv(curDate)
                }
                setDate(datesList[position].year, datesList[position].month)
                setBelowDate(datesList[position])
                //setPlanData(position)
                getPlanData(
                    viewModel.getGroupId(),
                    (curDate.year).toString().plus("-")
                        .plus(if (curDate.month < 10) "0".plus(curDate.month) else curDate.month)
                        .plus("-")
                        .plus(if (curDate.date < 10) "0".plus(curDate.date) else curDate.date)
                ) // 클릭한 날의 데이터를 가져
                modifyClickedView(view, dateAdapter, position)

                // 날짜 리사이클러 뷰 첫 번째 아이템(날짜) 뷰 변경시켜주는 부분
                itemClicked = true
                if (itemClicked && !onceDone) {
                    dateAdapter.setFirstItem()
                }
                onceDone = true
            }
        })
    }

    // 서버로부터 date 날짜의 일정을 가져 옴
    private fun getPlanData(groupId: String, date: String) {
        Log.e("date", date)
        apiModule.scheduleApi.fetchCertainTravelSchedule(groupId, date)
            .enqueue(object : Callback<CertainTravelScheduleRes> {
                override fun onResponse(
                    call: Call<CertainTravelScheduleRes>,
                    response: Response<CertainTravelScheduleRes>
                ) {
                    if (response.isSuccessful) {
                        Log.e("response", response.body()?.data?.travelSchedule.toString())
                        setPlanData(response.body()?.data?.travelSchedule)
                    }
                }

                override fun onFailure(call: Call<CertainTravelScheduleRes>, t: Throwable) {
                    Log.e("getPlanData onFailure", t.message.toString())
                }
            })
    }

    private fun setTravelDate(dates: MutableList<LocalDate>): List<TravelDate> {
        var travelDateList = listOf<TravelDate>()
        // TODO 끝에 Time list1 주는 것은 변경해야 함
        for (i in dates.indices) {
            travelDateList = travelDateList.plus(
                TravelDate(
                    "D".plus((i + 1).toString()),
                    dates[i].year,
                    dates[i].monthValue,
                    dates[i].dayOfMonth
                )
            )
        }
        return travelDateList
    }

    /**
     * 클릭한 뷰 동그란 체크표시와
     * 텍스트 색상 변경하는 함수
     */
    private fun modifyClickedView(view: View, dateAdapter: DateScheduleAdapter, position: Int) {
        view.apply {
            findViewById<ImageView>(R.id.iv_selected_date).visibility = View.VISIBLE
            findViewById<TextView>(R.id.tv_item_date).setTextColor(
                ContextCompat.getColor(
                    view.context,
                    R.color.gray_white_pure_9
                )
            )
            dateAdapter.dateScheduleNotifyItemChanged(position)
        }
    }

    /***
     * 하단 부분 시간 별로 여행 일정을 보여주는 뷰 구현 함수
     */
    private fun setPlanData(list: List<BaseTravelScheduleDTO>?) {
        if (list.isNullOrEmpty()) {
            binding.apply {
                rvScheduleMain.visibility = View.GONE
                ivEmptyImg.visibility = View.VISIBLE
                tvNoSchedule.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvScheduleMain.visibility = View.VISIBLE
                ivEmptyImg.visibility = View.GONE
                tvNoSchedule.visibility = View.GONE
            }
            setTimeScheduleAdapter(changeScheduleDataType(list)) // recyclerview에 plan update
        }
    }

    private fun changeScheduleDataType(list: List<BaseTravelScheduleDTO>?): List<PlanData> {
        var planDataList = listOf<PlanData>()
        for (i in 0 until (list?.size ?: 0)) {
            val (hour, minute) = list!![i].travelScheduleFormatTime.split("-")[3].split(":")
            val formattedTimeStr = (if (hour.toInt() < 10) "0".plus(hour) else hour).plus(":")
                .plus(if (minute.toInt() < 10) "0".plus(minute) else minute)
            planDataList = when (i) {
                0 -> { // 첫 데이터일 때
                    planDataList.plus(
                        PlanData(
                            formattedTimeStr,
                            list[i].travelScheduleTitle,
                            list[i].travelScheduleMemo,
                            PlanData.FIRST_DATE_PLAN,
                            list[i].travelScheduleId
                        )
                    )
                }
                list.size - 1 -> { // 마지막 데이터일 때
                    planDataList.plus(
                        PlanData(
                            formattedTimeStr,
                            list[i].travelScheduleTitle,
                            list[i].travelScheduleMemo,
                            PlanData.LAST_DATE_PLAN,
                            list[i].travelScheduleId
                        )
                    )
                }
                else -> { // 중간 데이터일 때
                    planDataList.plus(
                        PlanData(
                            formattedTimeStr,
                            list[i].travelScheduleTitle,
                            list[i].travelScheduleMemo,
                            PlanData.MIDDLE_DATE_PLAN,
                            list[i].travelScheduleId
                        )
                    )
                }
            }
        }
        return planDataList
    }

    /**
     * 다른 달 날짜를 클릭하면 달을 표시하는 text를 바꿔줍니다.
     * 연도도 다른 연도가 들어오면 바꿔줍니다.
     * */
    private fun setDate(year: Int, month: Int) {
        if (binding.tvYear.text != year.toString()) {
            binding.tvYear.text = year.toString()
        }
        if (binding.tvMonth.text != month.toString()) {
            binding.tvMonth.text = month.toString()
        }
    }

    /**
     * Horizontal Recyclerview 아래에 며칠 째 여행인지 표시해주는 textview와 오늘 며칠인지 보여주는 textview 설정하는 부분
     */
    private fun setBelowDate(dateInfo: TravelDate) {
        val dday = dateInfo.dday
        val ddayStr = "DAY ".plus(dday.substring(1, dday.length))
        val monthStr = if (dateInfo.month >= 10) { // 달 계산할 때 10보다 작으면 앞에 0붙이는 것
            dateInfo.month.toString()
        } else {
            "0".plus(dateInfo.month.toString())
        }
        val dateStr = if (dateInfo.date >= 10) { // 일 계산할 때 10보다 작으면 앞에 0붙이는 것
            dateInfo.date.toString()
        } else {
            "0".plus(dateInfo.date.toString())
        }
        val dateFormatStr = "".plus(dateInfo.year.toString()).plus("-").plus(monthStr).plus("-")
            .plus(dateStr) // 2021-07-21 과 같은 스트링
        val dayOfWeek = LocalDate.parse(dateFormatStr).dayOfWeek // 라이브러리 통해 요일 가져오는 코드
        val dayOfWeekKr = when (dayOfWeek.toString()) {
            "MONDAY" -> {
                "월요일"
            }
            "TUESDAY" -> {
                "화요일"
            }
            "WEDNESDAY" -> {
                "수요일"
            }
            "THURSDAY" -> {
                "목요일"
            }
            "FRIDAY" -> {
                "금요일"
            }
            "SATURDAY" -> {
                "토요일"
            }
            "SUNDAY" -> {
                "일요일"
            }
            else -> {
                Log.e("ScheduleFragment", "DateParsingError")
            }
        }
        val fullDateStr =
            dateInfo.year.toString().plus(". ").plus(monthStr).plus(". ")
                .plus(dateStr).plus(" ").plus(dayOfWeekKr) // "2021.07.29 목요일" 과같은 스트링
        binding.tvDday.text = ddayStr
        binding.tvDate.text = fullDateStr
    }

    private fun onBelowItemClickListener(timeAdapter: TimeScheduleAdapter, list: List<PlanData>) {
        timeAdapter.setItemClickListener(object : TimeScheduleAdapter.ItemClickListener {
            override fun onTimeScheduleClick(view: View, position: Int) {
                val bsDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
                val sheetView = LayoutInflater.from(requireContext()).inflate(
                    R.layout.bottomsheet_add_schedule,
                    requireActivity().findViewById(R.id.cl_bottom_sheet_root)
                )
                sheetView.apply {
                    getDetailScheduleData(list[position].planId) // 서버에서 데이터를 가져옴
                    // TODO 아래 하드코딩 된 부분을 수정해야 한다.
                    Handler(Looper.getMainLooper()).postDelayed({
                        val iv = findViewById<ImageView>(R.id.iv_profile_pic)
                        Glide.with(iv.context)
                            .load(travelData.travelScheduleWriter.profileImageUrl)
                            .centerCrop()
                            .into(iv)
                        findViewById<TextView>(R.id.tv_bottom_sheet_writer).text =
                            travelData.travelScheduleWriter.name // 작성
                        findViewById<TextView>(R.id.tv_written_time).text =
                            travelData.travelScheduleCreateTime // 시간
                        findViewById<TextView>(R.id.tv_main_todo).text =
                            list[position].mainTodo
                        findViewById<TextView>(R.id.tv_user_time).text =
                            travelData.travelScheduleStartTime
                        findViewById<TextView>(R.id.tv_user_place).text =
                            travelData.travelScheduleLocation
                        findViewById<TextView>(R.id.tv_user_memo).text =
                            list[position].subTodo
                            //travelData.travelScheduleMemo
                    }, 900L)
                    findViewById<Button>(R.id.btn_bottom_sheet_delete).setOnClickListener {
                        val deleteDlg = Dialog(requireContext())
                        deleteDlg.setContentView(R.layout.dialog_delete_question)
                        deleteDlg.findViewById<Button>(R.id.btn_no).setOnClickListener {
                            deleteDlg.dismiss()
                        }
                        deleteDlg.show()
                    }
                    findViewById<Button>(R.id.btn_edit).setOnClickListener {
                        val intent = Intent(requireContext(), ScheduleEditActivity::class.java)
                        startActivity(intent)
                    }
                }
                bsDialog.setContentView(sheetView)
                bsDialog.show()
            }
        })
    }


    private fun getDetailScheduleData(scheduleId: String) {
        apiModule.scheduleApi.fetchTravelSchedule(viewModel.getGroupId(), scheduleId)
            .enqueue(object : Callback<TravelScheduleRes> {
                override fun onResponse(
                    call: Call<TravelScheduleRes>,
                    response: Response<TravelScheduleRes>
                ) {
                    if (response.isSuccessful) {
                        travelData = response.body()!!.data
                    }
                }

                override fun onFailure(call: Call<TravelScheduleRes>, t: Throwable) {
                    Log.e("getDetailScheduleData", t.message.toString())
                }
            })
    }
}