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
import com.pankaj.adminnox.JavaClass.Classic_duo;
import com.pankaj.adminnox.JavaClass.Lite_Tdm;
import com.pankaj.adminnox.R;

public class LiteTdm_Activity extends AppCompatActivity {
    EditText matchno, date, time, registration, roomid, password, prizepool, perkill, entryfee, type,
            mode, map, information, disabled, paidentry, winner, rank1, rank2, rank3, rank4, rank5, upiid;
    Button update;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_tdm_);

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
        upiid = findViewById(R.id.upiid);
        upiid.setText("ranap8445@oksbi");


        update = findViewById(R.id.btn_update);
        databaseReference = FirebaseDatabase.getInstance().getReference("freeTdm")
                .child("fouractivity");

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
                            String st_upiid = dataSnapshot.child("upiid").getValue(String.class);

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

                    String st_upiid=upiid.getText().toString();

                    databaseReference = FirebaseDatabase.getInstance().getReference("freeTdm")
                            .child("fouractivity");


                    final Lite_Tdm s1 = new Lite_Tdm(st_matchno, st_date, st_time, st_registration, st_roomid
                            , st_password, st_prizepool, st_perkill, st_entryfee, st_type, st_mode,st_map,st_info, st_disabled,
                            st_paidentry, st_winner, st_rank1);

                    databaseReference.setValue(s1);


                    try {

                        Toast.makeText(LiteTdm_Activity.this, "Data Update", Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException nullPointerException) {
                        System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
                    }


                    if (!((Activity) LiteTdm_Activity.this).isFinishing()) {

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
