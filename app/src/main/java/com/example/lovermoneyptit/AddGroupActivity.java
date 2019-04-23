package com.example.lovermoneyptit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lovermoneyptit.models.Group;
import com.example.lovermoneyptit.repository.WalletRepo;
import com.example.lovermoneyptit.utils.GroupType;

public class AddGroupActivity extends AppCompatActivity {

    private EditText txtGroupName;
    private RadioButton btnBorrowLoan, btnCashIn, btnCashOut;
    private Toolbar toolBar;
    private WalletRepo walletRepo;
    private int groupType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        txtGroupName = findViewById(R.id.txtGroupName);
        btnBorrowLoan = findViewById(R.id.btnBorrowLoan);
        btnCashIn = findViewById(R.id.btnCashIn);
        btnCashOut = findViewById(R.id.btnCashOut);
        toolBar = findViewById(R.id.toolbarAddGroup);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(R.string.txtNewGroup);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        } else if (id == R.id.btnAddGroup) {
            // add group to db
            walletRepo = new WalletRepo(getApplicationContext());
            Group groupToAdd = new Group();
            groupToAdd.setGroupName(txtGroupName.getText().toString());
            if (btnBorrowLoan.isSelected()) {
                groupType = GroupType.LOAN;
            } else if (btnCashIn.isSelected()) {
                groupType = GroupType.CASH_IN;
            } else {
                groupType = GroupType.CASH_OUT;
            }
            groupToAdd.setGroupType(groupType);
            walletRepo.addGroup(groupToAdd);

            Toast.makeText(this, "Save Group", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_add_group, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
