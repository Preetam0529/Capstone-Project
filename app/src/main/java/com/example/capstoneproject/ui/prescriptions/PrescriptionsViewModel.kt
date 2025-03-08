package com.example.capstoneproject.ui.prescriptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrescriptionsViewModel : ViewModel() {
    private val _prescriptions = MutableLiveData<MutableList<String>>(mutableListOf())
    val prescriptions: LiveData<MutableList<String>> = _prescriptions

    // Add prescription
    fun addPrescription(uri: String) {
        val updatedList = _prescriptions.value ?: mutableListOf()
        updatedList.add(uri)
        _prescriptions.value = updatedList
    }

    // Remove prescription
    fun removePrescription(uri: String) {
        val updatedList = _prescriptions.value ?: mutableListOf()
        updatedList.remove(uri)
        _prescriptions.value = updatedList
    }
}


