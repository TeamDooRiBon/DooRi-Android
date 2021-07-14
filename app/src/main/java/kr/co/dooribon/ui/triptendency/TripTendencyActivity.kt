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
import kr.co.dooribon.di.ViewModelModule
import kr.co.dooribon.dialog.TripTendencyTestExitDialog
import kr.co.dooribon.dialog.TripTendencyTestResultLoadingDialog
import kr.co.dooribon.ui.triptendency.adapter.TripTendencyAdapter
import kr.co.dooribon.ui.triptendency.viewModel.TripTendencyViewModel
import kr.co.dooribon.utils.shortToast

/**
 * Issue 사항
 * TODO : 데이터의 선택된 순서는 리스트에 정확하게 들어가는데 문제는 문제지를 뒤로 돌아갔을 떄 데이터가 겹쳐서 보여지는 문제가 발생한다.
 */
class TripTendencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTripTendencyBinding

    private val viewModel by viewModels<TripTendencyViewModel>{
        viewModelModule.provideTripTendencyViewModelFactory()
    }

    private lateinit var tripTendencyAdapter: TripTendencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trip_tendency)
        binding.vm = viewModel
        binding.activity = this
        tripTendencyAdapter = TripTendencyAdapter(viewModel)

        observeQuestionPosition()
        observeToastEvent()
        observeTravelTendencyQuestions()
        configureQuestionPager()
        configureTab()
    }

    private fun observeTravelTendencyQuestions() {
        viewModel.travelTendencyQuestions.observe(this){
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
            if (it != 0 && it == tripTendencyAdapter.itemCount) {
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

    fun exitTripTendencyTest() {
        TripTendencyTestExitDialog().show(supportFragmentManager, EXIT_NAVIGATE_TAG)
    }

    fun navigateNextPage() {
        if (!viewModel.getLastQuestionSelectedPosition()!!
                .contains(-1) && viewModel.getQuestionPosition() == MAX_QUESTION_INDEX_COUNT
        ) {
            TripTendencyTestResultLoadingDialog().show(
                supportFragmentManager,
                RESULT_LOADING_NAVIGATE_TAG
            )
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