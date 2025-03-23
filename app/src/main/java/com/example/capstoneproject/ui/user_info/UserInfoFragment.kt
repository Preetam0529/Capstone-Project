package com.example.capstoneproject.ui.userinfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capstoneproject.databinding.FragmentUserInfoBinding
import com.example.capstoneproject.ui.user_info.*

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardBloodPressure.setOnClickListener {
            startActivity(Intent(requireContext(), BloodPressureActivity::class.java))
        }

        binding.cardHeartRate.setOnClickListener {
            startActivity(Intent(requireContext(), HeartRateActivity::class.java))
        }

        binding.cardBloodSugar.setOnClickListener {
            startActivity(Intent(requireContext(), BloodSugarActivity::class.java))
        }

        binding.cardCholesterol.setOnClickListener {
            startActivity(Intent(requireContext(), CholesterolActivity::class.java))
        }

        binding.cardOxygenSaturation.setOnClickListener {
            startActivity(Intent(requireContext(), OxygenSaturationActivity::class.java))
        }

        binding.cardBodyTemp.setOnClickListener {
            startActivity(Intent(requireContext(), BodyTempActivity::class.java))
        }

        binding.cardSleepHours.setOnClickListener {
            startActivity(Intent(requireContext(), SleepHoursActivity::class.java))
        }

        binding.cardPhysicalActivity.setOnClickListener {
            startActivity(Intent(requireContext(), PhysicalActivityActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
