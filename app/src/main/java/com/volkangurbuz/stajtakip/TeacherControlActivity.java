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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teacher_control);
  }
}
