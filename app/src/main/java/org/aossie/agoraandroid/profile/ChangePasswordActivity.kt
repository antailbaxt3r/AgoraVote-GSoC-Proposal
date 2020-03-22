package org.aossie.agoraandroid.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import net.steamcrafted.loadtoast.LoadToast
import org.aossie.agoraandroid.R.id
import org.aossie.agoraandroid.R.layout
import org.aossie.agoraandroid.R.string
import org.aossie.agoraandroid.databinding.ActivityChangePasswordBinding
import org.aossie.agoraandroid.databinding.ActivityEditProfileBinding
import org.aossie.agoraandroid.profile.ProfileViewModel.ResponseResults
import org.aossie.agoraandroid.profile.ProfileViewModel.ResponseResults.Error
import org.aossie.agoraandroid.profile.ProfileViewModel.ResponseResults.Success
import org.aossie.agoraandroid.remote.RetrofitClient
import org.aossie.agoraandroid.utilities.SharedPrefs
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {

  lateinit var viewModel: ProfileViewModel
  lateinit var loadToast: LoadToast
  lateinit var binding : ActivityChangePasswordBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, layout.activity_change_password)
    binding.lifecycleOwner = this@ChangePasswordActivity
    viewModel = ViewModelProvider(this).get(ProfileViewModel :: class.java)
    loadToast = LoadToast(this)
    binding.viewModel = viewModel

    val toolbar = binding.toolbar
    setSupportActionBar(toolbar)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    supportActionBar!!.setDisplayShowHomeEnabled(true)
    toolbar.setNavigationOnClickListener { onBackPressed() }

    binding.newPass.addTextChangedListener(getTextWatcher(3))
    binding.confirmPass.addTextChangedListener(getTextWatcher(4))

    binding.confirmButton!!.setOnClickListener {
      val newPass = binding.newPass.text.toString()
      val conPass = binding.confirmPass.text.toString()
      if(binding.newPassTil.error == null && binding.confirmPasswordTil.error == null) {
        when {
          newPass.isEmpty() -> binding.newPass.error = getString(string.password_empty_warn)
          conPass.isEmpty() -> binding.confirmPass.error = getString(string.password_empty_warn)
          newPass != conPass -> binding.confirmPass.error = getString(string.password_not_match_warn)
          else -> {
            loadToast.show()
            viewModel.changePassword(binding.newPass.text.toString())
          }
        }
      }
    }

    viewModel.passwordRequestCode.observe(this, Observer {
      handlePassword(it)
    })

  }

  private fun handlePassword(response: ResponseResults) = when(response) {
    is Success -> {
      loadToast.success()
      Toast.makeText(this, getString(string.password_change_success), Toast.LENGTH_SHORT).show()
    }
    is Error -> {
      loadToast.error()
      Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
    }
  }

  private fun getTextWatcher(code : Int): TextWatcher {
    return object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {
        when(code){
          3 -> {
            when {
              s.isNullOrEmpty() -> binding.newPass.error = getString(string.password_empty_warn)
              s.toString() == viewModel.pass -> binding.newPass.error = getString(string.password_same_oldpassword_warn)
              else -> binding.newPass.error = null
            }
          }
          4 -> {
            when {
              s.isNullOrEmpty() -> binding.confirmPass.error = getString(string.password_empty_warn)
              s.toString() != binding.newPassText.text.toString() -> binding.confirmPass.error = getString(string.password_not_match_warn)
              else -> binding.confirmPass.error = null
            }
          }
        }
      }
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
  }

}