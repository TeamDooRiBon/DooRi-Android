package kr.co.dooribon.utils

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.board.BoardFragment
import kr.co.dooribon.ui.existingtrip.schedule.ScheduleFragment
import kr.co.dooribon.ui.existingtrip.tendency.TendencyFragment

fun TabLayout.initializeTab(list: List<String>) {
    list.forEach {
        this.addTab(newTab().setText(it))
    }
}

fun BottomNavigationView.initExistingTripBottomNavigation(
    fragmentManager: FragmentManager,
    bundle: Bundle
) {
    val tendencyFragment = TendencyFragment()
    tendencyFragment.arguments = bundle
    this.setOnItemSelectedListener {
        when (it.itemId) {
            R.id.nav_people -> {
                fragmentManager.beginTransaction().replace(
                    R.id.fcv_existing_trip,
                    tendencyFragment
                ).commit()
            }
            R.id.nav_calendar -> {
                fragmentManager.beginTransaction().replace(
                    R.id.fcv_existing_trip,
                    ScheduleFragment()
                ).commit()
            }
            R.id.nav_board -> {
                fragmentManager.beginTransaction().replace(
                    R.id.fcv_existing_trip,
                    BoardFragment()
                ).commit()
            }
            else -> throw IllegalArgumentException("No Such Fragment inside this Project!")
        }
        true
    }
    // 일단 초기화면에서 bundle로 데이터를 옮겨줘야하는데 이 때문에 name을 사용하지 못함
    fragmentManager.beginTransaction().replace(R.id.fcv_existing_trip, tendencyFragment).commit()
}