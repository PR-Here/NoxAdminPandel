package com.pankaj.adminnox.ViewHolder;


import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.adminnox.R;

import static android.widget.Toast.LENGTH_SHORT;

public class CategoryViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView name,mobileno,coins;


    public CategoryViewHolders(@NonNull View itemView) {
        super(itemView);
        name = (TextView)itemView.findViewById(R.id.name);
        mobileno = (TextView)itemView.findViewById(R.id.coins);
        coins = (TextView)itemView.findViewById(R.id.number);
    }

    @Override
    public void onClick(View v) {

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
}