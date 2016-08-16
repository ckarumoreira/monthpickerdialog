package com.ckarumoreira.dev.animation_tests;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ckarumoreira.dev.monthpickerdialog.MonthPickerDialog;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textMonth;
    MonthPickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();

    }

    private void initializeComponents() {
        textMonth = (TextView)findViewById(R.id.text_month);
        textMonth.setOnClickListener(this);

        dialog = new MonthPickerDialog(this);
        dialog.setOnMonthPickListener(new MonthPickerDialog.OnMonthPickListener() {
            @Override
            public void pickMonth(int year, int month) {
                Calendar date = Calendar.getInstance();
                date.set(year, month, 1);
                String yearDisplay = Integer.toString(date.get(Calendar.YEAR));
                String monthDisplay = date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

                textMonth.setText(String.format("%s, %s", monthDisplay, yearDisplay));
                dialog.hide();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_month:
                dialog.show();
                break;
        }
    }
}
