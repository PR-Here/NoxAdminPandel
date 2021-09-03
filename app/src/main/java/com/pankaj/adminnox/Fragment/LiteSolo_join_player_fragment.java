package com.pankaj.adminnox.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import static android.content.Context.MODE_PRIVATE;
import static androidx.core.os.LocaleListCompat.create;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiteSolo_join_player_fragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Squad_join, CategoryViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText mobileno, cmobileno, coin;
    TextView coin_txt;
    private DatabaseReference Post, post_about, db_confirmmno, realsecound_db;
    String st_share, st_name, st_no, st_telegram, dupdate, dupiid;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference2 = firebaseDatabase.getReference();
    private DatabaseReference name_db = firebaseDatabase.getReference();
    TextView total_coin;

    String mobile, number2, number, st_share2, phoneno, edtmobilenoModel;

    public LiteSolo_join_player_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lite_solo_join_player_fragment, container, false);

        SharedPreferences preferences = getContext().getSharedPreferences("signup", MODE_PRIVATE);
        String value = preferences.getString("number", " ");
        mobile = value;
        Log.d("tag", "loginno" + mobile);

        name_db = FirebaseDatabase.getInstance().getReference("signup_user")
                .child("every_signup_user").child(mobile);
        databaseReference = FirebaseDatabase.getInstance().getReference("FreeFireSolo_join_player").child("EntryComplete_player");
        readRealTime();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadData();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("signup_user").child("every_signup_user")
                .child(mobile);

        db_confirmmno = FirebaseDatabase.getInstance().getReference("signup_user")
                .child("every_signup_user").child(mobile);


        return v;
    }

    private void readRealTime() {

        Handler handler2 = new Handler();
        handler2.postDelayed(
                new Runnable() {
                    public void run() {

                        name_db
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Log.d("addCoins", "Dataaa " + dataSnapshot);

                                        st_share = dataSnapshot.child("freecoins").getValue(String.class);
                                        st_name = dataSnapshot.child("name").getValue(String.class);
                                        st_no = dataSnapshot.child("mobileno").getValue(String.class);
                                        Log.d("addCoins", "coinsss " + st_share);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                    }
                                });
                    }
                }, 1000L);


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
                edtmobilenoModel = model.getMobileno();

                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteData();
                    }
                });

                holder.pubgname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView name;
                        Button send, cancel;
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.sendcoins_dialog);
                        dialog.setCancelable(false);
                        cancel = dialog.findViewById(R.id.cancel_button);
                        send = dialog.findViewById(R.id.cancel_button2);
                        mobileno = dialog.findViewById(R.id.mobileno_dialog_coins);
                        total_coin = dialog.findViewById(R.id.total_coins_dialog_transfer);
                        cmobileno = dialog.findViewById(R.id.confirmmobileno_dialog_coins);
                        coin_txt = dialog.findViewById(R.id.total_coins_dialog_transfer);
                        name = dialog.findViewById(R.id.name_dialog_transfer);
                        total_coin.setText(st_share);
                        coin = dialog.findViewById(R.id.coins);
                        mobileno.setText(model.getMobileno());
                        cmobileno.setText(model.getMobileno());
                        coin_txt.setText(st_share);
                        coin = dialog.findViewById(R.id.coins_dialog_coins);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        userDeatil();
                        send.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (coin.getText().toString().equals("")) {
                                    Toast.makeText(getApplicationContext(), "please add coins to transfer", Toast.LENGTH_SHORT).show();
                                } else {
                                    userInfo();
                                }
                            }
                        });

                        dialog.show();
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
        alertDialogBuilder.setMessage("Do you want to Delete all Entry?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

                        Query applesQuery = ref.child("LiteSolo_join_player").child("EntryComplete_player");

                        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
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


    private void userInfo() {

        number = mobileno.getText().toString();
        Log.d("Permissions", "mobilennnno: " + number);

        db_confirmmno = FirebaseDatabase.getInstance().getReference("signup_user")
                .child("every_signup_user");


        db_confirmmno.child(number).addListenerForSingleValueEvent(valueEventListener);
        Log.d("Permissions", "mobilennnno: " + number);


    }

    private void userDeatil() {

        number2 = mobileno.getText().toString();

        realsecound_db = FirebaseDatabase.getInstance().getReference("signup_user")
                .child("every_signup_user").child(number2);

        realsecound_db
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Log.d("addCoins", "Dataaabbb " + dataSnapshot);

                            st_share2 = dataSnapshot.child("freecoins").getValue(String.class);

                            Log.d("addCoins", "coinsssttt " + st_share2);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.d("Permissions", "dataaa: " + dataSnapshot);
            if (dataSnapshot.exists()) {
                phoneno = dataSnapshot.child("mobileno").getValue(String.class);
                final String name = dataSnapshot.child("name").getValue(String.class);

                if (phoneno.equals(number)) {


                    Log.d("Permissions", "noooo: " + mobileno);
                    Log.d("Permissions", "st_share: " + st_share);

                    validate();

                }

            } else {
                Toast.makeText(getApplicationContext(), "No User Found !", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

        }

    };

    private void validate() {

        if (!st_no.equals(mobileno.getText().toString())) {

            //my old coins of freefire
            String oldUserCoins = st_share;
            String newCoins = coin.getText().toString();

            int oldUser = Integer.parseInt(oldUserCoins);
            int newUser = Integer.parseInt(newCoins);

            if (oldUser >= newUser) {

                String oldCoins = st_share;
                String EnterNewCoins = coin.getText().toString();

                int old = Integer.parseInt(oldCoins);
                int newEntry = Integer.parseInt(EnterNewCoins);

                int FirstResult = old - newEntry;

                final String firstEntry = String.valueOf(FirstResult);

                total_coin.setText(firstEntry);

                //old user
                name_db = FirebaseDatabase.getInstance().getReference("signup_user")
                        .child("every_signup_user").child(mobile);


                name_db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {


                            name_db = FirebaseDatabase.getInstance().getReference("signup_user")
                                    .child("every_signup_user").child(mobile);

                            name_db.child("freecoins").setValue(firstEntry);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


                Validate2();

            } else {
                Toast.makeText(getApplicationContext(), "you dont have any coins in your wallet please add coins and then try it.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "you cant send coins on your number ", Toast.LENGTH_LONG).show();

        }

    }

    private void Validate2() {

        String cnumber = mobileno.getText().toString();

        if (!st_no.equals(cnumber)) {

            String oldUserCoins = st_share2;
            String edt_Coins = coin.getText().toString();

            int Old = Integer.parseInt(oldUserCoins);
            final int edt = Integer.parseInt(edt_Coins);

            int allCoins = Old + edt;

            final String all = String.valueOf(allCoins);


            number = mobileno.getText().toString();

            db_confirmmno = FirebaseDatabase.getInstance().getReference("signup_user")
                    .child("every_signup_user").child(number);

            db_confirmmno.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {


                        db_confirmmno = FirebaseDatabase.getInstance().getReference("signup_user")
                                .child("every_signup_user").child(number);

                        db_confirmmno.child("freecoins").setValue(all);

                        Toast.makeText(getApplicationContext(), edt + " Coins transfer Successfully to " + number, Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "you can not  be send coins on your own number", Toast.LENGTH_SHORT).show();
        }
    }

}

