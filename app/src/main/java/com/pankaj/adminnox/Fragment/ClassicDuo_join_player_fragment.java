package com.pankaj.adminnox.Fragment;


import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pankaj.adminnox.JavaClass.Squad_join;
import com.pankaj.adminnox.R;
import com.pankaj.adminnox.ViewHolder.CategoryViewHolder;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassicDuo_join_player_fragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Squad_join, CategoryViewHolder> recyclerAdapter;

    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public ClassicDuo_join_player_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_classic_duo_join_player_fragment, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("ClassicDuo_join_player").child("EntryComplete_player");

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadData();
        return v;
    }

    private void loadData() {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Squad_join>()
                        .setQuery(databaseReference, Squad_join.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Squad_join, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, int position, @NonNull final Squad_join model) {
                Log.d("hello", "position: " + position);
                holder.id.setText(model.getPaymentType());
                holder.pubgname.setText(model.getMobileno());
                holder.edit.setText(model.getPubgname());

                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteData();
                    }
                });

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.data_show_dialog, viewGroup, false);
                return new CategoryViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void deleteData() {

        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle("Alert!!");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setMessage("Do you want to Delete all Entry?");        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                        Query applesQuery = ref.child("ClassicDuo_join_player").child("EntryComplete_player");

                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                    appleSnapshot.getRef().removeValue();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.e(TAG, "onCancelled", databaseError.toException());
                            }
                        });

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // alertDialogBuilder.setCancelable(true);

                    }
                });
        alertDialogBuilder.show();



    }
}
