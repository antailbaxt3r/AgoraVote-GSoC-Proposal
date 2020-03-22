package org.aossie.agoraandroid.home.activities;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import org.aossie.agoraandroid.R;

public class ReportBugActivity extends AppCompatActivity {

  Toolbar toolbar;
  Button action;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report_bug);
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

    action.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        reportBug();
      }
    });
  }

  private void reportBug() {
    startActivity(new Intent(Intent.ACTION_VIEW,
        Uri.parse("https://gitlab.com/aossie/agora-android/issues/new")));
  }

  private void attachID() {
    toolbar  = findViewById(R.id.toolbar);
    action = findViewById(R.id.button_action);
  }
}
