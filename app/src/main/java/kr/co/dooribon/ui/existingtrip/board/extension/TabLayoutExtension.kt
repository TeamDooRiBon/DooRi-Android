package kr.co.dooribon.ui.existingtrip.board.extension

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.board.fragment.CheckListFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.GoalFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.MustKnowFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.RoleAllocFragment

fun TabLayout.initializeBoardTabNavigation(fragmentManager: FragmentManager, groupId: String) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            val groupIdBundle = Bundle()
            groupIdBundle.putString("groupId", groupId) // Put anything what you want
            val goalFrag = GoalFragment()
            val checkListFrag = CheckListFragment()
            val mustKnowFrag = MustKnowFragment()
            val roleAllocFrag = RoleAllocFragment()
            goalFrag.arguments = groupIdBundle
            checkListFrag.arguments = groupIdBundle
            mustKnowFrag.arguments = groupIdBundle
            roleAllocFrag.arguments = groupIdBundle
            when (tab.position) {
                0 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        goalFrag
                    ).commit()
                }
                1 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        mustKnowFrag
                    ).commit()
                }
                2 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        roleAllocFrag
                    ).commit()
                }
                3 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        checkListFrag
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