package kr.co.dooribon.ui.existingtrip.schedule.dialog

import android.app.Dialog
import android.content.Context
import kr.co.dooribon.R

class DeleteQuestionDialog(context: Context) {
    private val dlg = Dialog(context)
    fun start() {
        dlg.setContentView(R.layout.dialog_delete_question)
        dlg.show()
    }
}