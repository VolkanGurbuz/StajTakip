package com.volkangurbuz.stajtakip.Repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.volkangurbuz.stajtakip.Models.Student;

import java.util.List;

public class StudentRepository {

  private static StudentRepository instance;
  private MutableLiveData<List<Student>> mStudents;

  public static StudentRepository getInstance() {
    if (instance == null) {
      instance = new StudentRepository();
    }
    return instance;
  }

  public StudentRepository() {
    mStudents = new MutableLiveData<>();
  }

  public LiveData<List<Student>> getStudents() {
    return mStudents;
  }
}
