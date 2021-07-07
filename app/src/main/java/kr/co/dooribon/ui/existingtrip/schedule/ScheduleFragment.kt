package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentScheduleBinding
import kr.co.dooribon.ui.existingtrip.schedule.adapters.DateScheduleAdapter
import kr.co.dooribon.ui.existingtrip.schedule.adapters.TravelDate

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var datesList: List<TravelDate>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        setDataAdapter()
        return binding.root
    }

    private fun setDataAdapter() {
        val dateAdapter = DateScheduleAdapter()
        val dateRV = binding.rvDays
        dateRV.adapter = dateAdapter
        datesList = listOf(
            TravelDate("D1", 2021, 7, 29),
            TravelDate("D2", 2021, 7, 30),
            TravelDate("D3", 2021, 7, 31),
            TravelDate("D4", 2021, 8, 1),
            TravelDate("D5", 2021, 8, 2),
            TravelDate("D6", 2021, 8, 3),
            TravelDate("D7", 2021, 8, 4),
            TravelDate("D8", 2021, 8, 5),
            TravelDate("D9", 2021, 8, 6),
            TravelDate("D10", 2022, 9, 7),
        )
        dateAdapter.setItemList( // dummy data
            datesList
        )

        dateAdapter.setItemClickListener(object : DateScheduleAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.e("position", position.toString())
                setDate(datesList[position].year, datesList[position].month)
            }
        })
    }

    /**
     * 다른 달 날짜를 클릭하면 달을 표시하는 text를 바꿔줍니다.
     * 연도도 다른 연도가 들어오면 바꿔줍니다.
     * */
    private fun setDate(year:Int, month: Int) {
        if (binding.tvYear.text != year.toString()) {
            binding.tvYear.text = year.toString()
        }
        if (binding.tvMonth.text != month.toString()) {
            binding.tvMonth.text = month.toString()
        }
    }

}