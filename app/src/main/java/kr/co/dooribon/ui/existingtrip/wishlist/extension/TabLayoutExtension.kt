package kr.co.dooribon.ui.existingtrip.extension

import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.AccommodationFragment
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.AllWishListFragment
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.HotRestaurantFragment
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.TourismFragment


fun TabLayout.initializeWishListTab(){
    this.apply {
        addTab(this.newTab().setText("전체"))
        addTab(this.newTab().setText("관광"))
        addTab(this.newTab().setText("숙소"))
        addTab(this.newTab().setText("맛집"))
    }
}

fun TabLayout.initializeTab(tabName : List<String>){
    tabName.forEach {
        addTab(this.newTab().setText(it))
    }
}

fun TabLayout.initializeWishListTabNavigation(fragmentManager: FragmentManager){
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab) {
            when(tab.position){
                0 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_wish_list,
                        AllWishListFragment()
                    ).commit()
                }
                1 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_wish_list,
                        TourismFragment()
                    ).commit()
                }
                2 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_wish_list,
                        AccommodationFragment()
                    ).commit()
                }
                3 -> {
                    fragmentManager.beginTransaction().replace(
                        R.id.fcv_wish_list,
                        HotRestaurantFragment()
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

