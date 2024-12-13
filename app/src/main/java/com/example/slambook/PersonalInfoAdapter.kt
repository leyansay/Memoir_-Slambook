package com.example.slambook

import PersonalInfo
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slambook.databinding.ListNameBinding

class PersonalInfoAdapter(
    private val personalInfoList: List<PersonalInfo>,
    private val context: Context
) : RecyclerView.Adapter<PersonalInfoAdapter.PersonalInfoViewHolder>() {

    inner class PersonalInfoViewHolder(val binding: ListNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            // Set up click listener on the item view
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // Get the selected item
                    val personalInfo = personalInfoList[position]
                    // Start the activity with the clicked item
                    navigateToDetails(personalInfo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalInfoViewHolder {
        val binding = ListNameBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonalInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonalInfoViewHolder, position: Int) {
        val personalInfo = personalInfoList[position]

        // Populate the views with data
        with(holder.binding) {
            itemName.text = personalInfo.name
            itemDate.text = personalInfo.dateAdded
        }
    }

    override fun getItemCount(): Int = personalInfoList.size

    private fun navigateToDetails(personalInfo: PersonalInfo) {
        val intent = Intent(context, FriendsInfo::class.java).apply {
            putExtra("personal_info", personalInfo) // Pass the Parcelable object
        }
        context.startActivity(intent)
    }
}

