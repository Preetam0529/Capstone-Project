package com.example.capstoneproject.ui.prescriptions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.R

class PrescriptionsAdapter(private val viewModel: PrescriptionsViewModel) :
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

        holder.itemView.setOnLongClickListener {
            viewModel.removePrescription(uri)
            true
        }
    }

    override fun getItemCount(): Int = prescriptionList.size

    class PrescriptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.prescriptionImage)

        fun bind(uri: String) {
            Glide.with(imageView.context)
                .load(uri)
                .into(imageView)
        }
    }
}