package org.aossie.agoraandroid.home.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import org.aossie.agoraandroid.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ShareAppActivity extends AppCompatActivity {

  Toolbar toolbar;
  Button action;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_share_app);
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
        shareApp();
      }
    });
  }

  private void shareApp() {
    Intent shareIntent = new Intent(Intent.ACTION_SEND);

    //Get the app link in the Play Store
    final String appPackageName = getApplicationContext().getPackageName();
    String strAppLink;
    try {
      strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
    } catch (android.content.ActivityNotFoundException activityNotFound) {
      strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
    }

    // This is the sharing part
    shareIntent.setType("text/link");
    String shareBody =
        "Hey! Download Agora Vote application for Free and create elections right now" +
            "\n" + "" + strAppLink;
    String shareSub = "APP NAME/TITLE";
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
    startActivity(Intent.createChooser(shareIntent, "Share Agora Vote Using"));
  }

  private void attachID() {
    toolbar  = findViewById(R.id.toolbar);
    action = findViewById(R.id.button_action);
  }
}
