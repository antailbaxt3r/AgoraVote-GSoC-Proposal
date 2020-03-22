package org.aossie.agoraandroid.profile;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import net.steamcrafted.loadtoast.LoadToast;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.remote.APIService;
import org.aossie.agoraandroid.remote.RetrofitClient;
import org.aossie.agoraandroid.utilities.SharedPrefs;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

  ImageView confirmButton;
  EditText newPassword, confirmPassword;
  SharedPrefs sharedPrefs;
  LoadToast loadToast;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_change_password);
    Toolbar toolbar  = findViewById(R.id.toolbar);
    if(toolbar != null){
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          onBackPressed();
        }
      });
    }
    attachID();

    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String newPass = newPassword.getText().toString().trim();
        String confirmPass = confirmPassword.getText().toString().trim();
        if (newPass.equals(confirmPass)) {
          if (newPass.equals(sharedPrefs.getPass())) {
            Toast.makeText(ChangePasswordActivity.this, "New Password should not be same as old one", Toast.LENGTH_SHORT).show();
            confirmPassword.setText("");
          } else
            doChangePasswordRequest(confirmPass, sharedPrefs.getToken());
        }
        else {
          Toast.makeText(ChangePasswordActivity.this, "Password Does Not Matches", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  private void doChangePasswordRequest() {
  }

  private void attachID() {
    confirmButton = findViewById(R.id.confirm_button);
    newPassword = findViewById(R.id.new_pass);
    confirmPassword = findViewById(R.id.confirm_pass);
    sharedPrefs = new SharedPrefs(this);
  }

  private void doChangePasswordRequest(String password, String token) {
    loadToast = new LoadToast(this);
    loadToast.setText("Changing Password");
    loadToast.show();
    final JSONObject jsonObject = new JSONObject();
    try {

      jsonObject.put("password", password);

    } catch (JSONException e) {
      e.printStackTrace();
    }
    APIService apiService = RetrofitClient.getAPIService();
    Call<String> changePassResponse = apiService.changePassword(jsonObject.toString(), token);
    changePassResponse.enqueue(new Callback<String>() {
      @Override
      public void onResponse(Call<String> call, Response<String> response) {
        if (response.message().equals("OK")) {
          loadToast.success();
          Toast.makeText(ChangePasswordActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
        } else {
          Log.d("TAG", "onResponse:" + response.body());
          loadToast.error();
          Toast.makeText(ChangePasswordActivity.this, "Wrong User Name or Password", Toast.LENGTH_SHORT).show();
        }
      }

      @Override
      public void onFailure(Call<String> call, Throwable t) {
        loadToast.error();
        Toast.makeText(ChangePasswordActivity.this, "Something went wrong please try again", Toast.LENGTH_SHORT).show();

      }
    });

  }
}
