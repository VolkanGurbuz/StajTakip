package com.volkangurbuz.stajtakip;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.volkangurbuz.stajtakip.Adapter.FragmentAdapter;
import com.volkangurbuz.stajtakip.Fragment.HelpFragment;
import com.volkangurbuz.stajtakip.Fragment.TeacherFragment;

public class TeacherControlActivity extends AppCompatActivity {
  // This is our viewPager
  private ViewPager viewPagerTeacher;
  private MenuItem prevMenuItem;

  private BottomNavigationView bottomNavigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teacher_control);

    // Initializing viewPager
    viewPagerTeacher = findViewById(R.id.pager);

    // Initializing the bottomNavigationView
    bottomNavigationView = findViewById(R.id.bottom_navigation);

    bottomNavigationView.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
              case R.id.action_help:
                viewPagerTeacher.setCurrentItem(1);
                break;
              case R.id.action_student:
                viewPagerTeacher.setCurrentItem(0);
                break;
            }
            return false;
          }
        });

    viewPagerTeacher.addOnPageChangeListener(
        new ViewPager.OnPageChangeListener() {
          @Override
          public void onPageScrolled(
              int position, float positionOffset, int positionOffsetPixels) {}

          @Override
          public void onPageSelected(int position) {
            if (prevMenuItem != null) {
              prevMenuItem.setChecked(false);
            } else {
              bottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            bottomNavigationView.getMenu().getItem(position).setChecked(true);
            prevMenuItem = bottomNavigationView.getMenu().getItem(position);
          }

          @Override
          public void onPageScrollStateChanged(int state) {}
        });

    setupViewPager(viewPagerTeacher);
  }

  private void setupViewPager(ViewPager viewPager) {
    FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
    adapter.addFragment(TeacherFragment.getInstance());
    adapter.addFragment(HelpFragment.getInstance());
    viewPager.setAdapter(adapter);
  }
}
