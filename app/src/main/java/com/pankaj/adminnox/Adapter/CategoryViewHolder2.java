package com.pankaj.adminnox.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankaj.adminnox.R;

public class CategoryViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView player,mode,date,entryfee;


    public CategoryViewHolder2(@NonNull View itemView) {
        super(itemView);
        player = (TextView)itemView.findViewById(R.id.player);
        mode = (TextView)itemView.findViewById(R.id.mode);
        date = (TextView)itemView.findViewById(R.id.date);
        entryfee = (TextView)itemView.findViewById(R.id.entryfee);
    }

    @Override
    public void onClick(View v) {

    }
}
