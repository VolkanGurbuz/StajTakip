package com.volkangurbuz.stajtakip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class AdminMenuActivity extends AppCompatActivity implements View.OnClickListener {
  private CardView cvStudent, cvTeacher, cvSchool, cvHelp;
  private Toolbar toolbar;
  public static String MUDUR_ID;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_menu);

    toolbar = findViewById(R.id.toolbar);
    toolbar.setTitleTextColor(getColor(R.color.colorBackground));

    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Stajyerim Benim");
    getSupportActionBar().setSubtitle("Hosgeldiniz");
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    cvTeacher = (CardView) findViewById(R.id.cardview_teacher);
    cvSchool = (CardView) findViewById(R.id.cardview_class);
    cvHelp = (CardView) findViewById(R.id.cardview_help);

    cvTeacher.setOnClickListener(this);
    cvSchool.setOnClickListener(this);
    cvHelp.setOnClickListener(this);

    SharedPreferences preferences = getSharedPreferences(LoginActivity.PREF_LOGIN, MODE_PRIVATE);
    MUDUR_ID = preferences.getString(LoginActivity.MUDUR_ID, "");
  }

  @Override
  public void onClick(View v) {
    Intent i;

    switch (v.getId()) {
      case R.id.cardview_teacher:
        i = new Intent(this, TeacherControlActivity.class);
        startActivity(i);
        break;
      case R.id.cardview_class:
        i = new Intent(this, StajOgrenciActivity.class);
        startActivity(i);
        break;
      case R.id.cardview_help:
        /* i = new Intent(this, PackDocActivity2.class);
        startActivity(i);*/
        Toast.makeText(this, "Lutfen arayiniz.", Toast.LENGTH_SHORT).show();
        break;
      default:
        break;
    }
  }
}
