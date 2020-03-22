package org.aossie.agoraandroid.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.navigation.NavigationView;
import org.aossie.agoraandroid.R;
import org.aossie.agoraandroid.home.activities.AboutAgoraActivity;
import org.aossie.agoraandroid.home.activities.ContactUsActivity;
import org.aossie.agoraandroid.home.activities.ReportBugActivity;
import org.aossie.agoraandroid.home.activities.ShareAppActivity;
import org.aossie.agoraandroid.profile.ProfileActivity;
import org.aossie.agoraandroid.utilities.SharedPrefs;

public class HomeActivity extends AppCompatActivity {
  private DrawerLayout drawerLayout;
  private NavigationView navView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    HomeViewModel homeViewModel = new HomeViewModel(getApplication(), this);
    SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
    navView = findViewById(R.id.nav_view);

    View hView = navView.getHeaderView(0);
    TextView nav_user_name = hView.findViewById(R.id.header_name_tv);
    nav_user_name.setText(getString(R.string.welcome, sharedPrefs.getFirstName()));
    drawerLayout = findViewById(R.id.drawer_layout);

    setSupportActionBar(toolbar);
    toolbar.setTitle("");

    final ActionBar actionBar =getSupportActionBar();
    if (actionBar != null)
    {

      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowTitleEnabled(false);
      //drawerOpened = false;
      ActionBarDrawerToggle mDrawerToggle =
          new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer,
              R.string.close_drawer) {

            public void onDrawerClosed(View view) {
              supportInvalidateOptionsMenu();
              //drawerOpened = false;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
              return super.onOptionsItemSelected(item);
            }

            public void onDrawerOpened(View drawerView) {
              supportInvalidateOptionsMenu();
              navView.setNavigationItemSelectedListener(drawerNavOptions);
            }
          };

      mDrawerToggle.setDrawerIndicatorEnabled(false);
      toolbar.setNavigationIcon(R.drawable.menu_enlarged);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          drawerLayout.openDrawer(Gravity.LEFT);
        }
      });
      mDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
      //mDrawerToggle.setDrawerIndicatorEnabled(true);
      //noinspection deprecation
      drawerLayout.setDrawerListener(mDrawerToggle);
      mDrawerToggle.syncState();
    }

    Fragment fragment = new HomeFragment();
    loadFragment(fragment);

  }

  private void loadFragment(Fragment fragment) {
    // load fragment
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.content_container, fragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  private NavigationView.OnNavigationItemSelectedListener drawerNavOptions = new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


      switch (menuItem.getItemId()){

        case R.id.dashboard:
          drawerLayout.closeDrawer(GravityCompat.START);
          break;
        case R.id.profile:
          Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
          startActivity(profileIntent);
          break;
        case R.id.about_agora:
          Intent aboutIntent = new Intent(HomeActivity.this, AboutAgoraActivity.class);
          startActivity(aboutIntent);
          break;
        case R.id.fragment_report_bug:
          Intent bugIntent = new Intent(HomeActivity.this, ReportBugActivity.class);
          startActivity(bugIntent);
          break;
        case R.id.fragment_share_with_others:
          Intent shareIntent = new Intent(HomeActivity.this, ShareAppActivity.class);
          startActivity(shareIntent);
          break;
        case R.id.fragment_contact_us:
          Intent contactUsIntent = new Intent(HomeActivity.this, ContactUsActivity.class);
          startActivity(contactUsIntent);
          break;
      }

      return false;
    }
  };


}
