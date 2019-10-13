package com.volkangurbuz.stajtakip.Requests.Responses;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.volkangurbuz.stajtakip.Models.Student;

public class StudentResponse {

  @SerializedName("student")
  @Expose
  private Student student;

  @Nullable
  public Student getStudent() {

    return student;
  }

  @Override
  public String toString() {
    return "StudentResponse{" + "student=" + student + '}';
  }
}
