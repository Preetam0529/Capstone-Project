package com.example.capstoneproject.ui.profile

import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.databinding.FragmentProfileBinding
import java.io.ByteArrayInputStream

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        databaseHelper = DatabaseHelper(requireContext())

        // Fetch user data from database and display it
        loadUserProfile()

        return root
    }

    private fun loadUserProfile() {
        val db: SQLiteDatabase = databaseHelper.readableDatabase
        val cursor = db.rawQuery("SELECT fullname, profile_image FROM UserHealth WHERE id = 1", null)

        if (cursor.moveToFirst()) {
            // Set Full Name
            val fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullname"))
            binding.tvFullname.text = fullName

            // Set Profile Image
            val imageBlob = cursor.getBlob(cursor.getColumnIndexOrThrow("profile_image"))
            val imageStream = ByteArrayInputStream(imageBlob)
            val bitmap: Bitmap = BitmapFactory.decodeStream(imageStream)
            binding.profilePic.setImageBitmap(bitmap)
        }

        cursor.close()
        db.close()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
