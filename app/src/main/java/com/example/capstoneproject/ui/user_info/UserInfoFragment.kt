package com.example.capstoneproject.ui.user_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.capstoneproject.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {

    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel: UserInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoBinding.inflate(inflater, container, false).apply {
            viewModel = this@UserInfoFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.userDataSaved.observe(viewLifecycleOwner, Observer { isSaved ->
            if (isSaved) {
                Toast.makeText(requireContext(), "User Info Saved!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        })
    }
}