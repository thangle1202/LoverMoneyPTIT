package com.example.lovermoneyptit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lovermoneyptit.models.User;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.splash.Splash;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private CheckBox btnSaveAccount;
    private Button btnLogin;
    private Button btnReg;
    private WalletRepo walletRepo;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnSaveAccount = findViewById(R.id.btnSaveAccount);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg=findViewById(R.id.btnReg);
        walletRepo = new WalletRepo(getApplicationContext());
//        walletRepo.addUser();
        preferences = getSharedPreferences("user", MODE_PRIVATE);


        txtUsername.setText(preferences.getString("username", ""));
        txtPassword.setText(preferences.getString("password", ""));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                SharedPreferences.Editor editor = preferences.edit();
                User user = login(username, password);
                if (user != null) {
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putInt("userId", user.getId());
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,Register.class);
                startActivity(i);
            }
        });


    }

    private User login(String username, String password) {
        User user = walletRepo.login(username, password);
        return (user != null) ? user : null;
    }

}
