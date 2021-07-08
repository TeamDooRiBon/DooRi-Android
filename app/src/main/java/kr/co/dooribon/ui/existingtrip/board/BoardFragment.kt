package kr.co.dooribon.ui.existingtrip.board

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentBoardBinding
import kr.co.dooribon.ui.existingtrip.board.extension.initializeBoardTabNavigation
import kr.co.dooribon.ui.existingtrip.extension.initializeTab
import kr.co.dooribon.utils.AutoClearBinding

class BoardFragment : Fragment() {

    private var binding by AutoClearBinding<FragmentBoardBinding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBoardBinding.inflate(layoutInflater, container, false)
        .also { FragmentBoardBinding ->
            binding = FragmentBoardBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureTabNavigation()
        setTabDesign()
        setTabAttribute()
        onTabClickListener()
    }

    private fun onTabClickListener() {
        binding.tabBoard.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("position", tab?.position.toString())
                when(tab?.position){
                    0->{
                        tab.customView!!.findViewById<ImageView>(R.id.iv_tab_img).setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_icon_board_goal_active))
                    }
                    1->{
                        tab.customView!!.findViewById<ImageView>(R.id.iv_tab_img).setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_icon_board_aim_inactive))
                    }
                    2->{
                        tab.customView!!.findViewById<ImageView>(R.id.iv_tab_img).setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_icon_board_role_inactive))
                    }
                    3->{
                        tab.customView!!.findViewById<ImageView>(R.id.iv_tab_img).setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_icon_board_check_inactive))
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setTabAttribute() {
        binding.tabBoard.apply {
            tabRippleColor = null // 탭 리플 효과 제거
        }
    }

    private fun setTabDesign() {
        val firstView = layoutInflater.inflate(R.layout.custom_tab_board, null)
        val firstImg = firstView.findViewById<ImageView>(R.id.iv_tab_img)
        val firstTxt = firstView.findViewById<TextView>(R.id.tv_tab_text)

        val secondView = layoutInflater.inflate(R.layout.custom_tab_board, null)
        val secondImg = secondView.findViewById<ImageView>(R.id.iv_tab_img)
        val secondTxt = secondView.findViewById<TextView>(R.id.tv_tab_text)

        val thirdView = layoutInflater.inflate(R.layout.custom_tab_board, null)
        val thirdImg = thirdView.findViewById<ImageView>(R.id.iv_tab_img)
        val thirdTxt = thirdView.findViewById<TextView>(R.id.tv_tab_text)

        val fourthView = layoutInflater.inflate(R.layout.custom_tab_board, null)
        val fourthImg = fourthView.findViewById<ImageView>(R.id.iv_tab_img)
        val fourthTxt = fourthView.findViewById<TextView>(R.id.tv_tab_text)

        firstImg?.setImageResource(R.drawable.ic_icon_board_goal_inactive)
        firstTxt?.text = getString(R.string.travel_goal)
        binding.tabBoard.getTabAt(0)!!.customView = firstView

        secondImg?.setImageResource(R.drawable.ic_icon_board_aim_inactive)
        secondTxt?.text = getString(R.string.must_know)
        binding.tabBoard.getTabAt(1)!!.customView = secondView

        thirdImg?.setImageResource(R.drawable.ic_icon_board_role_inactive)
        thirdTxt?.text = getString(R.string.role_alloc)
        binding.tabBoard.getTabAt(2)!!.customView = thirdView

        fourthImg?.setImageResource(R.drawable.ic_icon_board_check_inactive)
        fourthTxt?.text = getString(R.string.checklist)
        binding.tabBoard.getTabAt(3)!!.customView = fourthView
    }

    private fun configureTabNavigation() {
        binding.tabBoard.initializeTab(listOf("여행 목표", "꼭 알아줘", "역할 분담", "체크리스트"))
        binding.tabBoard.initializeBoardTabNavigation(childFragmentManager)
    }
}