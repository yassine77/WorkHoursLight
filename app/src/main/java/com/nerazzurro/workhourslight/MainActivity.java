package com.nerazzurro.workhourslight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TimePickerDialog picker;
    private EditText mStartAM;
    private EditText mStopAM;
    private EditText mStartPM;
    private EditText mStopPM;
    private Button mCalculate;
    private TextView mResult;
    private TimeShift mTimeShift;
    private int mFilledBlanksNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartAM = findViewById(R.id.activity_main_start_am);
        mStopAM = findViewById(R.id.activity_main_stop_am);
        mStartPM = findViewById(R.id.activity_main_start_pm);
        mStopPM = findViewById(R.id.activity_main_stop_pm);
        mCalculate = findViewById(R.id.activity_main_calculate_btn);
        mResult = findViewById(R.id.activity_main_text_result);
        mFilledBlanksNumber = 0;

        toClock(mStartAM);
        toClock(mStopAM);
        toClock(mStartPM);
        toClock(mStopPM);

        mCalculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                List<EditText> champs = Arrays.asList(mStartAM, mStopAM, mStartPM, mStopPM);

                if(!CheckTimeFields(champs)){
                    return;
                }

                LocalTime startAM = LocalTime.parse(mStartAM.getText().toString());
                LocalTime stopAM = LocalTime.parse(mStopAM.getText().toString());
                LocalTime startPM = LocalTime.parse(mStartPM.getText().toString());
                LocalTime stopPM = LocalTime.parse(mStopPM.getText().toString());

                mTimeShift = new TimeShift(startAM, stopAM, startPM, stopPM);

                mResult.setText(LocalTime.MIN.plus(mTimeShift.Duration()).toString());
            }
        });
    }

    private void toClock(final EditText eText){
        final NumberFormat f = new DecimalFormat("00");
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
                                eText.setText(f.format(sHour) + ":" + f.format(sMinute));
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });
    }

    private boolean CheckTimeFields(List<EditText> times) {
        int champsManquants = 0;
        for (int i = 0; i < times.size(); i++) {
            if (TextUtils.isEmpty(times.get(i).getText())) {
                times.get(i).setError("This blank is to be required!");
                champsManquants++;
            }
        }

        if(champsManquants > 0){
            Toast.makeText(this, "Tous les champs doivent être renseignés.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
