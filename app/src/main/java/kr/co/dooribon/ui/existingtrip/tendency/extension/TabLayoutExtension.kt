package kr.co.dooribon.ui.existingtrip.tendency.extension

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.tendency.fragment.DetailFragment
import kr.co.dooribon.ui.existingtrip.tendency.fragment.MemberFragment

fun TabLayout.initializeTendencyNavigation(fragmentManager: FragmentManager , bundle: Bundle) {
    val memberFragment = MemberFragment()

    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            when (tab.position) {
                0 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_tendency,
                        MemberFragment()
                    ).commit()
                }
                1 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_tendency,
                        DetailFragment()
                    ).commit()
                }
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    })
}