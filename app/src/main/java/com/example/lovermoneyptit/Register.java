package com.example.lovermoneyptit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.models.User;
import com.example.lovermoneyptit.repository.WalletRepo;

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvUserName,tvPassword;
    Button btnReg;
    WalletRepo walletRepo;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar=findViewById(R.id.toobar_reg);
        setSupportActionBar(toolbar);
        setTitle("Đăng kí");
        walletRepo=new WalletRepo(getApplicationContext());
        preferences = getSharedPreferences("user", MODE_PRIVATE);
        tvPassword=findViewById(R.id.txtPassword_Reg);
        tvUserName=findViewById(R.id.txtUsername_Reg);
        btnReg=findViewById(R.id.btnRegister);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=tvUserName.getText().toString().trim();
                String pass=tvPassword.getText().toString().trim();
                SharedPreferences.Editor editor = preferences.edit();
            if ("".equals(user) || "".equals(pass)){
                Toast.makeText(Register.this, "Điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            }else{
                editor.putString("username", user);
                editor.putString("password", pass);
                editor.commit();
                Intent intent=new Intent(Register.this,MainActivity.class);
                startActivity(intent);
                finish();
                }
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // handle arrow click here
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
