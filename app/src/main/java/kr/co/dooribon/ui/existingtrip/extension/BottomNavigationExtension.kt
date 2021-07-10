package kr.co.dooribon.utils

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

fun BottomNavigationView.initExistingTripBottomNavigation(fragmentManager: FragmentManager) {
    this.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_people -> {
                fragmentManager.beginTransaction().replace(
                    R.id.fcv_existing_trip,
                    TendencyFragment()
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
}