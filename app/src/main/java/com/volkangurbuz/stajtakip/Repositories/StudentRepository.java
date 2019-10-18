package com.volkangurbuz.stajtakip.Repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.volkangurbuz.stajtakip.Models.Student;
import com.volkangurbuz.stajtakip.Requests.StudentApiClient;

import java.util.List;

public class StudentRepository {

  private static StudentRepository instance;
  private StudentApiClient mStudentApiClient;

  public static StudentRepository getInstance() {
    if (instance == null) {
      instance = new StudentRepository();
    }
    return instance;
  }

  public StudentRepository() {
    mStudentApiClient = StudentApiClient.getInstance();
  }

  public LiveData<List<Student>> getStudents() {

    return mStudentApiClient.getStudents();
  }
}
