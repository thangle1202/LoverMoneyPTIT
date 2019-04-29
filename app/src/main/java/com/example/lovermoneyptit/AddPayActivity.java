package com.example.lovermoneyptit;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.models.Debt;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import org.w3c.dom.Text;

public class AddPayActivity extends AppCompatActivity {

    TextView tvDate,tvWallet;
    EditText edtAmount,edtPerSon,edtDes;
    Button btnSave,btnChooseDate,btnChooseWallet;
    Wallet wallet;
    Debt debt;
    WalletRepo walletRepo;
    public void init(){
        tvDate=findViewById(R.id.tv_datepay);
        tvWallet=findViewById(R.id.tv_choose_wallet_pay);
        btnSave=findViewById(R.id.btn_tra);
        btnChooseDate=findViewById(R.id.btn_choosedatepay);
        btnChooseWallet=findViewById(R.id.btn_choose_wall_pay);
        edtAmount=findViewById(R.id.edt_sotien_tra);
        edtDes=findViewById(R.id.edt_ghichu_tra);
        edtPerSon=findViewById(R.id.edt_nguoi_vay);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pay);

        init();
        walletRepo = new WalletRepo(getApplicationContext());
        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatepikerPay();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });


        btnChooseWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectWalletActivity.class);
                startActivityForResult(intent, AddDealActivity.REQUEST_CODE_SELECT);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("".equals(edtAmount.getText().toString()) || "".equals(edtDes.getText().toString()) ||
                        "".equals(edtPerSon.getText().toString()) ||tvDate.getText().toString().equals("") ||tvWallet.getText().toString().equals(""))
                {
                    Toast.makeText(AddPayActivity.this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    debt=new Debt();
                    debt.setDesc(edtDes.getText().toString());
                    debt.setCreatedDate(tvDate.getText().toString());
                    debt.setPersonName(edtPerSon.getText().toString());
                    debt.setIdWallet(wallet.getId());
                    debt.setValue(Long.valueOf(edtAmount.getText().toString()));
                    debt.setDealType(1);

                    walletRepo.addDebt(debt);

                    wallet.setBalance(wallet.getBalance()-Double.valueOf(edtAmount.getText().toString()));
                    int res=walletRepo.updateBalanceWallet(wallet);
                    Toast.makeText(AddPayActivity.this, "Saved " + wallet.getBalance(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddPayActivity.this,MainActivity.class);
                    startActivity(intent);

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddDealActivity.REQUEST_CODE_SELECT) {

            switch (resultCode) {
                case AddDealActivity.REQUEST_CODE_SELECT_WALLET:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        wallet = (Wallet) bundle.getSerializable("wallet");
                        tvWallet.setText(wallet.getWalletName()+":"+wallet.getBalance());
                        Toast.makeText(getApplicationContext(), data.getStringExtra("walletName"), Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }

    public TextView getDate(){
        return tvDate;
    }
}
