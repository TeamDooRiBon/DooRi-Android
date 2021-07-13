package kr.co.dooribon.ui.newtrip.add.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import kr.co.dooribon.domain.entity.PickDatePair

class DatePickerActivityContract : ActivityResultContract<Intent, PickDatePair>() {
    // 이동하고자 하는 화면의 intent를 찾거나 혹은 만들어 낸다.
    // 저의 방법의 경우 launch메소드에서 intent를 인자로 넘겨주기 때문에 input으로 넘어온 친구를 그냥 반환했습니다.
    override fun createIntent(context: Context, input: Intent?): Intent = input!!

    // 이동하고 난 다음에 result를 받아내서 어떻게 처리할지에 대한 로직입니다.
    // 주로 parcelize로 넘어온 데이터를 받아옵니다.
    override fun parseResult(resultCode: Int, intent: Intent?): PickDatePair? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getParcelableExtra("datePair")
            else -> null
        }
    }
}