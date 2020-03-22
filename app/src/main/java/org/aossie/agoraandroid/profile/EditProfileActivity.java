package org.aossie.agoraandroid.profile;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.utilities.SharedPrefs;

public class EditProfileActivity extends AppCompatActivity {

  EditText username, name, about;
  TextView email;
  ImageView confirmButton, backButton;
  SharedPrefs sharedPrefs;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_profile);
    attachId();

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });

    email.setText(sharedPrefs.getEmail());
    confirmButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if(name.getText().toString().isEmpty()){
          Toast.makeText(EditProfileActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
        }else if(username.getText().toString().isEmpty()){
          Toast.makeText(EditProfileActivity.this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
          updateProfile();
        }
      }
    });
  }

  private void updateProfile() {
    //TODO add update funtionality
  }

  private void attachId() {username = findViewById(R.id.profile_username);
    name = findViewById(R.id.profile_name_first);
    about = findViewById(R.id.profile_description);
    email = findViewById( R.id.profile_email);
    backButton = findViewById(R.id.back_button);
    confirmButton = findViewById(R.id.confirm_button);
    sharedPrefs = new SharedPrefs(this);
  }
}
