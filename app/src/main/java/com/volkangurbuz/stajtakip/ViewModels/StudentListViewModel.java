package com.volkangurbuz.stajtakip.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.volkangurbuz.stajtakip.Models.Student;
import com.volkangurbuz.stajtakip.Repositories.StudentRepository;

import java.util.List;

public class StudentListViewModel extends ViewModel {

  private StudentRepository mStudentRepository;
  private boolean mDidRetrieveStudents;

  public StudentListViewModel() {
    mStudentRepository = StudentRepository.getInstance();
  }

  // starts to get dthe data from reciperepository to viewmodel and then to the acvitvity
  public LiveData<List<Student>> getStudents() {
    return mStudentRepository.getStudents();
  }

  public LiveData<Boolean> isStudentRequestTimedOut() {
    return mStudentRepository.isStudentRequestTimedOut();
  }

  public boolean ismDidRetrieveStudents() {
    return mDidRetrieveStudents;
  }

  public void setmDidRetrieveStudents(boolean mDidRetrieveStudents) {
    this.mDidRetrieveStudents = mDidRetrieveStudents;
  }

  public void getStudentsApi() {

    mStudentRepository.getStudentsApi();
  }
}
