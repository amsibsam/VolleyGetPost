package com.example.rahardyan.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements ClientCallback {
    private EditText etNim, etPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final NetworkService networkService = new NetworkService(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");

        etNim = (EditText) findViewById(R.id.et_nim);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkService.login(etNim.getText().toString(), etPassword.getText().toString(), LoginActivity.this);
                progressDialog.show();
            }
        });
    }


    @Override
    public void onSucceedeed() {
        Toast.makeText(LoginActivity.this, "Berhasil login", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onFailed() {
        Toast.makeText(LoginActivity.this, "gagal login", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }
}
