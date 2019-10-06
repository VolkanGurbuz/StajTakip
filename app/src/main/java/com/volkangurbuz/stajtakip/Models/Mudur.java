package com.volkangurbuz.stajtakip.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "mudur")
public class Mudur {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "mudur_id")
    @SerializedName("mudur_id")
    @Expose
    private String mudurId;

    @ColumnInfo(name = "mudur_ad")
    @SerializedName("mudur_ad")
    @Expose
    private String mudurAd;

    @ColumnInfo(name = "mudur_soyad")
    @SerializedName("mudur_soyad")
    @Expose
    private String mudurSoyad;

    @ColumnInfo(name = "okul_adi")
    @SerializedName("okul_adi")
    @Expose
    private String okulAdi;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private String userId;

    @ColumnInfo(name = "add_count")
    @SerializedName("add_count")
    @Expose
    private String addCount;

    public String getMudurId() {
        return mudurId;
    }

    public void setMudurId(String mudurId) {
        this.mudurId = mudurId;
    }

    public String getMudurAd() {
        return mudurAd;
    }

    public void setMudurAd(String mudurAd) {
        this.mudurAd = mudurAd;
    }

    public String getMudurSoyad() {
        return mudurSoyad;
    }

    public void setMudurSoyad(String mudurSoyad) {
        this.mudurSoyad = mudurSoyad;
    }

    public String getOkulAdi() {
        return okulAdi;
    }

    public void setOkulAdi(String okulAdi) {
        this.okulAdi = okulAdi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddCount() {
        return addCount;
    }

    public void setAddCount(String addCount) {
        this.addCount = addCount;
    }
}
