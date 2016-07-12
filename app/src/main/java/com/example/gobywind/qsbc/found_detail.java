package com.example.gobywind.qsbc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Gobywind on 2016/7/11.
 */
public class found_detail extends Activity
{
    int label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        label = intent.getIntExtra(login.EXTRA_MESSAGE, -1);
        setContentView(R.layout.activity_found_detail);
        Toast.makeText(found_detail.this, String.valueOf(label), Toast.LENGTH_SHORT).show();
    }
}
