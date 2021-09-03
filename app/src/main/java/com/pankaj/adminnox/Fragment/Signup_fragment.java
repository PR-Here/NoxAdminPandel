package com.pankaj.adminnox.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.pankaj.adminnox.Activity.AllUserDetail_Activity;
import com.pankaj.adminnox.Activity.EnterUserDeatl_Activity;
import com.pankaj.adminnox.JavaClass.classic_top;
import com.pankaj.adminnox.R;
import com.pankaj.adminnox.ViewHolder.CategoryViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Signup_fragment extends Fragment {

    RecyclerView recyclerView;

    FirebaseRecyclerAdapter<classic_top, CategoryViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    DatabaseReference databaseReference;
    TextView count;
    FloatingActionButton floatingActionButton;

    public Signup_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.signup_fragment, container, false);

        count = v.findViewById(R.id.count);
        floatingActionButton = v.findViewById(R.id.plus_btn);
        floatingActionButton.setVisibility(View.GONE);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EnterUserDeatl_Activity.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("signup_user")
                .child("every_signup_user");

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadData();
        return v;
    }

    private void loadData() {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<classic_top>()
                        .setQuery(databaseReference, classic_top.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<classic_top, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder,
                                            int position, @NonNull final classic_top model) {
                Log.d("hello", "position: " + position);

                holder.id.setText(model.getCoins());
                holder.pubgname.setText(model.getMobileno());
                holder.edit.setText(model.getName());
                holder.date.setText(model.getDate());

                count.setText(String.valueOf(recyclerView.getAdapter().getItemCount()));


                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), AllUserDetail_Activity.class);
                        intent.putExtra("phoneno", model.getMobileno());
                        Log.d("hello", "phoneno: " + model.getMobileno());
                        startActivity(intent);
                    }
                });

                holder.date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), AllUserDetail_Activity.class);
                        intent.putExtra("phoneno", model.getMobileno());
                        Log.d("hello", "phoneno: " + model.getMobileno());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.signup_result, viewGroup, false);
                return new CategoryViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);

        int countt = 0;
        if (recyclerAdapter != null) {
            countt = recyclerAdapter.getItemCount();
            Log.d("hello", "counttt: " + countt);
        }


    }


}
