package org.nunocky.navigationanddialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class YesNoDialogFragment : DialogFragment() {

    sealed class Result {
        object Init : Result()
        object Yes : Result()
        object No : Result()
    }

    private val resultViewModel: DialogResultViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
            .setTitle("Title")
            .setMessage("Message")
            .setPositiveButton("Yes") { dialog, _ ->
                resultViewModel.result.value = Result.Yes
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                resultViewModel.result.value = Result.No
                dialog.dismiss()
            }

        resultViewModel.result.value = Result.Init
        return builder.create()
    }
}