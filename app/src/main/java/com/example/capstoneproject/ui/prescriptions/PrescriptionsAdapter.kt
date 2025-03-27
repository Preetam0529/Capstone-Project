package com.example.capstoneproject.ui.prescriptions

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PrescriptionsAdapter(private val viewModel: PrescriptionsViewModel, private val dbHelper: DatabaseHelper) :
    RecyclerView.Adapter<PrescriptionsAdapter.PrescriptionViewHolder>() {

    private var prescriptionList: List<String> = listOf()

    fun submitList(list: List<String>) {
        prescriptionList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_prescription, parent, false)
        return PrescriptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrescriptionViewHolder, position: Int) {
        val uri = prescriptionList[position]
        holder.bind(uri)

        try {
            // Generate unique image name based on timestamp
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val uniqueImageName = "prescription_$timestamp"

            // Save image URI to the database with a unique name
            val values = ContentValues().apply {
                put("prescription_name", uniqueImageName)
                put("prescription_image", uri)
            }
            dbHelper.insertOrUpdateData(values)
        } catch (e: Exception) {
            Log.e("PrescriptionAdapter", "Error saving image URI: ${e.message}")
        }

        // Handle long press for delete
        holder.itemView.setOnLongClickListener {
            showDeleteDialog(holder.itemView.context, uri)
            true
        }

        // Handle click for full-screen view
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FullScreenImageActivity::class.java)
            intent.putExtra("image_uri", uri)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = prescriptionList.size

    private fun showDeleteDialog(context: android.content.Context, uri: String) {
        AlertDialog.Builder(context)
            .setTitle("Delete Prescription")
            .setMessage("Are you sure you want to delete this prescription?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.removePrescription(uri)

            }
            .setNegativeButton("No", null)
            .show()
    }

    class PrescriptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.prescriptionImage)

        fun bind(uri: String) {
            Glide.with(imageView.context)
                .load(uri)
                .into(imageView)
        }
    }
}