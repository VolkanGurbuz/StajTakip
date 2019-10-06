package com.volkangurbuz.stajtakip.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.volkangurbuz.stajtakip.Models.Student;

import java.util.List;

public class StudentListViewModel extends ViewModel {

  private MutableLiveData<List<Student>> mStudent = new MutableLiveData<>();

  public StudentListViewModel() {}

  public LiveData<List<Student>> getStudents() {
    return mStudent;
  }
}
