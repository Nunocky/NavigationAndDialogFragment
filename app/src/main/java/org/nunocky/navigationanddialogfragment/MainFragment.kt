package org.nunocky.navigationanddialogfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import org.nunocky.navigationanddialogfragment.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val resultViewModel: DialogResultViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.button.setOnClickListener {
            showDialog()
        }

        resultViewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                YesNoDialogFragment.Result.Yes -> {
                    Toast.makeText(requireActivity(), "YES", Toast.LENGTH_SHORT).show()
                }
                YesNoDialogFragment.Result.No -> {
                    Toast.makeText(requireActivity(), "NO", Toast.LENGTH_SHORT).show()
                }
                else -> {
                }
            }
        }

        return binding.root
    }

    private fun showDialog() {
        findNavController().navigate(R.id.yesNoDialogFragment)
    }
}