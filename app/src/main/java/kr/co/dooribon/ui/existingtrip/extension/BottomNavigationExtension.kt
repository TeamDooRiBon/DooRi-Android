package kr.co.dooribon.utils

import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.board.BoardFragment
import kr.co.dooribon.ui.existingtrip.schedule.ScheduleFragment
import kr.co.dooribon.ui.existingtrip.tendency.TendencyFragment
import kr.co.dooribon.ui.existingtrip.wishlist.WishListFragment

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
            R.id.nav_folder -> {
                fragmentManager.beginTransaction().replace(
                    R.id.fcv_existing_trip,
                    WishListFragment()
                ).commit()
            }
            else -> throw IllegalArgumentException("No Such Fragment inside this Project!")
        }
        true
    }
}