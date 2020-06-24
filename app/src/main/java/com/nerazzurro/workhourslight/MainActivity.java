package com.nerazzurro.workhourslight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TimePickerDialog picker;
    private EditText mStartAM;
    private EditText mStopAM;
    private EditText mStartPM;
    private EditText mStopPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartAM = findViewById(R.id.activity_main_start_am);
        mStopAM = findViewById(R.id.activity_main_stop_am);
        mStartPM = findViewById(R.id.activity_main_start_pm);
        mStopPM = findViewById(R.id.activity_main_stop_pm);

        toClock(mStartAM);
        toClock(mStopAM);
        toClock(mStartPM);
        toClock(mStopPM);

        /*mStartAM.setInputType(InputType.TYPE_NULL);
        mStartAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                mStartAM.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });*/
    }

    private void toClock(final EditText eText){
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eText.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
    }
}