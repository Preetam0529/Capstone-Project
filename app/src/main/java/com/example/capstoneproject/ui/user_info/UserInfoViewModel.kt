package com.example.capstoneproject.ui.user_info

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val height = MutableLiveData<String>()
    val weight = MutableLiveData<String>()
    val bp = MutableLiveData<String>()
    val heartRate = MutableLiveData<String>()
    val sugarLevel = MutableLiveData<String>()

    private val _userDataSaved = MutableLiveData<Boolean>()
    val userDataSaved: LiveData<Boolean> get() = _userDataSaved

    fun onSubmit() {
        if (name.value.isNullOrEmpty() || age.value.isNullOrEmpty() ||
            height.value.isNullOrEmpty() || weight.value.isNullOrEmpty() ||
            bp.value.isNullOrEmpty() || heartRate.value.isNullOrEmpty() ||
            sugarLevel.value.isNullOrEmpty()
        ) {
            _userDataSaved.value = false // Show error (handle in UI)
        } else {
            _userDataSaved.value = true // Data saved successfully (handle in UI)
        }
    }
}
