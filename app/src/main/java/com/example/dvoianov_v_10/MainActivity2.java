package com.example.dvoianov_v_10;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loginTextView = findViewById(R.id.HelloLogin);

        Intent intent = getIntent();

        String login = intent.getStringExtra("LOGIN");

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("Привет," + "" + login + "!");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GREEN);

        stringBuilder.setSpan(colorSpan, 7, stringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        loginTextView.setText(stringBuilder);

        Button showUsersButton = findViewById(R.id.button1);
        showUsersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBaseHelper dbHelper = new MyDataBaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String[] projection = {
                        MyDataBaseHelper.COLUMN_LOGIN,
                        MyDataBaseHelper.COLUMN_PASSWORD
                };

                Cursor cursor = db.query(
                        MyDataBaseHelper.TABLE_USERS,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null
                );

                while (cursor.moveToNext()) {
                    String login = cursor.getString(cursor.getColumnIndexOrThrow(MyDataBaseHelper.COLUMN_LOGIN));
                    String password = cursor.getString(cursor.getColumnIndexOrThrow(MyDataBaseHelper.COLUMN_PASSWORD));

                    Log.d("User", "Login: " + login + ", Password: " + password);
                }

                cursor.close();
                db.close();


            }
        });
    }

    public void StartFirst(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void ToSite(View view){
        Uri uri = Uri.parse("https://www.s7.ru/en/info/s7-airlines/brand/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}