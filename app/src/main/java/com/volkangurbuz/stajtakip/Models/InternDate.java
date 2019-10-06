package com.volkangurbuz.stajtakip.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "interndate")
public class InternDate implements Parcelable {
  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "interndate_id")
  @SerializedName("interndate_id")
  private String internDateID;

  @ColumnInfo(name = "dukkan_id")
  @SerializedName("dukkan_id")
  private String dukkanID;

  @ColumnInfo(name = "interns")
  @SerializedName("interns")
  private List<InternDate> results;

  protected InternDate(Parcel in) {
    internDateID = in.readString();
    dukkanID = in.readString();
    results = in.createTypedArrayList(InternDate.CREATOR);
    internDate = in.readString();
  }

  public static final Creator<InternDate> CREATOR =
      new Creator<InternDate>() {
        @Override
        public InternDate createFromParcel(Parcel in) {
          return new InternDate(in);
        }

        @Override
        public InternDate[] newArray(int size) {
          return new InternDate[size];
        }
      };

  public List<InternDate> getResults() {
    return results;
  }

  public void setResults(List<InternDate> results) {
    this.results = results;
  }

  public InternDate(String internDateID, String dukkanID, String internDate) {
    this.internDateID = internDateID;
    this.dukkanID = dukkanID;
    this.internDate = internDate;
  }

  @SerializedName("intern_date")
  private String internDate;

  public String getInternDateID() {
    return internDateID;
  }

  public String getDukkanID() {
    return dukkanID;
  }

  public String getInternDate() {
    return internDate;
  }

  @Override
  public String toString() {
    return "InternDate{"
        + "internDateID='"
        + internDateID
        + '\''
        + ", dukkanID='"
        + dukkanID
        + '\''
        + ", internDate='"
        + internDate
        + '\''
        + '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(internDateID);
    dest.writeString(dukkanID);
    dest.writeTypedList(results);
    dest.writeString(internDate);
  }
}
