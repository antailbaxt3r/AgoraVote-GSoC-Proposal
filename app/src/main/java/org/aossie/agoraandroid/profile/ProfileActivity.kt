package org.aossie.agoraandroid.profile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile.*
import org.aossie.agoraandroid.R.id
import org.aossie.agoraandroid.R.layout
import org.aossie.agoraandroid.utilities.SharedPrefs

class ProfileActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_profile)
    var sharedPrefs = SharedPrefs(applicationContext)

    back_button!!.setOnClickListener { onBackPressed() }
    val authToken = sharedPrefs!!.token
    profile_username!!.text = sharedPrefs!!.userName
    profile_name!!.text = sharedPrefs!!.firstName + " " + sharedPrefs!!.lastName
    profile_description!!.text = "Short description"
    profile_email!!.text = sharedPrefs!!.email
    change_password_button!!.setOnClickListener {
      val changePasswordIntent =
        Intent(this@ProfileActivity, ChangePasswordActivity::class.java)
      startActivity(changePasswordIntent)
    }
    edit_profile_button!!.setOnClickListener {
      val editIntent = Intent(this@ProfileActivity, EditProfileActivity::class.java)
      startActivity(editIntent)
    }
  }
}