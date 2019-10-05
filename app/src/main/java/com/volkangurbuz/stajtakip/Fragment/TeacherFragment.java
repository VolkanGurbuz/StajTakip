package com.volkangurbuz.stajtakip.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volkangurbuz.stajtakip.R;

/** A simple {@link Fragment} subclass. */
public class TeacherFragment extends Fragment {

  public static TeacherFragment INSTANCE = null;

  public TeacherFragment() {
    // Required empty public constructor
  }

  public static TeacherFragment getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new TeacherFragment();
    }

    return INSTANCE;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_teacher, container, false);
  }
}
