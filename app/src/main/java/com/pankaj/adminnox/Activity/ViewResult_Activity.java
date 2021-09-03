package com.pankaj.adminnox.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pankaj.adminnox.Adapter.CategoryViewHolder2;
import com.pankaj.adminnox.ModeActivity.Result_Model;
import com.pankaj.adminnox.R;

public class ViewResult_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Result_Model, CategoryViewHolder2> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result_);


        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Admin_Match_Deatil").child("detail");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ViewResult_Activity.this);
        recyclerView.setLayoutManager(layoutManager);

        loadData();
    }

    private void loadData()
    {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Result_Model>()
                        .setQuery(databaseReference,Result_Model.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Result_Model, CategoryViewHolder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder2 holder, int position, @NonNull Result_Model model) {

                holder.player.setText(model.getPlayer());
                holder.mode.setText(model.getMode());
                holder.date.setText(model.getDate());
                holder.entryfee.setText(model.getEntryfee());

            }

            @NonNull
            @Override
            public CategoryViewHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.result_layout,viewGroup,false);
                return new CategoryViewHolder2(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }
}
