package kr.co.dooribon.ui.triptendency

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTripTendencyBinding
import kr.co.dooribon.domain.entity.TripTendency
import kr.co.dooribon.ui.triptendency.adapter.TripTendencyAdapter
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel

/**
 * 이제 12개의 프래그먼트를 viewPager에 보여줘야 하는데
 * 문제가 뭐냐면 tabLayout 현재 viewPager가 가지고 있는 fragment의 갯수를 알아야한다는 것이 문제다.
 *
 * ViewPager.setCurrentItem 메소드를 사용해서 뷰모델에 보여지는 뷰의 포지션을 담아두고 이에 맞춰 작업을 하면 될거 같음!!!!
 */
class TripTendencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripTendencyBinding

    private val viewModel by viewModels<TripTendencyViewModel>()

    private lateinit var tripTendencyAdapter: TripTendencyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_tendency)

        tripTendencyAdapter = TripTendencyAdapter()

        binding.vpTripTendencyTest.apply {
            adapter = tripTendencyAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        TabLayoutMediator(
            binding.tabTripTendency,
            binding.vpTripTendencyTest
        ){ _, _ -> }.attach()

        tripTendencyAdapter.submitItem(
            listOf(
                TripTendency(
                    1, "송훈기 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1,"안괜찮아"),
                        TripTendency.TripTendencyQuestion(2,"괜찮아"),
                        TripTendency.TripTendencyQuestion(3,"공감 못함"),
                        TripTendency.TripTendencyQuestion(4,"처치대상 0호")
                    )
                ),
                TripTendency(
                    2, "조예진 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1,"호기심 대마왕"),
                        TripTendency.TripTendencyQuestion(2,"아니 근데"),
                        TripTendency.TripTendencyQuestion(3,"킹받게 함"),
                        TripTendency.TripTendencyQuestion(4,"처치대상 1호...")
                    )
                ),
                TripTendency(
                    3, "이원중 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1,"개잘함"),
                        TripTendency.TripTendencyQuestion(2,"방금 선그어서 멋져보임"),
                        TripTendency.TripTendencyQuestion(3,"가끔 킹받게 함"),
                        TripTendency.TripTendencyQuestion(4,"처치대상 2호")
                    )
                )
            )
        )
    }
}