package com.volkangurbuz.stajtakip.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "student")
public class Student implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "ogrenci_id")
  @SerializedName("ogrenci_id")
  private String studentId;

  @ColumnInfo(name = "ogrenci_ad")
  @SerializedName("ogrenci_ad")
  private String studentName;

  @ColumnInfo(name = "ogrenci_soyad")
  @SerializedName("ogrenci_soyad")
  private String studentSurname;

  @ColumnInfo(name = "dukkan_adi")
  @SerializedName("dukkan_adi")
  private String dukkanAdi;

  @ColumnInfo(name = "ogretmen_okul")
  @SerializedName("ogretmen_okul")
  private String ogrenciOkul;

  @ColumnInfo(name = "interndate_id")
  @SerializedName("interndate_id")
  @Expose
  private String interndateId;

  @ColumnInfo(name = "student_enter_time")
  @SerializedName("student_enter_time")
  @Expose
  private String studentEnterTime;

  @ColumnInfo(name = "is_dress")
  @SerializedName("is_dress")
  @Expose
  private int is_dress;

  @ColumnInfo(name = "internsOgrenci")
  @SerializedName("internsOgrenci")
  private List<Student> results;

  @ColumnInfo(name = "student_day_detail")
  @SerializedName("student_day_detail")
  private String day_detail;

  protected Student(Parcel in) {
    studentId = in.readString();
    studentName = in.readString();
    studentSurname = in.readString();
    dukkanAdi = in.readString();
    ogrenciOkul = in.readString();
    interndateId = in.readString();
    studentEnterTime = in.readString();
    is_dress = in.readInt();
    results = in.createTypedArrayList(Student.CREATOR);
    day_detail = in.readString();
    studentRollCall = in.readByte() != 0;
  }

  public static final Creator<Student> CREATOR =
      new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
          return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
          return new Student[size];
        }
      };

  public String getDay_detail() {
    return day_detail;
  }

  public void setDay_detail(String day_detail) {
    this.day_detail = day_detail;
  }

  public int getIs_dress() {
    return is_dress;
  }

  public void setIs_dress(int is_dress) {
    this.is_dress = is_dress;
  }

  public List<Student> getResults() {
    return results;
  }

  public void setResults(List<Student> results) {
    this.results = results;
  }

  public String getInterndateId() {
    return interndateId;
  }

  public void setStudentEnterTime(String studentEnterTime) {
    this.studentEnterTime = studentEnterTime;
  }

  public String getStudentEnterTime() {
    return studentEnterTime;
  }

  public Student(
      String studentId,
      String studentName,
      String studentSurname,
      String interndateId,
      String studentEnterTime,
      String studentDetail,
      int isDress) {
    this.studentId = studentId;
    this.studentName = studentName;
    this.studentSurname = studentSurname;
    this.interndateId = interndateId;
    this.studentEnterTime = studentEnterTime;
    this.day_detail = studentDetail;
    this.is_dress = isDress;
  }

  public Student(
      String studentId,
      String interndateId,
      String studentEnterTime,
      String dayDetail,
      int isDress) {
    this.studentId = studentId;
    this.interndateId = interndateId;
    this.studentEnterTime = studentEnterTime;
    this.day_detail = dayDetail;
    this.is_dress = isDress;
  }

  public Student(String ogrenciId, String interndateId, String studentEnterTime, int is_dress) {
    this.studentId = ogrenciId;
    this.interndateId = interndateId;
    this.studentEnterTime = studentEnterTime;
    this.is_dress = is_dress;
  }

  public Student() {}

  public String getDukkanAdi() {
    return dukkanAdi;
  }

  public String getOgrenciOkul() {
    return ogrenciOkul;
  }

  public String getStudentId() {
    return studentId;
  }

  private boolean studentRollCall;

  public Student(String studentName, String studentSurname, boolean studentRollCall) {
    this.studentName = studentName;
    this.studentSurname = studentSurname;
    this.studentRollCall = studentRollCall;
  }

  public Student(
      String studentName,
      String studentSurname,
      String dukkanAdi,
      String ogrenciOkul,
      boolean studentRollCall) {
    this.studentName = studentName;
    this.studentSurname = studentSurname;
    this.dukkanAdi = dukkanAdi;
    this.ogrenciOkul = ogrenciOkul;
    this.studentRollCall = studentRollCall;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getStudentSurname() {
    return studentSurname;
  }

  public void setStudentSurname(String studentSurname) {
    this.studentSurname = studentSurname;
  }

  public boolean isStudentRollCall() {
    return studentRollCall;
  }

  public void setStudentRollCall(boolean studentRollCall) {
    this.studentRollCall = studentRollCall;
  }

  @Override
  public String toString() {
    return "Student{"
        + "studentId='"
        + studentId
        + '\''
        + ", interndateId='"
        + interndateId
        + '\''
        + ", studentEnterTime='"
        + studentEnterTime
        + '\''
        + ", is_dress='"
        + is_dress
        + '\''
        + " isdailydetail: "
        + day_detail
        + '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(studentId);
    dest.writeString(studentName);
    dest.writeString(studentSurname);
    dest.writeString(dukkanAdi);
    dest.writeString(ogrenciOkul);
    dest.writeString(interndateId);
    dest.writeString(studentEnterTime);
    dest.writeInt(is_dress);
    dest.writeTypedList(results);
    dest.writeString(day_detail);
    dest.writeByte((byte) (studentRollCall ? 1 : 0));
  }
}
