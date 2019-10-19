package com.volkangurbuz.stajtakip;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.volkangurbuz.stajtakip.Models.Student;
import com.volkangurbuz.stajtakip.ViewModels.StudentListViewModel;

import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class StajOgrenciActivity extends AppCompatActivity {

  private StudentListViewModel mStudentListViewModel;
  private static final String TAG = "StajOgrenciActivity";
  private FabSpeedDial fabSpeedDialStudent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_staj_ogrenci);

    fabSpeedDialStudent = findViewById(R.id.fab_speed_btn_manager);

    mStudentListViewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);
    getfabSpeedDialMenu();

    subscribeObservers();
  }

  private void subscribeObservers() {

    mStudentListViewModel
        .getStudents()
        .observe(
            this,
            new Observer<List<Student>>() {
              @Override
              public void onChanged(@Nullable List<Student> students) {

                for (Student student : students) {
                  Log.d(TAG, "onChanged: " + student.getStudentName());
                  mStudentListViewModel.setmDidRetrieveStudents(true);
                }
              }
            });

    // return the timed out, with three seconds does pass to get the timeout.
    mStudentListViewModel
        .isStudentRequestTimedOut()
        .observe(
            this,
            new Observer<Boolean>() {
              @Override
              public void onChanged(@Nullable Boolean isTimeOut) {
                if (isTimeOut && !mStudentListViewModel.ismDidRetrieveStudents()) {
                  Log.d(TAG, "onChanged: timed out...");

                  Toast.makeText(StajOgrenciActivity.this, "connection problem", Toast.LENGTH_SHORT)
                      .show();
                }
              }
            });
  }

  public void getStudentsApi() {

    mStudentListViewModel.getStudentsApi();
  }

  private void getfabSpeedDialMenu() {
    fabSpeedDialStudent.setMenuListener(
        new SimpleMenuListenerAdapter() {
          @Override
          public boolean onMenuItemSelected(MenuItem menuItem) {

            switch (menuItem.getItemId()) {
              case R.id.action_call:
                getStudentsApi();
                Toast.makeText(StajOgrenciActivity.this, "hey ozelligi", Toast.LENGTH_SHORT).show();
                break;
              case R.id.action_send:
                // TODO send the data

                Toast.makeText(StajOgrenciActivity.this, "Mesaj ozelligi", Toast.LENGTH_SHORT)
                    .show();

                break;

              case R.id.action_grade:
                Toast.makeText(StajOgrenciActivity.this, "Notlandirma", Toast.LENGTH_SHORT).show();

                break;

              default:
                return false;
            }

            return false;
          }
        });
  }
}
