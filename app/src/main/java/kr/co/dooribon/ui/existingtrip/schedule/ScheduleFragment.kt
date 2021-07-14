package kr.co.dooribon.ui.existingtrip.schedule

import android.app.Dialog
import android.content.Intent
import android.icu.util.LocaleData
import android.os.Bundle
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.ParticipateTravelRes
import kr.co.dooribon.api.remote.TravelInfoDTO
import kr.co.dooribon.api.remote.TravelInfoRes
import kr.co.dooribon.api.remote.TravelScheduleRes
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
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.LongStream

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var datesList: List<TravelDate>
    private lateinit var timeList1: List<PlanData>
    private lateinit var timeList2: List<PlanData>
    private lateinit var timeList3: List<PlanData>
    private var onceDone = false // 날짜 리사이클러뷰 아이템 클릭하면 true로 변경.

    private val viewModel by activityViewModels<ExistingTripViewModel>()
    // 날짜 리사이클러 뷰에서 두 번째 클릭부터는 리사이클러 뷰 첫번째 날짜를 다시
    // 바꿔줄 필요가 없어 true로 변경해서 다시 접근하지 않도록 해준다.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)

        setDummyTimeList()
        setDataAdapter()
        setTimeScheduleAdapter(timeList1)
        onAddScheduleBtnClick()
        getDateScheduleList()

        return binding.root
    }

    private fun getDateScheduleList() {
        Log.e("groupId", viewModel.getGroupId())
        apiModule.travelApi.fetchTravelInfo(viewModel.getGroupId()).enqueue(object : Callback<TravelInfoRes>{
            override fun onResponse(call: Call<TravelInfoRes>, response: Response<TravelInfoRes>) {
                if(response.isSuccessful){
                    Log.e("check", response.body()?.data?.startDate.toString())
                    val serverStartDateStrs = response.body()?.data?.startDate.toString().split("-")
                    val serverEndDateStrs = response.body()?.data?.startDate.toString().split("-")
                    val startYear = serverStartDateStrs[0]
                    val startMonth = serverStartDateStrs[1]
                    val startDate = serverStartDateStrs[2]
                    val endYear = serverEndDateStrs[0]
                    val endMonth = serverEndDateStrs[1]
                    val endDate = serverEndDateStrs[2]
                    // TODO : 두 날짜 사이(startDate, endDate)의 날들을 구해 리스트에 추가하여 리사이클러 뷰에 넘겨줘야 함.
                }else{
                    Log.e("response", "fail")
                }
            }

            override fun onFailure(call: Call<TravelInfoRes>, t: Throwable) {
                Log.e("getDateScheduleListOnFailure", t.message.toString())
            }
        })
    }



    private fun onAddScheduleBtnClick() {
        binding.btAddSchedule.setOnClickListener {
            val intent = Intent(requireContext(), ScheduleAddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setTimeScheduleAdapter(planList: List<PlanData>) {
        val timeAdapter = TimeScheduleAdapter()
        val timeRV = binding.rvScheduleMain
        timeAdapter.setItemList(planList)
        timeRV.adapter = timeAdapter

        onBelowItemClickListener(timeAdapter) // 아이템 클릭 리스너
    }

    private fun setDummyTimeList() {
        timeList1 = listOf( // dummy data
            PlanData("10:00", "김포공항 앞에서 모이기", "2304 버스 정류장 찾아보기", PlanData.FIRST_DATE_PLAN),
            PlanData("12:00", "인천공항으로 출발", "여권 꼭 챙기기", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "인천공항으로 출발", "여권 꼭 챙기기", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "인천공항으로 출발", "여권 꼭 챙기기", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "인천공항으로 출발", "여권 꼭 챙기기", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "인천공항으로 출발", "여권 꼭 챙기기", PlanData.LAST_DATE_PLAN)
        )
        timeList2 = listOf( // dummy data
            PlanData("10:00", "여행 가즈아", "여행 갈 곳 찾아보기", PlanData.FIRST_DATE_PLAN),
            PlanData("12:00", "가즈아 여행", "제주도갈까요", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "송훈기", "기훈송", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "조예진", "진예조", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "이원중", "중원이", PlanData.MIDDLE_DATE_PLAN),
            PlanData("12:00", "두리번", "두리안", PlanData.LAST_DATE_PLAN)
        )
        timeList3 = listOf( // dummy data
        )
    }

    /**
     * Horizontal Recyclerview adapter 연결하는 부분,
     * 서버에서 data 받기 전까지는 dummy data로 적용시켜봄.
     */
    private fun setDataAdapter() {
        val dateAdapter = DateScheduleAdapter()
        val dateRV = binding.rvDays
        dateRV.adapter = dateAdapter
        datesList = listOf( // dummy data
            TravelDate("D1", 2021, 7, 29, timeList1),
            TravelDate("D2", 2021, 7, 30, timeList2),
            TravelDate("D3", 2021, 7, 31, timeList3),
            TravelDate("D4", 2021, 8, 1, timeList2),
            TravelDate("D5", 2021, 8, 2, timeList1),
            TravelDate("D6", 2021, 8, 3, timeList2),
            TravelDate("D7", 2021, 8, 4, timeList1),
            TravelDate("D8", 2021, 8, 5, timeList1),
            TravelDate("D9", 2021, 8, 6, timeList1),
            TravelDate("D10", 2022, 9, 7, timeList1)
        )
        dateAdapter.setItemList( // dummy data
            datesList
        )

        var itemClicked = false
        // Item Click Listener
        dateAdapter.setItemClickListener(object : DateScheduleAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                setDate(datesList[position].year, datesList[position].month)
                setBelowDate(datesList[position])
                setPlanData(position)
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
    private fun setPlanData(position: Int) {
        if (datesList[position].planData.isNullOrEmpty()) { // plan이 아직 없다면
            binding.apply {
                rvScheduleMain.visibility = View.GONE
                ivEmptyImg.visibility = View.VISIBLE
                tvNoSchedule.visibility = View.VISIBLE
            }
        } else { // plan이 있다면
            binding.apply {
                rvScheduleMain.visibility = View.VISIBLE
                ivEmptyImg.visibility = View.GONE
                tvNoSchedule.visibility = View.GONE
            }
            setTimeScheduleAdapter(datesList[position].planData) // recyclerview에 plan update
        }
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

    private fun onBelowItemClickListener(timeAdapter: TimeScheduleAdapter) {
        timeAdapter.setItemClickListener(object : TimeScheduleAdapter.ItemClickListener {
            override fun onTimeScheduleClick(view: View, position: Int) {
                val bsDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetTheme)
                val sheetView = LayoutInflater.from(requireContext()).inflate(
                    R.layout.bottomsheet_add_schedule,
                    requireActivity().findViewById(R.id.cl_bottom_sheet_root)
                )
                sheetView.apply {
                    findViewById<Button>(R.id.btn_delete).setOnClickListener {
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
}