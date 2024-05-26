package com.jhostinluna.heroes.presentation.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.jhostinluna.heroes.R

class MessageDialog constructor(
    var message: String,
): DialogFragment() {
    var listener: (id: Int) -> Unit = {id: Int ->  }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {fragmentActivity->
            val builder = AlertDialog.Builder(fragmentActivity)
            builder.setMessage(message)
                .setPositiveButton(R.string.accept){ dialogInterface, id->
                    listener(id)
                }
                .setNegativeButton(R.string.cancel) { dialogInterface, id->
                    listener(id)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity can not be null")

    }
}