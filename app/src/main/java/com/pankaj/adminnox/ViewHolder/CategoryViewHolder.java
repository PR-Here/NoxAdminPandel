package com.pankaj.adminnox.ViewHolder;


import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.adminnox.R;

import static android.widget.Toast.LENGTH_SHORT;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView id,pubgname,edit,date;


    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        id = (TextView)itemView.findViewById(R.id.id);
        pubgname = (TextView)itemView.findViewById(R.id.name);
        edit = (TextView)itemView.findViewById(R.id.amount);
        date=itemView.findViewById(R.id.date);
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