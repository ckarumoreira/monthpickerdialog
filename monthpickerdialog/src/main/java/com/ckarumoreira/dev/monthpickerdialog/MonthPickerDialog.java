package com.ckarumoreira.dev.monthpickerdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;

import com.wefika.horizontalpicker.HorizontalPicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by carlos.moreira on 16/08/2016.
 */
public class MonthPickerDialog implements View.OnClickListener, HorizontalPicker.OnItemSelected {
    private Dialog dialog;
    private Context context;
    private OnMonthPickListener monthPickListener;
    private HorizontalPicker yearPicker;
    private List<String> yearList;
    private List<Integer> monthButtons;
    private int month;
    private int year;

    public MonthPickerDialog(Context context) {
        this.context = context;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_month_picker);
        initializeComponents();
    }

    private void initMonthButton(@IdRes int buttonResource, int monthNumber) {
        Button button = (Button)dialog.findViewById(buttonResource);
        button.setTag(monthNumber);
        button.setOnClickListener(this);
    }

    private void initYearPicker(int start, int end) {
        yearList = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            yearList.add(Integer.toString(i));
        }

        CharSequence[] charSequences = yearList.toArray(new CharSequence[yearList.size()]);

        yearPicker.setValues(charSequences);
    }

    private void initializeComponents() {
        monthButtons = new ArrayList<>();
        monthButtons.add(R.id.button_january);
        monthButtons.add(R.id.button_february);
        monthButtons.add(R.id.button_march);
        monthButtons.add(R.id.button_april);
        monthButtons.add(R.id.button_may);
        monthButtons.add(R.id.button_june);
        monthButtons.add(R.id.button_july);
        monthButtons.add(R.id.button_august);
        monthButtons.add(R.id.button_september);
        monthButtons.add(R.id.button_october);
        monthButtons.add(R.id.button_november);
        monthButtons.add(R.id.button_december);

        initMonthButton(R.id.button_january, Calendar.JANUARY);
        initMonthButton(R.id.button_february, Calendar.FEBRUARY);
        initMonthButton(R.id.button_march, Calendar.MARCH);
        initMonthButton(R.id.button_april, Calendar.APRIL);
        initMonthButton(R.id.button_may, Calendar.MAY);
        initMonthButton(R.id.button_june, Calendar.JUNE);
        initMonthButton(R.id.button_july, Calendar.JULY);
        initMonthButton(R.id.button_august, Calendar.AUGUST);
        initMonthButton(R.id.button_september, Calendar.SEPTEMBER);
        initMonthButton(R.id.button_october, Calendar.OCTOBER);
        initMonthButton(R.id.button_november, Calendar.NOVEMBER);
        initMonthButton(R.id.button_december, Calendar.DECEMBER);

        Button buttonPick = (Button)dialog.findViewById(R.id.button_pick);
        buttonPick.setOnClickListener(this);

        Button buttonCancel = (Button)dialog.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(this);

        yearPicker = (HorizontalPicker)dialog.findViewById(R.id.year_picker);
        yearPicker.setOnItemSelectedListener(this);
        initYearPicker(1900, 2100);

        if (year == 0) {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        int index = yearList.indexOf(Integer.toString(year));
        if (index > -1) {
            yearPicker.setSelectedItem(index);
        } else {
            index = yearList.size() - 1;
            year = Integer.parseInt(yearList.get(index));
            yearPicker.setSelectedItem(index);
        }
    }

    public void show() {
        dialog.show();
    }

    public void hide() {
        dialog.hide();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void setOnShowListener(DialogInterface.OnShowListener listener) {
        dialog.setOnShowListener(listener);
    }

    public void setOnMonthPickListener(OnMonthPickListener listener) {
        monthPickListener = listener;
    }

    private void setButtonBackground(int monthNumber, @ColorInt int color) {
        Button button = null;

        switch (monthNumber) {
            case Calendar.JANUARY:
                button = (Button)dialog.findViewById(R.id.button_january);
                break;
            case Calendar.FEBRUARY:
                button = (Button)dialog.findViewById(R.id.button_february);
                break;
            case Calendar.MARCH:
                button = (Button)dialog.findViewById(R.id.button_march);
                break;
            case Calendar.APRIL:
                button = (Button)dialog.findViewById(R.id.button_april);
                break;
            case Calendar.MAY:
                button = (Button)dialog.findViewById(R.id.button_may);
                break;
            case Calendar.JUNE:
                button = (Button)dialog.findViewById(R.id.button_june);
                break;
            case Calendar.JULY:
                button = (Button)dialog.findViewById(R.id.button_july);
                break;
            case Calendar.AUGUST:
                button = (Button)dialog.findViewById(R.id.button_august);
                break;
            case Calendar.SEPTEMBER:
                button = (Button)dialog.findViewById(R.id.button_september);
                break;
            case Calendar.OCTOBER:
                button = (Button)dialog.findViewById(R.id.button_october);
                break;
            case Calendar.NOVEMBER:
                button = (Button)dialog.findViewById(R.id.button_november);
                break;
            case Calendar.DECEMBER:
                button = (Button)dialog.findViewById(R.id.button_december);
                break;
        }

        if (button == null) {
            return;
        }

        button.setBackgroundColor(color);
    }

    @Override
    public void onClick(View view) {
        int target = view.getId();
        if (monthButtons.contains(target)) {
            int commonColor = context.getResources().getColor(R.color.month_picker_month_button_background_color);
            setButtonBackground(month, commonColor);
            month = (int)view.getTag();
            int activeColor = context.getResources().getColor(R.color.month_picker_month_button_active_background_color);
            view.setBackgroundColor(activeColor);
        } else if (target == R.id.button_pick) {
            monthPickListener.pickMonth(year, month);
        } else if (target == R.id.button_cancel) {
            dialog.hide();
        }
    }

    @Override
    public void onItemSelected(int index) {
        String value = yearList.get(index);
        year = Integer.parseInt(value);
    }

    public interface OnMonthPickListener {
        void pickMonth(int year, int month);
    }
}
