package com.example.gobywind.qsbc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

/**
 * Created by Gobywind on 2016/7/11.
 */
public class start_cf extends Activity
{
    private EditText endTime;
    private String initEndTime = "2016年7月12日 00:00";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
//        label = intent.getIntExtra(login.EXTRA_MESSAGE, -1);
        setContentView(R.layout.start_cf);
//        Toast.makeText(.this, String.valueOf(label), Toast.LENGTH_SHORT).show();
        endTime = (EditText) findViewById(R.id.end_time);
        endTime.setText(initEndTime);
        endTime.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                date_time_picker dateTimePickerDialog = new date_time_picker(start_cf.this, initEndTime);
                dateTimePickerDialog.dateTimePicKDialog(endTime);
            }
        });
    }
}
