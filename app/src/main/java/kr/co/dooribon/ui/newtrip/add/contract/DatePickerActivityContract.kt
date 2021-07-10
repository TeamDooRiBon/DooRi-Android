package kr.co.dooribon.ui.newtrip.add.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import kr.co.dooribon.domain.entity.PickDatePair

class DatePickerActivityContract : ActivityResultContract<Intent, PickDatePair>() {
    override fun createIntent(context: Context, input: Intent): Intent = input

    override fun parseResult(resultCode: Int, intent: Intent?): PickDatePair? {
        return when(resultCode){
            Activity.RESULT_OK -> intent?.getParcelableExtra("datePair")
            else -> null
        }
    }
}