package org.aossie.agoraandroid.displayelections;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import org.aossie.agoraandroid.displayelections.fragments.ActiveElectionsFragment;
import org.aossie.agoraandroid.displayelections.fragments.FinishedElectionsFragment;
import org.aossie.agoraandroid.displayelections.fragments.PendingElectionsFragment;
import org.aossie.agoraandroid.displayelections.fragments.TotalElectionsFragment;
import org.aossie.agoraandroid.home.HomeFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

  private static final int TAB_COUNT = 4;

  public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
    super(fm, behavior);
  }

  @NonNull @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new TotalElectionsFragment();
      case 1:
        return new ActiveElectionsFragment();
      case 2:
        return new PendingElectionsFragment();
      case 3:
        return new FinishedElectionsFragment();
    }
    return null;
  }

  @Override public int getCount() {
    return TAB_COUNT;
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch(position){
      case 0:
        return "Total";
      case 1:
        return "Active";
      case 2:
        return "Pending";
      case 3:
        return "Finished";
    }
    return null;
  }
}
