package com.volkangurbuz.stajtakip.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "teacher")
public class TeacherModel implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "ogretmen_id")
  @SerializedName("ogretmen_id")
  @Expose
  private int teacherID;

  @ColumnInfo(name = "user_id")
  @SerializedName("user_id")
  @Expose
  private int teacherUserID;

  @ColumnInfo(name = "ogretmen_adi")
  @SerializedName("ogretmen_adi")
  @Expose
  private String name;

  @ColumnInfo(name = "ogretmen_soyad")
  @SerializedName("ogretmen_soyad")
  @Expose
  private String surname;

  @ColumnInfo(name = "classes")
  private List<Sinif> classes;

  // @TypeConverters(Converters.class)
  @SerializedName("ogretmenler")
  private List<TeacherModel> results;

  public TeacherModel(int teacherID, int teacherUserID, String name, String surname) {
    this.teacherID = teacherID;
    this.teacherUserID = teacherUserID;
    this.name = name;
    this.surname = surname;
  }

  protected TeacherModel(Parcel in) {
    teacherID = in.readInt();
    teacherUserID = in.readInt();
    name = in.readString();
    surname = in.readString();
    classes = in.createTypedArrayList(Sinif.CREATOR);
    results = in.createTypedArrayList(TeacherModel.CREATOR);
  }

  public static final Creator<TeacherModel> CREATOR =
      new Creator<TeacherModel>() {
        @Override
        public TeacherModel createFromParcel(Parcel in) {
          return new TeacherModel(in);
        }

        @Override
        public TeacherModel[] newArray(int size) {
          return new TeacherModel[size];
        }
      };

  public int getTeacherID() {
    return teacherID;
  }

  public List<TeacherModel> getResults() {
    return results;
  }

  public void setTeacherID(int teacherID) {
    this.teacherID = teacherID;
  }

  public TeacherModel(String name, String surname, List<Sinif> classes) {
    this.name = name;
    this.surname = surname;
    this.classes = classes;
  }

  public TeacherModel(int teacherUserID, String name, String surname) {
    this.teacherUserID = teacherUserID;
    this.name = name;
    this.surname = surname;
  }

  public TeacherModel(List<TeacherModel> teachers) {

    results = teachers;
  }

  public int getTeacherUserID() {
    return teacherUserID;
  }

  public void setTeacherUserID(int teacherUserID) {
    this.teacherUserID = teacherUserID;
  }

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public List<Sinif> getClasses() {
    return classes;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void setClasses(List<Sinif> classes) {
    this.classes = classes;
  }

  @Override
  public String toString() {
    return name + " " + surname;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(teacherID);
    dest.writeInt(teacherUserID);
    dest.writeString(name);
    dest.writeString(surname);
    dest.writeTypedList(classes);
    dest.writeTypedList(results);
  }
}
