package com.example.gobywind.qsbc;

import com.example.gobywind.qsbc.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class login extends Activity
{

    private SQLiteDatabase MySql;

    public static final String EXTRA_MESSAGE = "com.example.gobywind.qsbc.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MySql = this.openOrCreateDatabase("qsbc", MODE_PRIVATE, null);
        try {
         //   String Drop_Table = "drop table Data";
        //    MySql.execSQL(Drop_Table);
            String Create_Table = "create table Data(" +
                    "id int, " +
                    "fYear int, " +
                    "fMonth int, " +
                    "fDay int, " +
                    "fHour int, " +
                    "fMinute int, " +
                    "Title text, " +
                    "Matter text, " +
                    "Bo1 text," +
                    "Bo2 text," +
                    "ans int," +
                    "nYear int," +
                    "nMonth int," +
                    "nDay int," +
                    "nHour int," +
                    "nMinute int," +
                    "b1 int, " +
                    "b2 int, " +
                    "b3 int, " +
                    "b4 int, " +
                    "status int)";
            MySql.execSQL(Create_Table);
        }
        catch(Exception e)
        {
           // Toast.makeText(login.this, "data is not find", Toast.LENGTH_SHORT).show();
        }

        try
        {
          //  String Drop_Table = "drop table person";
          //  MySql.execSQL(Drop_Table);
            String Create_Table = "create table person(" +
                    "id int, " +
                    "count int, " +
                    "win int, " +
                    "full int," +
                    "name text," +
                    "pwd text)";
            MySql.execSQL(Create_Table);

            ContentValues values = new ContentValues();
            for(int i = 1; i <= 4; i++)
            {
                values.put("id", i);
                values.put("count", 100);
                values.put("win", 0);
                values.put("full", 0);
                values.put("name","user" + Integer.toString(i));
                values.put("pwd", "123456");
                MySql.insert("person", "id", values);
            }
        }
        catch (Exception e)
        {
           // Toast.makeText(login.this, "person is not find", Toast.LENGTH_SHORT).show();
        }

    }

    public void Login_f(View view)
    {

        EditText tname = (EditText)findViewById(R.id.us_name);
        EditText tpwd = (EditText)findViewById(R.id.us_pwd);

        String Iname = tname.getText().toString();
        String Ipwd = tpwd.getText().toString();

        String select = "select * from person where name = '"+Iname+"' and pwd = '"+Ipwd+"'";
        //String select = "select * from person where name = 'user1'";
        Cursor ccr;
        ccr = MySql.rawQuery(select, null);
        //if (ccr.getCount() > 0)
        if(true)
        {
            ccr.moveToFirst();
            Intent intent = new Intent(login.this, MainActivity.class);
//            intent.putExtra(EXTRA_MESSAGE, ccr.getInt(0));
            intent.putExtra(EXTRA_MESSAGE, 1);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(login.this, "User Name Or Password Error !", Toast.LENGTH_SHORT).show();
        }

    }

}
