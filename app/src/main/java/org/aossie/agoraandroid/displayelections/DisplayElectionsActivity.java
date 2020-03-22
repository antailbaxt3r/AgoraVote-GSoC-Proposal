package org.aossie.agoraandroid.displayelections;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import org.aossie.agoraandroid.R;

public class DisplayElectionsActivity extends AppCompatActivity {

  ViewPager viewPager;
  ViewPagerAdapter adapter;
  TabLayout tabLayout;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_elections);
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

    int position = getIntent().getIntExtra("position", 0);
    setViewPager(position);
  }

  private void setViewPager(int position) {

    viewPager = (ViewPager) findViewById(R.id.pager);
    adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    viewPager.setAdapter(adapter);
    viewPager.setCurrentItem(position);

    tabLayout = (TabLayout) findViewById(R.id.dashboard_tab_layout);
    tabLayout.setupWithViewPager(viewPager);
  }
}
