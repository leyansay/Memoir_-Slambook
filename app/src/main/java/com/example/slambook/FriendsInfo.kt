package com.example.slambook

import PersonalInfo
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.slambook.databinding.ActivityFriendsInfoBinding

class FriendsInfo : AppCompatActivity() {
    private lateinit var binding: ActivityFriendsInfoBinding
    private var friend: PersonalInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendsInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the PersonalInfo object from the intent
        friend = intent.getParcelableExtra("SlambookPrefs")

        // Display the details in the UI using the passed PersonalInfo object
        friend?.let {
            binding.name.setText(it.name)
            binding.nickName.setText(it.nickname)
            binding.homeTown.setText(it.hometown)
            binding.age.setText(it.age)
            binding.gender.setText(it.gender)
            binding.color.setText(it.color)
            binding.comfort.setText(it.comfort?.ifEmpty { "Not specified" }) // Fallback if comfort is empty
            binding.food.setText(it.food)
            binding.hobbies.setText(it.hobbies)
            binding.sports.setText(it.sports)
        } ?: run {
            // If personalInfo is null, show an error or a message
            Toast.makeText(this, "No personal information found!", Toast.LENGTH_SHORT).show()

            // Retrieve saved data from SharedPreferences if personalInfo is null
            val sharedPreferences = getSharedPreferences("SlambookPrefs", MODE_PRIVATE)
            binding.name.setText(sharedPreferences.getString("name", "No name"))
            binding.nickName.setText(sharedPreferences.getString("nickName", "No nickname"))
            binding.homeTown.setText(sharedPreferences.getString("homeTown", "No hometown"))
            binding.age.setText(sharedPreferences.getString("age", "No age"))
            binding.gender.setText(sharedPreferences.getString("gender", "No gender"))
            binding.color.setText(sharedPreferences.getString("color", "No color"))
            binding.food.setText(sharedPreferences.getString("food", "No food"))
            binding.comfort.setText(sharedPreferences.getString("comfortFood", "No comfort food"))
            binding.hobbies.setText(sharedPreferences.getString("hobbies", "No hobbies"))
            binding.sports.setText(sharedPreferences.getString("sports", "No sports"))
        }

        // Find the update button
        val updateButton = binding.updateBtn

        // Set OnClickListener for the update button
        updateButton.setOnClickListener {
            saveFriend()



            // Save the updated data to SharedPreferences
         /*   val sharedPreferences = getSharedPreferences("SlambookPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.putString("name", binding.name.text.toString())
            editor.putString("nickName", binding.nickName.text.toString())
            editor.putString("homeTown", binding.homeTown.text.toString())
            editor.putString("age", binding.age.text.toString())
            editor.putString("gender", binding.gender.text.toString())
            editor.putString("color", binding.color.text.toString())
            editor.putString("food", binding.food.text.toString())
            editor.putString("comfortFood", binding.comfort.text.toString())
            editor.putString("hobbies", binding.hobbies.text.toString())
            editor.putString("sports", binding.sports.text.toString())

            // Apply the changes to SharedPreferences
            editor.apply()

            // Show a confirmation message
            Toast.makeText(this, "Information updated successfully!", Toast.LENGTH_SHORT).show()
            finish()

          */
        }
    }

    private fun saveFriend() {
        val updateFriend = PersonalInfo(
            name = binding.name.text.toString(),
            nickname = binding.nickName.text.toString(),
            hometown = binding.homeTown.text.toString(),
            age = binding.age.text.toString(),
            gender = binding.gender.text.toString(),
            color = binding.color.text.toString(),
            food = binding.food.text.toString(),
            comfort = binding.comfort.toString(),
            hobbies = binding.comfort.toString(),
            sports = binding.hobbies.text.toString(),
            dateAdded = ""

        )
        val result = Intent()
        result.putExtra("update_friend", updateFriend)
        setResult(Activity.RESULT_OK, result)
        val toHome = Intent(this, HomeActivity::class.java)
        startActivity(toHome)
        binding.nameHeader.text = updateFriend.name
    }
}
