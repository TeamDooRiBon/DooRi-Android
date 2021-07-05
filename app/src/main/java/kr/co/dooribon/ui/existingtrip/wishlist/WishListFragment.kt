package kr.co.dooribon.ui.existingtrip.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentWishListBinding
import kr.co.dooribon.ui.existingtrip.util.initializeWishListTab
import kr.co.dooribon.ui.existingtrip.util.initializeWishListTabNavigation
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.AccommodationFragment
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.AllWishListFragment
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.HotRestaurantFragment
import kr.co.dooribon.ui.existingtrip.wishlist.fragment.TourismFragment
import kr.co.dooribon.utils.AutoClearBinding

class WishListFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentWishListBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentWishListBinding.inflate(layoutInflater,container,false).also { FragmentWishListBinding ->
        binding = FragmentWishListBinding
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tabWishList.initializeWishListTab()
        binding.tabWishList.initializeWishListTabNavigation(childFragmentManager)
    }
}