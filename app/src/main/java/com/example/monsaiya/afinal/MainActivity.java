package com.example.monsaiya.afinal;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DB mHelper;
    private SQLiteDatabase mDb;
    TextView tx_sum;
    NumberFormat nf;

    private ArrayList<item> mIncome = new ArrayList<>();
    private Adapter mAdapter;

    int sum = 0 ;
    int phoneid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nf = NumberFormat.getInstance();

        mHelper = new DB(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();



        Button b_income = findViewById(R.id.button);
        Button b_outcome = findViewById(R.id.button2);


        mAdapter = new Adapter(
                this,
                R.layout.item,
                mIncome
        );

        ListView lv = findViewById(R.id.list_item);
        lv.setAdapter(mAdapter);

        b_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,input.class);
                startActivity(intent);
            }
        });
        b_outcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,output.class);
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                item item = mIncome.get(position);
                phoneid = item.id;

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("ยืนยันลบรายการ '"+item.title + " "+ String.valueOf(item.money)+" บาท' ?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mDb.delete(
                                DB.TABLE_NAME,
                                DB.COL_ID + "=?" ,
                                new String[]{String.valueOf(phoneid)}
                        );
                        sum = 0 ;
                        loadDataFromDb();
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });
                builder.show();





                return true;
            }
        });

    }

    private void loadDataFromDb() {
        tx_sum = findViewById(R.id.sum);
        Cursor cursor = mDb.query(
                DB.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mIncome.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DB.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(DB.COL_TITLE));
            int number = cursor.getInt(cursor.getColumnIndex(DB.COL_NUMBER));
            int money = cursor.getInt(cursor.getColumnIndex(DB.COL_MONEY));
            if(number == 1){
                sum += money;
            } else {
                sum -= money;
            }
            item item = new item(id, title, number, money);
            mIncome.add(item);
        }
//        for (int i = 0 ;i < mIncome.size() ; i++){
//
//        }
        tx_sum.setText("คงเหลือ " +String.valueOf(nf.format(sum)) + " บาท");

    }
}
