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

@Entity(tableName = "sinif")
public class Sinif implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "sinif_id")
  @SerializedName("sinif_id")
  @Expose
  private String sinifId;

  @ColumnInfo(name = "sinif_adi")
  @SerializedName("sinif_adi")
  @Expose
  private String sinifAdi;

  @ColumnInfo(name = "sinif_mevcut")
  @SerializedName("sinif_mevcut")
  @Expose
  private String sinifMevcut;

  @SerializedName("class")
  private List<Sinif> results;

  protected Sinif(Parcel in) {
    sinifId = in.readString();
    sinifAdi = in.readString();
    sinifMevcut = in.readString();
    results = in.createTypedArrayList(Sinif.CREATOR);
  }

  public static final Creator<Sinif> CREATOR =
      new Creator<Sinif>() {
        @Override
        public Sinif createFromParcel(Parcel in) {
          return new Sinif(in);
        }

        @Override
        public Sinif[] newArray(int size) {
          return new Sinif[size];
        }
      };

  public String getSinifId() {
    return sinifId;
  }

  public Sinif(String sinifAdi, String sinifMevcut) {
    this.sinifAdi = sinifAdi;
    this.sinifMevcut = sinifMevcut;
  }

  public Sinif(String sinifID, String sinifAdi, String sinifMevcut) {
    this.sinifId = sinifID;
    this.sinifAdi = sinifAdi;
    this.sinifMevcut = sinifMevcut;
  }

  public String getSinifAdi() {
    return sinifAdi;
  }

  public void setSinifAdi(String sinifAdi) {
    this.sinifAdi = sinifAdi;
  }

  public String getSinifMevcut() {
    return sinifMevcut;
  }

  public void setSinifMevcut(String sinifMevcut) {
    this.sinifMevcut = sinifMevcut;
  }

  public List<Sinif> getResults() {
    return results;
  }

  @Override
  public String toString() {
    return sinifAdi;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(sinifId);
    dest.writeString(sinifAdi);
    dest.writeString(sinifMevcut);
    dest.writeTypedList(results);
  }
}
