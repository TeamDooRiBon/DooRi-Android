package kr.co.dooribon.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kr.co.dooribon.databinding.DialogDooribonTemplateBinding
import kr.co.dooribon.utils.AutoClearBinding
import kr.co.dooribon.utils.setDooRiBonDialogSize

/**
 * Created by SSong-develop 2021.07.12
 *
 * TODO by SSong-develop , Need to refactor Builder Pattern
 *
 */
class DooRiBonDialog(
    private val dooribonDialogTitle: String = DEFAULT_DIALOG_TITLE,
    private val dooribonDialogSubTitle: String? = DEFAULT_DIALOG_SUB_TITLE,
    private val dooribonDialogSubTitle2: String? = DEFAULT_DIALOG_SUB_TITLE_2,
    private val dooribonDialogOrangeButtonText: String? = DEFAULT_DIALOG_POSITIVE_BUTTON_TEXT,
    private val dooribonDialogGrayButtonText: String? = DEFAULT_DIALOG_NEGATIVE_BUTTON_TEXT,
    val onOrangeButtonClicked: () -> Unit
) : DialogFragment() {

    private var binding: DialogDooribonTemplateBinding by AutoClearBinding<DialogDooribonTemplateBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogDooribonTemplateBinding.inflate(inflater, container, false)
        .also { DialogDooribonTemplateBinding ->
            binding = DialogDooribonTemplateBinding
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.dialog = this
        binding.apply {
            tvExitDescription.text = dooribonDialogTitle
            tvExitSubDescription.text = dooribonDialogSubTitle
            tvExitSubDescription2.text = dooribonDialogSubTitle2
            btnNoExit.text = dooribonDialogOrangeButtonText
            btnExit.text = dooribonDialogGrayButtonText
        }

        setDooRiBonDialogSize()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun positiveButtonClicked() {
        onOrangeButtonClicked.invoke()
        dismiss()
    }

    companion object {
        const val DEFAULT_DIALOG_TITLE = "정말 나가시겠습니까?"
        const val DEFAULT_DIALOG_SUB_TITLE = "지금까지의 수정 정보는 저장되지 않습니다"
        const val DEFAULT_DIALOG_SUB_TITLE_2 = "수정을 취소하려면 오른쪽 버튼을 눌러주세요"
        const val DEFAULT_DIALOG_POSITIVE_BUTTON_TEXT = "계속할게요"
        const val DEFAULT_DIALOG_NEGATIVE_BUTTON_TEXT = "나갈게요"
    }
}