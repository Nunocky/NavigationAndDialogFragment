package org.nunocky.app3

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs

class YesNoDialogFragment : DialogFragment() {
    private val args : YesNoDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
            .setTitle(args.requestKey)
            .setMessage("Message")
            .setPositiveButton("Yes") { dialog, _ ->
                setFragmentResult(args.requestKey, bundleOf("result" to RESULT_OK))
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                setFragmentResult(args.requestKey, bundleOf("result" to RESULT_CANCELED))
                dialog.dismiss()
            }

        return builder.create()
    }
}