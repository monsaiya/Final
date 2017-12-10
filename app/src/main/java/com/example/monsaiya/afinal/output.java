package com.example.monsaiya.afinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class output extends AppCompatActivity {

    EditText mTitleEditText;
    EditText mNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        mTitleEditText = findViewById(R.id.eiei);
        mNumberEditText = findViewById(R.id.eiei2);
        Button mSaveButton = findViewById(R.id.beiei);



        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Intent intent = new Intent(output.this,MainActivity.class);
                startActivity(intent);
            }
        });







    }

    public void save(){
        String phoneTitle = mTitleEditText.getText().toString();
        String phoneNumber = mNumberEditText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(DB.COL_TITLE, phoneTitle);
        cv.put(DB.COL_NUMBER, 2);
        cv.put(DB.COL_MONEY, Integer.valueOf(phoneNumber));

        DB dbHelper = new DB(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert(DB.TABLE_NAME, null, cv);

    }


    }

