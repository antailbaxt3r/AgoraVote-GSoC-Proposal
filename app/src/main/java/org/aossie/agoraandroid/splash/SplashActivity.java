package org.aossie.agoraandroid.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.home.HomeActivity;
import org.aossie.agoraandroid.homelogin.HomeLoginActivity;
import org.aossie.agoraandroid.utilities.SharedPrefs;

public class SplashActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Fresco.initialize(this);
    final SharedPrefs sharedPrefs = new SharedPrefs(SplashActivity.this);
    // Rotation And Fade Out Animation

    final ImageView floatAgora = findViewById(R.id.main_logo_iv);
    final CardView getStartedButton = findViewById(R.id.get_started_button);

    getStartedButton.setClickable(false);
    getStartedButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(SplashActivity.this, HomeLoginActivity.class));
        finish();
      }
    });

    final Animation floatAnimation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.float_in_up);

    floatAgora.startAnimation(floatAnimation);
    floatAnimation.setAnimationListener(new Animation.AnimationListener() {
      @Override
      public void onAnimationStart(Animation animation) {

      }

      @Override
      public void onAnimationEnd(Animation animation) {

        String userName = sharedPrefs.getUserName();
        String password = sharedPrefs.getPass();
        if (userName == null || password == null) {
          getStartedButton.setClickable(true);
        } else {
          startActivity(new Intent(SplashActivity.this, HomeActivity.class));
          finish();
        }
      }

      @Override
      public void onAnimationRepeat(Animation animation) {

      }
    });
  }
}
