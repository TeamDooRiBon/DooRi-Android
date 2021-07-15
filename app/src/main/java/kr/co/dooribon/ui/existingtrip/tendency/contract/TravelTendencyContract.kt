package kr.co.dooribon.ui.existingtrip.tendency.contract

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import kr.co.dooribon.api.remote.StoreTravelTendencyDTO

class TravelTendencyContract : ActivityResultContract<Intent, StoreTravelTendencyDTO>() {
    override fun createIntent(context: Context, input: Intent?): Intent = input!!

    override fun parseResult(resultCode: Int, intent: Intent?): StoreTravelTendencyDTO? {
        return when(resultCode){
            Activity.RESULT_OK -> intent?.getParcelableExtra("travelTendencyResult")
            else -> null
        }
    }
}