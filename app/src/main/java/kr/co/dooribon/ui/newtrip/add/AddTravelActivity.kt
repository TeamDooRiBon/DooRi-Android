package kr.co.dooribon.ui.newtrip.add

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.api.remote.CreateTravelReq
import kr.co.dooribon.api.remote.CreateTravelRes
import kr.co.dooribon.api.remote.DefaultTravelImageDTO
import kr.co.dooribon.application.MainApplication.Companion.apiModule
import kr.co.dooribon.databinding.ActivityNewTravelBinding
import kr.co.dooribon.dialog.DooRiBonDialog
import kr.co.dooribon.domain.entity.PickDatePair
import kr.co.dooribon.ui.newtrip.TravelPlanDoneActivity
import kr.co.dooribon.ui.newtrip.adapter.ImageData
import kr.co.dooribon.ui.newtrip.adapter.RecoImgAdapter
import kr.co.dooribon.ui.newtrip.add.contract.DatePickerActivityContract
import kr.co.dooribon.utils.RVItemDeco
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddTravelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewTravelBinding

    //아래 변수들이 모두 true가 돼야 새로운 여행 시작하기 버튼 활성화 된다.
    private var etTravelNameNotEmpty = false // 여행 이름
    private var etTravelPlaceNotEmpty = false // 여행 위치
    private var tvCalendarNotEmpty = false // 여행 날짜
    private var ivChecked = false // 추천 이미지 체크

    private var selectedImgIndex = -1 // 열여섯개 중 클릭한 이미지 인덱스 처리
    private var teamCode = -1 // 서버로부터 수신한 팀 코드

    // ActivityContract
    private val datePickLauncher =
        registerForActivityResult(DatePickerActivityContract()) { result: PickDatePair? ->
            // 데이터가 반환되어서 왔을 때, 어떤 로직을 실행할지를 적어놓습니다.
            binding.apply {
                tvStartDate.text = result?.startDate ?: ""
                tvEndDate.text = result?.endDate ?: ""
            }
            setCalendarData() // 캘린더에서 받은 값 액티비티에 반영
            tvCalendarNotEmpty = true // 여행 날짜 입력 플래그 true로 설정
            enableNewTravelBtn() // 다 체크되었는지 한 번 확인
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_travel)
        window.setSoftInputMode(SOFT_INPUT_ADJUST_NOTHING)

        backBtnClickListener() // 뒤로가기 버튼 클릭 이벤트
        onStartNewTravelBtnClick() // 새로운 여행 시작 버튼 클릭 이벤
        onAddDateBtnClick() // 날짜 추가 버튼 클릭 이벤트
        //resetData(-1) // 수정할 값이 없으므로 -1 대입
        setRecoImg() // 서버로부터 이미지 불러와서 값 입력
        chkEditTextInput() // 여행 이름과 위치 클릭했는지 확인하는 함수
        enableNewTravelBtn() // 다 입력했을 시 버튼 활성화
    }

    private fun onStartNewTravelBtnClick() {
        binding.btStartNewTravel.setOnClickListener {
            sendTravelData()
            val intent = Intent(this, TravelPlanDoneActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /* 서버에 데이터 보내는 함 */
    private fun sendTravelData() {
        apiModule.travelApi.createUserTravel(
            CreateTravelReq(
                binding.etTravelName.text.toString(),
                binding.etTravelPlace.text.toString(),
                binding.tvStartDate.text.toString().replace(".", "-"),
                binding.tvEndDate.text.toString().replace(".", "-"), // .을 -로 변경해서 전
                selectedImgIndex
            )
        ).enqueue(object : Callback<CreateTravelRes> {
            override fun onResponse(
                call: Call<CreateTravelRes>,
                response: Response<CreateTravelRes>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        teamCode = it.data.inviteCode.toInt()
                    }
                }
            }

            override fun onFailure(call: Call<CreateTravelRes>, t: Throwable) {
                t.stackTrace
            }
        })
    }

    private fun onAddDateBtnClick() {
        binding.btAddDate.setOnClickListener {
            val intent = Intent(this, DatePickActivity::class.java)
            if (!binding.tvStartDate.text.isNullOrEmpty() && !binding.tvEndDate.text.isNullOrEmpty()) {
                intent.putExtra(
                    "beforeSelect", PickDatePair(
                        binding.tvStartDate.text.toString(),
                        binding.tvEndDate.text.toString()
                    )
                )
            }
            datePickLauncher.launch(intent)
        }
    }

    private fun setRecoImg() {
        apiModule.travelImageApi.fetchDefaultTravelImage()
            .enqueue(object : Callback<DefaultTravelImageDTO> {
                override fun onResponse(
                    call: Call<DefaultTravelImageDTO>,
                    response: Response<DefaultTravelImageDTO>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { it ->
                            imgAdapter(it.data)
                        }
                    }
                }

                override fun onFailure(call: Call<DefaultTravelImageDTO>, t: Throwable) {
                    Log.e("getImgUrlOnFailure", t.message.toString())
                }
            })
    }

    /* 다음으로 넘어갈 수 있도록 버튼 활성화 */
    private fun enableNewTravelBtn() {
        if (isEverythingChecked()) {
            binding.btStartNewTravel.apply {
                isEnabled = true
                background = ContextCompat.getDrawable(
                    this@AddTravelActivity,
                    R.drawable.bg_new_travel_enabled_btn
                )
                setTextColor(Color.WHITE)
            }
        }
    }

    /* 모든 것이 다 체크되었는지 확인하는 함수 */
    private fun isEverythingChecked() =
        etTravelPlaceNotEmpty && etTravelNameNotEmpty && tvCalendarNotEmpty && ivChecked

    /* 캘린더에서 데이터 주면 뷰에 받아서 뷰에 적용함 */
    private fun setCalendarData() {
        if (chkDateSelected()) {
            binding.btAddDate.apply {
                setBackgroundResource(R.drawable.bg_add_date_gray_btn)
                text = "+ 날짜 수정하기"
            }
            binding.tvStartDate.setBackgroundResource(R.drawable.bg_text_gray_stroke)
            binding.tvEndDate.setBackgroundResource(R.drawable.bg_text_gray_stroke)
        }
    }

    /* 날짜 텍스트가 잘 들어와있는지 확인하는 부분 */
    private fun chkDateSelected() =
        binding.tvStartDate.text.isNotEmpty() && binding.tvEndDate.text.isNotEmpty()

    private fun imgAdapter(imgUrls: List<String>) {
        Log.e("imgUrls", imgUrls.toString())
        val imgAdapter = RecoImgAdapter()
        val imgRV = binding.rvPreparedPhotos
        imgRV.adapter = imgAdapter
        imgRV.addItemDecoration(RVItemDeco(10, 10, 10, 10))
        imgAdapter.setItemList(imgUrls)
        onImageItemClickListener(imgAdapter)
    }

    private fun backBtnClickListener() {
        binding.ivBack.setOnClickListener {
            DooRiBonDialog(
                dooribonDialogSubTitle = "지금까지의 수정 정보는 저장되지 않습니다.",
                dooribonDialogSubTitle2 = "수정을 취소하려면 오른쪽 버튼을 눌러주세요.",
                onOrangeButtonClicked = {
                    finish()
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                },
            ).show(supportFragmentManager, DOO_RI_BON_DIALOG_TAG)
        }
    }

    override fun onBackPressed() {
        setQuestionDialog()
    }

    private fun setQuestionDialog() {
        DooRiBonDialog(
            dooribonDialogSubTitle = "지금까지의 수정 정보는 저장되지 않습니다.",
            dooribonDialogSubTitle2 = "수정을 취소하려면 오른쪽 버튼을 눌러주세요.",
            onOrangeButtonClicked = {
                finish()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            },
        ).show(supportFragmentManager, DOO_RI_BON_DIALOG_TAG)
    }

    companion object {
        private const val DOO_RI_BON_DIALOG_TAG = "DooRiBon"
    }

    /* 여행 이름과 위치를 적었는지 확인하는 함수 */
    private fun chkEditTextInput() {
        binding.apply {
            etTravelName.addTextChangedListener {
                etTravelNameNotEmpty = etTravelName.text.isNotEmpty()
                enableNewTravelBtn() // 다 체크되었는지 한 번 확인
            }
            etTravelPlace.addTextChangedListener {
                etTravelPlaceNotEmpty = etTravelName.text.isNotEmpty()
                enableNewTravelBtn() // 다 체크되었는지 한 번 확인
            }
        }
    }

    /* 대표 사진 클릭 이벤트 리스너 */
    private fun onImageItemClickListener(adapter: RecoImgAdapter) {
        adapter.setItemClickListener(object : RecoImgAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                selectedImgIndex = position
                ivChecked = true
                enableNewTravelBtn() // 다 체크되었는지 한 번 확인
                adapter.notifyItemChanged(adapter.prevClickedImgPos)
                adapter.isRemoveBgBinding = true
                adapter.modifyImgBg(view, false)
                adapter.prevClickedImgPos = position
            }
        })
    }
}