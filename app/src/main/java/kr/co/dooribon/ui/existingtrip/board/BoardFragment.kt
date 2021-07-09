package kr.co.dooribon.ui.existingtrip.board

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

    override fun onResume() {
        super.onResume()
        setDefaultTab()
    }

    /**
     * 탭 레이아웃에 커스텀 뷰를 넣으면 처음으로 나오는 Default tab이
     * 첫 번째 탬으로 선택되지 않는 현상이 있어서 아래 코드를 추가해서 해결함.
     * https://stackoverflow.com/a/52013898/14155735
     * 위 링크 참고했음.
     * */
    private fun setDefaultTab() {
        binding.tabBoard.getTabAt(1)!!.select()
        binding.tabBoard.getTabAt(0)!!.select()
    }

    private fun onTabClickListener() {
        binding.tabBoard.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("position", tab?.position.toString())
                setTabItem(tab?.position ?: Log.e("BoardFragment", "position null error"), tab!!)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    // 사실 이전에 클릭했던 pos갖고 있어서 그 뷰만 바꿔주면 되기는 함
    private fun setTabItem(pos: Int, tab: TabLayout.Tab) {
        tab.customView!!.findViewById<ImageView>(R.id.iv_tab_img).setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_icon_board_goal_active
            )
        )
        // 아이템 클릭시 아이템 색을 바꾼다. 클릭되지 않은 것은 회색으로 다시 처리해준다.
        for (i in 0 until 4) {
            if (i != pos) {
                binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<TextView>(R.id.tv_tab_text)
                    .setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray_gray_5_main
                        )
                    )
                when (i) {
                    0 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_goal_inactive
                                )
                            )
                    }
                    1 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_aim_inactive
                                )
                            )
                    }
                    2 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_role_inactive
                                )
                            )
                    }
                    3 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_check_inactive
                                )
                            )
                    }
                }
            } else {
                binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<TextView>(R.id.tv_tab_text)
                    .setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.main_point_orange
                        )
                    )
                when (i) {
                    0 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_goal_active
                                )
                            )
                    }
                    1 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_aim_active
                                )
                            )
                    }
                    2 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_role_active
                                )
                            )
                    }
                    3 -> {
                        binding.tabBoard.getTabAt(i)!!.customView!!.findViewById<ImageView>(R.id.iv_tab_img)
                            .setImageDrawable(
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.ic_icon_board_check_active
                                )
                            )
                    }
                }
            }
        }
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

        firstImg?.setImageResource(R.drawable.ic_icon_board_goal_active)
        firstTxt?.text = getString(R.string.travel_goal)
        firstTxt?.setTextColor(ContextCompat.getColor(requireContext(), R.color.main_point_orange))
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