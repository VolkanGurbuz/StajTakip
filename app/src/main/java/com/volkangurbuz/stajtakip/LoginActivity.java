package com.volkangurbuz.stajtakip;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.volkangurbuz.stajtakip.Util.Constants;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
  private EditText editTextUserName, userPassword;
  private Button loginButton;
  public static final String PREF_LOGIN = "LOGIN_PREF";
  public static final String USER_NAME = "USER_NAME";
  public static final String USER_PASSWORD = "PASSWORD";
  private SharedPreferences sharedPreferences;
  public static final String TAG = "LoginActivity";
  private String loginName, loginPassword;
  public static final String MUDUR_ID = "MUDUR_ID";
  public static final String OGRETMEN_ID = "OGRETMEN_ID";
  private ProgressDialog progressDialog;

  // boolean variable to check user is logged in or not
  // initially it is false
  private boolean loggedIn = false;

  private RequestQueue requestQueue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    requestQueue = Volley.newRequestQueue(LoginActivity.this);

    progressDialog = new ProgressDialog(LoginActivity.this);
    editTextUserName = findViewById(R.id.userName);
    userPassword = findViewById(R.id.userPassword);

    loginButton = findViewById(R.id.loginButton);

    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    // set existing value
    editTextUserName.setText(sharedPreferences.getString(USER_NAME, ""));
    userPassword.setText(sharedPreferences.getString(USER_PASSWORD, ""));

    loginButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            loginName = editTextUserName.getText().toString().trim();
            loginPassword = userPassword.getText().toString().trim();
            if (TextUtils.isEmpty(loginName)) {

              Toast.makeText(
                      LoginActivity.this, "lutfen gerekli alanlari doldurunuz", Toast.LENGTH_SHORT)
                  .show();

            } else {
              userLogin();
            }
          }
        });
  }

  private void userLogin() {
    try {
      // Showing progress dialog at user registration time.
      progressDialog.setMessage("Lutfen bekleyiniz.");
      progressDialog.show();

      // Creating string request with post method to login.
      StringRequest loginRequest =
          new StringRequest(
              Request.Method.POST,
              Constants.LOGIN_URL,
              new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                  Log.d("response", response);

                  String userID = response.trim().replaceAll("[^0-9]+", "");
                  String userType = response.trim().replaceAll("\\d", "");
                  if (userType.equalsIgnoreCase("mudur")) {
                    SharedPreferences.Editor editor =
                        getSharedPreferences(PREF_LOGIN, MODE_PRIVATE).edit();
                    editor.putString(USER_NAME, loginName);
                    editor.putString(USER_PASSWORD, loginPassword);
                    editor.putString(MUDUR_ID, userID);
                    editor.commit();

                    goToActivityMudur();

                  } else if (userType.equalsIgnoreCase("ogretmen")) {
                    SharedPreferences.Editor editor =
                        getSharedPreferences(PREF_LOGIN, MODE_PRIVATE).edit();
                    editor.putString(USER_NAME, loginName);
                    editor.putString(USER_PASSWORD, loginPassword);
                    editor.putString(OGRETMEN_ID, userID);
                    editor.commit();

                    goToActivityLOgretmen();
                  } else {
                    Toast.makeText(
                            LoginActivity.this,
                            "Giris bilgileriniz yanlis, lutfen tekrar deneyiniz",
                            Toast.LENGTH_SHORT)
                        .show();
                  }
                  progressDialog.dismiss();
                }
              },
              new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                  // Hiding the progress dialog after all task complete.
                  progressDialog.dismiss();

                  // Showing error message if something goes wrong.
                  Toast.makeText(
                          LoginActivity.this,
                          "Giris Bilgileriniz Yanlis" + error.getMessage(),
                          Toast.LENGTH_LONG)
                      .show();
                }
              }) {
            @Override
            protected Map<String, String> getParams() {

              // Creating Map String Params.
              Map<String, String> params = new HashMap<>();

              // Adding All values to Params.
              // The firs argument should be same sa your MySQL database table columns.
              params.put("mail", loginName);
              params.put("password", loginPassword);

              return params;
            }
          };

      // Creating RequestQueue.
      // RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

      // Adding the StringRequest object into requestQueue.
      requestQueue.add(loginRequest);

    } catch (Exception e) {
      Log.e(TAG, " Failure ", e);
    }
  }

  private void goToActivityMudur() {
    Intent loginIntent = new Intent(this, AdminMenuActivity.class);
    startActivity(loginIntent);
  }

  public void goToActivityLOgretmen() {
    Intent loginIntent = new Intent(this, TeacherMainActivity.class);
    // loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(loginIntent);
  }
}
