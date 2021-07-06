package kr.co.dooribon.ui.existingtrip.wishlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.dooribon.databinding.FragmentWishListBinding
import kr.co.dooribon.ui.existingtrip.extension.initializeTab
import kr.co.dooribon.ui.existingtrip.extension.initializeWishListTabNavigation
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
        binding.tabWishList.initializeTab(listOf("전체","관광","숙소","맛집"))
        binding.tabWishList.initializeWishListTabNavigation(childFragmentManager)
    }
}