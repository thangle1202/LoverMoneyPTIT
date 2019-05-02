package com.example.lovermoneyptit;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lovermoneyptit.models.Debt;
import com.example.lovermoneyptit.models.Wallet;
import com.example.lovermoneyptit.repository.WalletRepo;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddPayActivity extends AppCompatActivity {
    public ArrayList<String> listContacts=new ArrayList<>();
    TextView tvDate,tvWallet,tvContact;
    EditText edtAmount,edtPerSon,edtDes;
    Button btnSave,btnChooseDate,btnChooseWallet;
    Wallet wallet;
    Debt debt;
    Toolbar toolbar;
    ImageView imgDate,imgWallet;
    WalletRepo walletRepo;
    public void init(){
        tvDate=findViewById(R.id.tv_datepay);
        tvWallet=findViewById(R.id.tv_choose_wallet_pay);
        btnSave=findViewById(R.id.btn_tra);
        btnChooseDate=findViewById(R.id.btn_choosedatepay);
        btnChooseWallet=findViewById(R.id.btn_choose_wall_pay);
        imgDate=findViewById(R.id.img_date_pay);
        toolbar=findViewById(R.id.toobar_pay);
        imgWallet=findViewById(R.id.img_wallet_pay);
        edtAmount=findViewById(R.id.edt_sotien_tra);
        tvContact=findViewById(R.id.tv_choose_contact_pay);
        edtDes=findViewById(R.id.edt_ghichu_tra);
        edtPerSon=findViewById(R.id.edt_nguoi_vay);
    }

    public static final int READ_CONTACTS_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pay);

        init();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_CODE);
        }
        //Toolbar toolbar=findViewById(R.id.toobar_pay);
        //ActionBar appBarLayout=getSupportActionBar();
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cho vay");
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


        imgWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectWalletActivity.class);
                startActivityForResult(intent, AddDealActivity.REQUEST_CODE_SELECT);
            }
        });

        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatepikerPay();
                newFragment.show(getSupportFragmentManager(), "date picker");
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

        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readContact();
                ShowAlertDialogWithListview();
            }
        });

    }


    public void ShowAlertDialogWithListview()
    {

        //Create sequence of items
        final CharSequence[] Animals = listContacts.toArray(new String[listContacts.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Chọn tên");
        dialogBuilder.setItems(Animals, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = Animals[item].toString();  //Selected item in listview
                edtPerSon.setText(selectedText);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }

    public void readContact(){
        StringBuilder stringBuilder=new StringBuilder();
        ContentResolver contentResolver=getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        if (cursor.getCount()>0){
            while (cursor.moveToNext()){


                String id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasNumber=Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasNumber>0){
                    Cursor cursor1=contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
                            ,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+ " = ?",
                            new String[]{id}, null);
                    while (cursor1.moveToNext()){
                        String phoneNumber=cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        stringBuilder.append("Contact ").append(name).append(", phone number: ").append(phoneNumber).append("\n\n");
                        listContacts.add(name);
                    }
                    cursor1.close();
                }
            }

        }
        cursor.close();

        Log.i("contactsss",stringBuilder.toString());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case READ_CONTACTS_CODE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Contacts permission granted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Contacts permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
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
