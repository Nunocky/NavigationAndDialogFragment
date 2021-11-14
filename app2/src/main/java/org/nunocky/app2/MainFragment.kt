package org.nunocky.app2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.ResultReceiver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.nunocky.app2.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.button.setOnClickListener {
            showDialog()
        }

        return binding.root
    }

    private fun showDialog() {
        val resultReceiver = object : ResultReceiver(Handler(Looper.getMainLooper())) {
            override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                when (resultCode) {
                    0 -> {
                        Toast.makeText(requireActivity(), "YES", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(requireActivity(), "NO", Toast.LENGTH_SHORT).show()
                    }
                    else -> throw IllegalArgumentException("unsupported resultCode")
                }
            }
        }

        val action = YesNoDialogFragmentDirections.actionGlobalYesNoDialogFragment(resultReceiver)
        findNavController().navigate(action)
    }
}