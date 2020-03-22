package org.aossie.agoraandroid.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.aossie.agoraandroid.R.layout
import net.steamcrafted.loadtoast.LoadToast
import org.aossie.agoraandroid.R.string
import org.aossie.agoraandroid.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

  lateinit var viewModel: ProfileViewModel
  lateinit var loadToast: LoadToast
  lateinit var binding : ActivityEditProfileBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, layout.activity_edit_profile)
    binding.lifecycleOwner = this@EditProfileActivity
    viewModel = ViewModelProvider(this).get(ProfileViewModel :: class.java)
    loadToast = LoadToast(this)
    binding.viewModel = viewModel

    binding.backButton!!.setOnClickListener { onBackPressed() }

    binding.profileNameFirst.addTextChangedListener(getTextWatcher(1))
    binding.profileNameLast.addTextChangedListener(getTextWatcher(2))

    binding.confirmButton!!.setOnClickListener {
      loadToast.show()
      if(binding.firstNameTil.error == null && binding.lastNameTil.error == null) {
        viewModel.updateUser(
            binding.profileNameFirst.text.toString().trim(),
            binding.profileNameLast.text.toString().trim()
        )
      }
      else loadToast.error()
    }

    viewModel.userUpdateResponse.observe(this, Observer {
      handleUser(it)
    })
  }

  private fun handleUser(response: ProfileViewModel.ResponseResults) = when(response) {
    is ProfileViewModel.ResponseResults.Success -> {
      loadToast.success()
      Toast.makeText(this, getString(string.user_updated), Toast.LENGTH_SHORT).show()
    }
    is ProfileViewModel.ResponseResults.Error -> {
      loadToast.error()
      Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    }
  }

  private fun getTextWatcher(code : Int): TextWatcher {
    return object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        when(code){
          1 -> {
            if(s.isNullOrEmpty()) binding.firstNameTil.error = getString(string.first_name_empty)
            else binding.firstNameTil.error = null
          }
          2 -> {
            if(s.isNullOrEmpty()) binding.lastNameTil.error = getString(string.last_name_empty)
            else binding.lastNameTil.error = null
          }
        }
      }
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
  }
}