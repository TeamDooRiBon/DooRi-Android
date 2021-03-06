package kr.co.dooribon.ui.triptendency

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.ActivityTripTendencyBinding
import kr.co.dooribon.dialog.TripTendencyTestExitDialog
import kr.co.dooribon.dialog.TripTendencyTestResultLoadingDialog
import kr.co.dooribon.ui.triptendency.adapter.TripTendencyAdapter
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel
import kr.co.dooribon.utils.debugE
import kr.co.dooribon.utils.shortToast

/**
 * Issue 사항
 * TODO : 데이터의 선택된 순서는 리스트에 정확하게 들어가는데 문제는 문제지를 뒤로 돌아갔을 떄 데이터가 겹쳐서 보여지는 문제가 발생한다.
 * TODO : 홈에서 성향테스트 하는 로직이 똑같이 있는데 이건 그냥 GROUPID를 제외한 녀석으로 하면 되기 때문에 복사 붙여넣기 하면 될 거 같음
 */
class TripTendencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripTendencyBinding

    private val viewModel by viewModels<TripTendencyViewModel> {
        viewModelModule.provideTripTendencyViewModelFactory()
    }

    private lateinit var tripTendencyAdapter: TripTendencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_tendency)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        binding.activity = this

        tripTendencyAdapter = TripTendencyAdapter(viewModel)

        intent.getStringExtra("groupId")?.let {
            viewModel.initializeGroupId(it)
        }

        observeQuestionPosition()
        observeToastEvent()
        observeTravelTendencyQuestions()
        observeTravelTendencyResult()
        configureQuestionPager()
        configureTab()
    }

    private fun observeTravelTendencyResult() {
        viewModel.travelTendencyResult.observe(this) {
            val imageBundle = Bundle()
            imageBundle.putString(
                "resultImageUrl",
                it.tendencyResultImageUrl
            )
            imageBundle.putString(
                "resultImageName",
                it.tendencyTitle
            )
            imageBundle.putString(
                "resultUserName",
                it.memberName
            )
            val tripTendencyTestResultLoadingDialog = TripTendencyTestResultLoadingDialog()
            tripTendencyTestResultLoadingDialog.arguments = imageBundle
            tripTendencyTestResultLoadingDialog.show(
                supportFragmentManager,
                RESULT_LOADING_NAVIGATE_TAG
            )
        }
    }

    private fun observeTravelTendencyQuestions() {
        viewModel.travelTendencyQuestions.observe(this) {
            tripTendencyAdapter.submitItem(it)
        }
    }

    private fun observeToastEvent() {
        viewModel.toastEventLiveData.observe(this) {
            shortToast(it)
        }
    }

    override fun onBackPressed() {
        exitTripTendencyTest()
    }

    private fun observeQuestionPosition() {
        viewModel.questionPosition.observe(this) {
            binding.vpTripTendencyTest.currentItem = it
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

    fun exitTripTendencyTest() {
        TripTendencyTestExitDialog().show(supportFragmentManager, EXIT_NAVIGATE_TAG)
    }

    // 다음 문항으로 가는 버튼 클릭 이벤트이기 때문에 여기에 조건을 걸어주는 것이 옳다고 생각합니다.
    fun navigateNextPage() {
        if (!viewModel.lastQuestionSelectedPosition.value!!
                .contains(-1) && viewModel.questionPosition.value == MAX_QUESTION_INDEX_COUNT
        ) {
            // 선택한 데이터들을 전부 계산하고
            synchronized(viewModel.calculateQuestionWeight()) {}
            runCatching {
                viewModel.storeMyTravelTendency()
            }.onFailure {
                debugE(it)
            }
        } else {
            viewModel.nextPage()
        }
    }

    companion object {
        private const val EXIT_NAVIGATE_TAG = "EXIT"
        private const val RESULT_LOADING_NAVIGATE_TAG = "LOADING"
        private const val MAX_QUESTION_INDEX_COUNT = 9
    }
}