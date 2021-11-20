package org.nunocky.app3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.nunocky.app3.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.button.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToYesNoDialogFragment("Button1")
            findNavController().navigate(action)
        }

        binding.button2.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToYesNoDialogFragment("Button2")
            findNavController().navigate(action)
        }

        parentFragmentManager.setFragmentResultListener(
            "Button1",
            viewLifecycleOwner
        ) { requestKey: String, result: Bundle ->
            val retVal = result.getInt("result")
            Toast.makeText(requireActivity(), "Button1 - $retVal", Toast.LENGTH_SHORT).show()
        }

        parentFragmentManager.setFragmentResultListener(
            "Button2",
            viewLifecycleOwner
        ) { requestKey: String, result: Bundle ->
            val retVal = result.getInt("result")
            Toast.makeText(requireActivity(), "Button2 - $retVal", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}