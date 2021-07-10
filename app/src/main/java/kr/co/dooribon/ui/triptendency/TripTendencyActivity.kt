package kr.co.dooribon.ui.triptendency

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityTripTendencyBinding
import kr.co.dooribon.dialog.TripTendencyTestExitDialog
import kr.co.dooribon.dialog.TripTendencyTestResultLoadingDialog
import kr.co.dooribon.domain.entity.TripTendency
import kr.co.dooribon.ui.triptendency.adapter.TripTendencyAdapter
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel
import kr.co.dooribon.utils.shortToast

/**
 * Issue 사항
 * TODO : 데이터의 선택된 순서는 리스트에 정확하게 들어가는데 문제는 문제지를 뒤로 돌아갔을 떄 데이터가 겹쳐서 보여지는 문제가 발생한다.
 * TODO : 밀리는 거 같음 lastSelectedPosition의 로직을 다시한번 생각해봐야할거 같습니다.
 */
class TripTendencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripTendencyBinding

    private val viewModel by viewModels<TripTendencyViewModel>()

    private lateinit var tripTendencyAdapter: TripTendencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_tendency)
        binding.vm = viewModel
        binding.activity = this
        tripTendencyAdapter = TripTendencyAdapter(viewModel)

        configureQuestionPager()
        configureTab()
        observeQuestionPosition()
        setDummy()
    }

    override fun onBackPressed() {
        exitTripTendencyTest()
    }

    private fun observeQuestionPosition() {
        viewModel.questionPosition.observe(this) {
            if (it == tripTendencyAdapter.itemCount) {
                // 질문지에 대한 선택들이 전부 선택이 되었는지 검사하는 로직이 필요하다.
                for (i in 0 until tripTendencyAdapter.itemCount) {
                    if (viewModel.lastQuestionSelectedPosition.value!![i] == -1) {
                        shortToast("질문을 전부 선택하셨는지 확인해주세요")
                        break
                    }
                }
                TripTendencyTestResultLoadingDialog().show(
                    supportFragmentManager,
                    RESULT_LOADING_NAVIGATE_TAG
                )
            } else {
                binding.vpTripTendencyTest.currentItem = it
            }
        }
    }

    private fun configureTab() {
        TabLayoutMediator(
            binding.tabTripTendency,
            binding.vpTripTendencyTest
        ) { _, _ -> }.attach()
    }

    private fun configureQuestionPager() {
        binding.vpTripTendencyTest.apply {
            adapter = tripTendencyAdapter
            offscreenPageLimit = 1
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            isUserInputEnabled = false
        }
    }

    private fun setDummy() {
        tripTendencyAdapter.submitItem(
            listOf(
                TripTendency(
                    1, "송훈기 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "안괜찮아"),
                        TripTendency.TripTendencyQuestion(2, "괜찮아"),
                        TripTendency.TripTendencyQuestion(3, "공감 못함"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 0호")
                    )
                ),
                TripTendency(
                    2, "조예진 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "호기심 대마왕"),
                        TripTendency.TripTendencyQuestion(2, "아니 근데"),
                        TripTendency.TripTendencyQuestion(3, "킹받게 함"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 1호...")
                    )
                ),
                TripTendency(
                    3, "이원중 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "방금 선그어서 멋져보임"),
                        TripTendency.TripTendencyQuestion(3, "가끔 킹받게 함"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 2호")
                    )
                ),
                TripTendency(
                    4, "김태현 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "아요대장"),
                        TripTendency.TripTendencyQuestion(3, "노는거 좋아함"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 3호")
                    )
                ),
                TripTendency(
                    5, "한상진 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "캘린더 장인"),
                        TripTendency.TripTendencyQuestion(3, "합숙중임"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 4호")
                    )
                ),
                TripTendency(
                    6, "이민재 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "야구장인"),
                        TripTendency.TripTendencyQuestion(3, "홈화면 중인걸로 암"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 5호")
                    )
                ),
                TripTendency(
                    7, "박유진 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "피엠임"),
                        TripTendency.TripTendencyQuestion(3, "기획대장"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 6호")
                    )
                ),
                TripTendency(
                    8, "김민영 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "디자인 잘함"),
                        TripTendency.TripTendencyQuestion(3, "내가 마니또 였음"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 7호")
                    )
                ),
                TripTendency(
                    9, "유지인 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "디자인 잘함"),
                        TripTendency.TripTendencyQuestion(3, "낯가린다고 했는데 잘 안가리는거 같음"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 8호")
                    )
                ),
                TripTendency(
                    10, "김인우 이대로 괜찮은가", listOf(
                        TripTendency.TripTendencyQuestion(1, "개잘함"),
                        TripTendency.TripTendencyQuestion(2, "디자인 잘함"),
                        TripTendency.TripTendencyQuestion(3, "말 속에 뼈가 있을거같음"),
                        TripTendency.TripTendencyQuestion(4, "처치대상 9호")
                    )
                )
            )
        )
    }

    fun exitTripTendencyTest() {
        TripTendencyTestExitDialog().show(supportFragmentManager, EXIT_NAVIGATE_TAG)
    }

    companion object {
        private const val EXIT_NAVIGATE_TAG = "EXIT"
        private const val RESULT_LOADING_NAVIGATE_TAG = "LOADING"
    }
}