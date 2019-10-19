package com.volkangurbuz.stajtakip.Requests.Responses;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volkangurbuz.stajtakip.Models.Student;

import java.util.List;

public class StudentResponse {

  @SerializedName("student")
  @Expose
  private Student student;

  @SerializedName("students")
  @Expose
  private List<Student> studentList;

  public List<Student> getStudentList() {
    return studentList;
  }

  @Nullable
  public Student getStudent() {

    return student;
  }

  @Override
  public String toString() {
    return "StudentResponse{" + "student=" + student + '}';
  }
}
