package com.pankaj.adminnox.ModeActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pankaj.adminnox.JavaClass.Classic_Squad;
import com.pankaj.adminnox.R;

public class LiteSquad_Activity extends AppCompatActivity {

    EditText matchno, date, time, registration, roomid, password, prizepool, perkill, entryfee, type,
            mode, map, information, disabled, paidentry, winner, rank1, rank2, rank3, rank4, rank5, rank6, rank7, rank8, rank9, rank10, upiid;
    Button update;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_squad_);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.whiteall), PorterDuff.Mode.SRC_ATOP);


        matchno = findViewById(R.id.matchno);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        registration = findViewById(R.id.registration);
        roomid = findViewById(R.id.roomid);
        password = findViewById(R.id.password);
        prizepool = findViewById(R.id.prizepool);
        perkill = findViewById(R.id.perkill);
        entryfee = findViewById(R.id.entryfee);
        type = findViewById(R.id.type);
        mode = findViewById(R.id.mode);
        map = findViewById(R.id.map);
        information = findViewById(R.id.information);
        disabled = findViewById(R.id.disabled);
        paidentry = findViewById(R.id.paidentry);
        winner = findViewById(R.id.winner);
        rank1 = findViewById(R.id.rank1);
        rank2 = findViewById(R.id.rank2);
        rank3 = findViewById(R.id.rank3);
        rank4 = findViewById(R.id.rank4);
        rank5 = findViewById(R.id.rank5);
        rank6 = findViewById(R.id.rank6);
        rank7 = findViewById(R.id.rank7);
        rank8 = findViewById(R.id.rank8);
        rank9 = findViewById(R.id.rank9);
        rank10 = findViewById(R.id.rank10);
        upiid = findViewById(R.id.upiid);
        upiid.setText("ranap8445@oksbi");
        update = findViewById(R.id.btn_update);

        databaseReference = FirebaseDatabase.getInstance().getReference("freeSquad")
                .child("firstactivity");

        headerData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });
    }

    private void headerData() {


        databaseReference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            String st_matchno = dataSnapshot.child("matchno").getValue(String.class);
                            String st_date = dataSnapshot.child("date").getValue(String.class);
                            String st_time = dataSnapshot.child("time").getValue(String.class);
                            String st_registration = dataSnapshot.child("status").getValue(String.class);
                            String st_roomid = dataSnapshot.child("roomid").getValue(String.class);

                            String st_password = dataSnapshot.child("password").getValue(String.class);
                            String st_prizepool = dataSnapshot.child("prizepool").getValue(String.class);
                            String st_perkill = dataSnapshot.child("perkill").getValue(String.class);
                            String st_entryfee = dataSnapshot.child("entryfee").getValue(String.class);

                            String st_type = dataSnapshot.child("type").getValue(String.class);
                            String st_mode = dataSnapshot.child("mode").getValue(String.class);
                            String st_map = dataSnapshot.child("map").getValue(String.class);
                            String st_info = dataSnapshot.child("information").getValue(String.class);
                            String st_disabled = dataSnapshot.child("disabled").getValue(String.class);

                            String st_paidentry = dataSnapshot.child("paidentry").getValue(String.class);
                            String st_einner = dataSnapshot.child("winner").getValue(String.class);
                            String st_rank1 = dataSnapshot.child("rank1").getValue(String.class);
                            String st_rank2 = dataSnapshot.child("rank2").getValue(String.class);
                            String st_rank3 = dataSnapshot.child("rank3").getValue(String.class);
                            String st_rank4 = dataSnapshot.child("rank4").getValue(String.class);
                            String st_rank5 = dataSnapshot.child("rank5").getValue(String.class);

                            String st_rank6 = dataSnapshot.child("rank6").getValue(String.class);
                            String st_rank7 = dataSnapshot.child("rank7").getValue(String.class);
                            String st_rank8 = dataSnapshot.child("rank8").getValue(String.class);
                            String st_rank9 = dataSnapshot.child("rank9").getValue(String.class);
                            String st_rank10 = dataSnapshot.child("rank10").getValue(String.class);


                            matchno.setText(st_matchno);
                            date.setText(st_date);
                            time.setText(st_time);
                            registration.setText(st_registration);
                            roomid.setText(st_roomid);
                            password.setText(st_password);
                            prizepool.setText(st_prizepool);
                            perkill.setText(st_perkill);
                            entryfee.setText(st_entryfee);
                            type.setText(st_type);
                            mode.setText(st_mode);
                            map.setText(st_map);
                            information.setText(st_info);
                            disabled.setText(st_disabled);
                            paidentry.setText(st_paidentry);
                            winner.setText(st_einner);
                            rank1.setText(st_rank1);
                            rank2.setText(st_rank2);
                            rank3.setText(st_rank3);
                            rank4.setText(st_rank4);
                            rank5.setText(st_rank5);
                            rank6.setText(st_rank6);
                            rank7.setText(st_rank7);
                            rank8.setText(st_rank8);
                            rank9.setText(st_rank9);
                            rank10.setText(st_rank10);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void Update() {


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {

                    String st_matchno = matchno.getText().toString();
                    String st_date = date.getText().toString();
                    String st_time = time.getText().toString();
                    String st_registration = registration.getText().toString();
                    String st_roomid = roomid.getText().toString();
                    String st_password = password.getText().toString();
                    String st_prizepool = prizepool.getText().toString();
                    String st_perkill = perkill.getText().toString();
                    String st_entryfee = entryfee.getText().toString();
                    String st_type = type.getText().toString();
                    String st_mode = mode.getText().toString();
                    String st_map = map.getText().toString();
                    String st_info = information.getText().toString();
                    String st_disabled = disabled.getText().toString();
                    String st_paidentry = paidentry.getText().toString();
                    String st_winner = winner.getText().toString();
                    String st_rank1 = rank1.getText().toString();
                    String st_rank2 = rank2.getText().toString();
                    String st_rank3 = rank3.getText().toString();
                    String st_rank4 = rank4.getText().toString();
                    String st_rank5 = rank5.getText().toString();
                    String st_rank6 = rank6.getText().toString();
                    String st_rank7 = rank7.getText().toString();
                    String st_rank8 = rank8.getText().toString();
                    String st_rank9 = rank9.getText().toString();
                    String st_rank10 = rank10.getText().toString();
                    String st_upiid = upiid.getText().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference("freeSquad")
                            .child("firstactivity");


                    final Classic_Squad s1 = new Classic_Squad(st_matchno, st_date, st_time, st_registration, st_roomid, st_roomid
                            , st_password, st_prizepool, st_perkill, st_entryfee, st_type, st_mode, st_map, st_info, st_disabled,
                            st_paidentry, st_winner, st_rank1, st_rank2, st_rank3, st_rank4, st_rank5, st_rank6, st_rank7, st_rank8, st_rank9, st_rank10, st_upiid);
                    databaseReference.setValue(s1);


                    try {

                        Toast.makeText(LiteSquad_Activity.this, "Data Update", Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException nullPointerException) {
                        System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
                    }


                    if (!((Activity) LiteSquad_Activity.this).isFinishing()) {

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}
