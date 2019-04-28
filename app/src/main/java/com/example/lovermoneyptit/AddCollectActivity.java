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
import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

public class AddCollectActivity extends AppCompatActivity {

    Button btnChooseDate;
    Button btnChooseWallet;
    TextView tvDate;
    EditText edtAmount,edtPerson,edtDes;
    Button btnBorrow;
    Debt debt;
    WalletRepo walletRepo;
    public void init(){
        btnChooseDate=findViewById(R.id.btn_choosedateborrow);
        btnChooseWallet=findViewById(R.id.btn_choose_wall_borrow);
        tvDate=findViewById(R.id.tv_datecollect);
        edtAmount=findViewById(R.id.edt_sotien_vay);
        edtDes=findViewById(R.id.edt_ghichu_vay);
        edtPerson=findViewById(R.id.edt_nguoi_cho_vay);
        btnBorrow=findViewById(R.id.btn_vay);
        wallet=new Wallet();
        txtSelectWallet=findViewById(R.id.tv_choose_wallet);
    }

    public TextView getDate(){
        return tvDate;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collect);
        init();
        debt=new Debt();
        walletRepo = new WalletRepo(getApplicationContext());

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatepickerDebt();
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

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ("".equals(edtAmount.getText().toString()) || "".equals(edtDes.getText().toString()) ||
                "".equals(edtPerson.getText().toString()) ||tvDate.getText().toString().equals("") ||txtSelectWallet.getText().toString().equals(""))
                {
                    Toast.makeText(AddCollectActivity.this, "Nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else{
                    debt.setDesc(edtDes.getText().toString());
                    debt.setCreatedDate(tvDate.getText().toString());
                    debt.setPersonName(edtPerson.getText().toString());
                    debt.setIdWallet(wallet.getId());
                    debt.setValue(Long.valueOf(edtAmount.getText().toString()));
                    debt.setDealType(2);

                    walletRepo.addDebt(debt);

                    wallet.setBalance(wallet.getBalance()+Double.valueOf(edtAmount.getText().toString()));
                    int res=walletRepo.updateBalanceWallet(wallet);
                    Toast.makeText(AddCollectActivity.this, "Saved " + wallet.getBalance(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(AddCollectActivity.this,MainActivity.class);
                    startActivity(intent);

                }

            }
        });
    }

    Wallet wallet;
    TextView txtSelectWallet;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddDealActivity.REQUEST_CODE_SELECT) {

            switch (resultCode) {
                case AddDealActivity.REQUEST_CODE_SELECT_WALLET:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        wallet = (Wallet) bundle.getSerializable("wallet");
                        txtSelectWallet.setText(wallet.getWalletName()+":"+wallet.getBalance());
                        Toast.makeText(getApplicationContext(), data.getStringExtra("walletName"), Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }
}
