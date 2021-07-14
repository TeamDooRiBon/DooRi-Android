package kr.co.dooribon.ui.existingtrip.board.extension

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.board.fragment.CheckListFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.GoalFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.MustKnowFragment
import kr.co.dooribon.ui.existingtrip.board.fragment.RoleAllocFragment

fun TabLayout.initializeBoardTabNavigation(fragmentManager: FragmentManager, groupId : String) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            val groupIdBundle = Bundle()
            groupIdBundle.putString("groupId", groupId) // Put anything what you want
            val goalFrag = GoalFragment()
            goalFrag.setArguments(groupIdBundle)

            Log.e("goalFragmentArguments", goalFrag.arguments.toString())
            Log.e("goalFragmentArgumentsGroupId", groupId.toString())
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
                        MustKnowFragment()
                    ).commit()
                }
                2 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_board,
                        RoleAllocFragment()
                    ).commit()
                }
                3 -> {
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