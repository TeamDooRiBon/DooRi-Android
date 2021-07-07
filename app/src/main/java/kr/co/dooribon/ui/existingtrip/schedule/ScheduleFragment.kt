package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
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
        dateAdapter.setItemList( // dummy data
            listOf(
                TravelDate("D1", 29, 7),
                TravelDate("D2", 30, 7),
                TravelDate("D3", 31, 7),
                TravelDate("D4", 1, 8),
                TravelDate("D5", 2, 8),
            )
        )
    }

}