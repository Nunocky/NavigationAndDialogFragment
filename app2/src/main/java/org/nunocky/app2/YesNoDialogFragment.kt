package org.nunocky.app2

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs

class YesNoDialogFragment : DialogFragment() {

    private val args: YesNoDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireActivity())
            .setTitle("Title")
            .setMessage("Message")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                args.receiver?.send(0, null)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
                args.receiver?.send(1, null)
            }

        return builder.create()
    }
}