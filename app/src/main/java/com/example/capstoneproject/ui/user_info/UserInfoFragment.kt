package com.example.capstoneproject.ui.user_info

import android.content.Intent
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
        setupClickListeners()
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

    private fun setupClickListeners() {
        binding.gridLayout.findViewById<View>(com.example.capstoneproject.R.id.gridLayout).apply {
            findViewById<View>(com.example.capstoneproject.R.id.gridLayout).apply {
                findViewById<View>(com.example.capstoneproject.R.id.gridLayout).apply {
                    // Blood Pressure
                    findViewById<View>(com.example.capstoneproject.R.id.blood_pressure_card).setOnClickListener {
                        startActivity(Intent(requireContext(), BloodPressureActivity::class.java))
                    }
                    // Heart Rate
                    findViewById<View>(com.example.capstoneproject.R.id.heart_rate_card).setOnClickListener {
                        startActivity(Intent(requireContext(), HeartRateActivity::class.java))
                    }
                    // Blood Sugar
                    findViewById<View>(com.example.capstoneproject.R.id.blood_sugar_card).setOnClickListener {
                        startActivity(Intent(requireContext(), BloodSugarActivity::class.java))
                    }
                    // Cholesterol
                    findViewById<View>(com.example.capstoneproject.R.id.cholesterol_card).setOnClickListener {
                        startActivity(Intent(requireContext(), CholesterolActivity::class.java))
                    }
                    // Oxygen Saturation
                    findViewById<View>(com.example.capstoneproject.R.id.oxygen_saturation_card).setOnClickListener {
                        startActivity(Intent(requireContext(), OxygenSaturationActivity::class.java))
                    }
                    // Body Temperature
                    findViewById<View>(com.example.capstoneproject.R.id.body_temp_card).setOnClickListener {
                        startActivity(Intent(requireContext(), BodyTempActivity::class.java))
                    }
                    // Sleep Hours
                    findViewById<View>(com.example.capstoneproject.R.id.sleep_hours_card).setOnClickListener {
                        startActivity(Intent(requireContext(), SleepHoursActivity::class.java))
                    }
                    // Physical Activity
                    findViewById<View>(com.example.capstoneproject.R.id.physical_activity_card).setOnClickListener {
                        startActivity(Intent(requireContext(), PhysicalActivityActivity::class.java))
                    }
                }
            }
        }
    }
}