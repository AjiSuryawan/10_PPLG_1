package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginView extends AppCompatActivity {
    // deklarasi
    EditText txtEmail;
    EditText txtPassword;
    Button btnLogin;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();

        // disambungkan
        txtEmail = (EditText) findViewById(R.id.txtemail);
        txtPassword = (EditText) findViewById(R.id.txtpassword);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtEmail.getText().toString().equals("admin")
                        && txtPassword.getText().toString().equals("12345")){
                    editor.putString("username", txtEmail.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(LoginView.this,MainMenu.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}