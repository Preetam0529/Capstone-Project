package com.example.capstoneproject.ui.prescriptions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R

class PrescriptionsFragment : Fragment() {
    private val viewModel: PrescriptionsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PrescriptionsAdapter

    private val PICK_IMAGE = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prescriptions, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewPrescriptions)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = PrescriptionsAdapter(viewModel)
        recyclerView.adapter = adapter

        // Observe ViewModel
        viewModel.prescriptions.observe(viewLifecycleOwner) {
            adapter.submitList(it.toList()) // Update RecyclerView
        }

        view.findViewById<Button>(R.id.btnUploadPrescription).setOnClickListener {
            openGallery()
        }

        return view
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = data?.data
            imageUri?.let {
                viewModel.addPrescription(it.toString())
                Toast.makeText(requireContext(), "Prescription Added", Toast.LENGTH_SHORT).show()
            }
        }
    }
}