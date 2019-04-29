package com.example.lovermoneyptit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.lovermoneyptit.utils.FormatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatepikerPay extends DialogFragment {
    public static String createdDate = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
        return datePickerDialog;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    createdDate = view.getDayOfMonth() +
                            "/" + (view.getMonth() + 1) +
                            "/" + view.getYear();
                    Toast.makeText(getActivity(), createdDate, Toast.LENGTH_SHORT).show();
                    Date date = FormatUtils.getDateFromDatePicker(view);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    ((AddPayActivity) getActivity()).getDate().setText(simpleDateFormat.format(date));
                }
            };

}
