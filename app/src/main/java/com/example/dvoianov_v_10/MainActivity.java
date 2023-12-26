package com.example.dvoianov_v_10;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private MyDataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = findViewById(R.id.stringLogin);
        passwordEditText = findViewById(R.id.stringPassword);
        loginButton = findViewById(R.id.buttonLogin);

        databaseHelper = new MyDataBaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
                    addUserToDatabase(login, password);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("LOGIN", login);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Введите логин и пароль", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addUserToDatabase(String login, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDataBaseHelper.COLUMN_LOGIN, login);
        values.put(MyDataBaseHelper.COLUMN_PASSWORD, password);

        long newRowId = db.insert(MyDataBaseHelper.TABLE_USERS, null, values);

        if (newRowId != -1) {
            Toast.makeText(MainActivity.this, "Пользователь добавлен", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Ошибка при добавлении пользователя", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}