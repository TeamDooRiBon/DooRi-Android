package kr.co.dooribon.ui.traveltendencyresult.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kr.co.dooribon.utils.shortToast

/**
 * TODO : Need To Test
 */
class DownloadResultImageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            Intent.ACTION_SEND -> {
                // TODO : if you want it, send Notification
                context.shortToast("Success Upload Image to Gallery")
            }
        }
    }
}