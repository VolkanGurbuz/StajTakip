package com.volkangurbuz.stajtakip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
  private EditText editTextUserName, userPassword;
  private Button loginButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    editTextUserName = findViewById(R.id.userName);
    userPassword = findViewById(R.id.userPassword);

    loginButton = findViewById(R.id.loginButton);

    loginButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            String userName = editTextUserName.getText().toString();

            if (userName.equalsIgnoreCase("admin")) {
              Intent intent = new Intent(getApplicationContext(), AdminMenuActivity.class);
              startActivity(intent);
            } else {
              Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
              startActivity(intent);
            }
          }
        });
  }
}
