package kr.co.dooribon.ui.newtrip.join.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kr.co.dooribon.ui.newtrip.join.viewmodel.ParticipateGroupViewModel

fun EditText.initializeCode1Listener(viewModel: ParticipateGroupViewModel) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.equals("") == false) {
                viewModel.updateCode1Text(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}

    })
}

fun EditText.initializeCode2Listener(viewModel: ParticipateGroupViewModel) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.equals("") == false) {
                viewModel.updateCode2Text(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.initializeCode3Listener(viewModel: ParticipateGroupViewModel) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.equals("") == false) {
                viewModel.updateCode3Text(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.initializeCode4Listener(viewModel: ParticipateGroupViewModel) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.equals("") == false) {
                viewModel.updateCode4Text(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.initializeCode5Listener(viewModel: ParticipateGroupViewModel) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.equals("") == false) {
                viewModel.updateCode5Text(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun EditText.initializeCode6Listener(viewModel: ParticipateGroupViewModel) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s?.equals("") == false) {
                viewModel.updateCode6Text(s.toString())
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}