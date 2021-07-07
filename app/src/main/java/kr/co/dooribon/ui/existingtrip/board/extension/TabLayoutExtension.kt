package kr.co.dooribon.ui.existingtrip.board.extension

import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.board.fragment.CheckListFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.CommunicationFragment

fun TabLayout.initializeBoardTabNavigation(fragmentManager: FragmentManager){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab) {
            when(tab.position){
                0 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        CommunicationFragment()
                    ).commit()
                }
                1 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        CheckListFragment()
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