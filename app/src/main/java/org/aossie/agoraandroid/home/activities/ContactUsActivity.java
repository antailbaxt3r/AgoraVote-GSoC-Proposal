package org.aossie.agoraandroid.home.activities;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import org.aossie.agoraandroid.R;

public class ContactUsActivity extends AppCompatActivity {

  Toolbar toolbar;
  Button gitter, gitlab;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_us);
    attachID();
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

    gitlab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://gitlab.com/aossie")));
      }
    });
    gitter.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://gitter.im/aossie/home")));
      }
    });
  }

  private void attachID() {
    toolbar  = findViewById(R.id.toolbar);
    gitter = findViewById(R.id.button_gitter);
    gitlab = findViewById(R.id.button_gitlab);
  }
}
