package com.example.monsaiya.afinal;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monsaiya on 10/12/2560.
 */

public class Adapter extends ArrayAdapter<item> {

    private Context mContext;
    private int mLayoutResId;
    private ArrayList<item> mIncomeList;


    public Adapter(@NonNull Context context, int resource, @NonNull ArrayList<item> objects) {
        super(context, resource, objects);

        this.mContext = context;
        this.mLayoutResId = resource;
        this.mIncomeList = objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);
        NumberFormat nf = NumberFormat.getInstance();

        item item = mIncomeList.get(position);

        ImageView ImageView = itemLayout.findViewById(R.id.imageView2);
        TextView TitleTextView = itemLayout.findViewById(R.id.title);
        TextView NumberTextView = itemLayout.findViewById(R.id.money);

        TitleTextView.setText(item.title);
        NumberTextView.setText(String.valueOf(nf.format(item.money)));

        if(item.number == 1){
            ImageView.setImageResource(R.drawable.ic_income);
        }else{
            ImageView.setImageResource(R.drawable.ic_expense);
        }



        return itemLayout;
    }
}
