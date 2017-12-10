package com.example.monsaiya.afinal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class input extends AppCompatActivity {

    EditText mTitleEditText;
    EditText mNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mTitleEditText = findViewById(R.id.mon1);
        mNumberEditText = findViewById(R.id.mon2);
        Button mSaveButton = findViewById(R.id.button);



        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
                Intent intent = new Intent(input.this,MainActivity.class);
                startActivity(intent);
            }
        });







    }

    public void save(){
        String phoneTitle = mTitleEditText.getText().toString();
        String phoneNumber = mNumberEditText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(DB.COL_TITLE, phoneTitle);
        cv.put(DB.COL_NUMBER, 1);
        cv.put(DB.COL_MONEY, Integer.valueOf(phoneNumber));

        DB dbHelper = new DB(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert(DB.TABLE_NAME, null, cv);

    }
}
