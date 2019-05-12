package com.example.lovermoneyptit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lovermoneyptit.api.APIUtils;
import com.example.lovermoneyptit.api.MoneyService;
import com.example.lovermoneyptit.models.Deal;
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.User;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.splash.Splash;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private CheckBox btnSaveAccount;
    private Button btnLogin;
    private Button btnReg;
    private WalletRepo walletRepo;
    private SharedPreferences preferences;
    private ProgressBar progressBar;

    private MoneyService moneyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnSaveAccount = findViewById(R.id.btnSaveAccount);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg=findViewById(R.id.btnReg);
        progressBar = findViewById(R.id.progressBar);

        walletRepo = new WalletRepo(getApplicationContext());
//        walletRepo.addUser();
        preferences = getSharedPreferences("user", MODE_PRIVATE);

        progressBar.setVisibility(View.GONE);

        txtUsername.setText(preferences.getString("username", ""));
        txtPassword.setText(preferences.getString("password", ""));

        moneyService = APIUtils.getAPIService();

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

                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setMax(150);
                    int current = progressBar.getProgress();
                    progressBar.setProgress(current + 10);

                    if(progressBar.getMax() == 150){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                    }
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
