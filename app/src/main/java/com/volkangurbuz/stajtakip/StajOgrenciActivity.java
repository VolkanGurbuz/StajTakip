package com.volkangurbuz.stajtakip;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.volkangurbuz.stajtakip.ViewModels.StudentListViewModel;

public class StajOgrenciActivity extends AppCompatActivity {

    private StudentListViewModel mStudentListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staj_ogrenci);

        mStudentListViewModel = ViewModelProviders.of(this).get(StudentListViewModel.class);

    }



    private void subscribeObservers(){

        mStudentListViewModel


    }


}
