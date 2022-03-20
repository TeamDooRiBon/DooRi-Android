package kr.co.dooribon.ui.maketrip

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.co.dooribon.ui.maketrip.MakeTripConstants.DETAIL_PAGE_COUNT
import kr.co.dooribon.ui.maketrip.details.*

class SlidePagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = DETAIL_PAGE_COUNT

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> MakeTripNameFragment()
        1 -> SetTripLocationFragment()
        2 -> TripCalendarFragment()
        3 -> TripPhotoFragment()
        else -> TripMakingDoneFragment()
    }
}