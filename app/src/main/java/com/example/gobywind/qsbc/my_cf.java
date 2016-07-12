package com.example.gobywind.qsbc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gobywind on 2016/7/11.
 */
public class my_cf extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = getIntent();
//        label = intent.getIntExtra(login.EXTRA_MESSAGE, -1);
        setContentView(R.layout.my_cf);

    }

    public void set_start(View view)
    {
        Toast.makeText(my_cf.this, "我发起的", Toast.LENGTH_SHORT).show();
        TextView tv = (TextView)findViewById(R.id.cf_type);
        tv.setText("我发起的");
    }

    public void set_join(View view)
    {
        Toast.makeText(my_cf.this, "我参加的", Toast.LENGTH_SHORT).show();
        TextView tv = (TextView)findViewById(R.id.cf_type);
        tv.setText("我参加的");
    }

    public void set_collect(View view)
    {
        Toast.makeText(my_cf.this, "我收藏的", Toast.LENGTH_SHORT).show();
        TextView tv = (TextView)findViewById(R.id.cf_type);
        tv.setText("我收藏的");
    }

}
