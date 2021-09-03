package com.pankaj.adminnox.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pankaj.adminnox.JavaClass.All_Detail_signup;
import com.pankaj.adminnox.R;

public class AllUserDetail_Activity extends AppCompatActivity {

    EditText name, pubgname, mobileno, emailid, lastname, pubgid, address, date, coins;

    private DatabaseReference databaseReference;
    String phoneno;

    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_detail_);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.whiteall), PorterDuff.Mode.SRC_ATOP);
        name = findViewById(R.id.name);
        pubgname = findViewById(R.id.pubgname);
        mobileno = findViewById(R.id.mobileno);
        emailid = findViewById(R.id.emailid);
        lastname = findViewById(R.id.lastname);
        pubgid = findViewById(R.id.pubgid);
        address = findViewById(R.id.address);
        date = findViewById(R.id.date);
        coins = findViewById(R.id.coins);

        update = findViewById(R.id.update_btn);

        phoneno = getIntent().getStringExtra("phoneno");

        databaseReference = FirebaseDatabase.getInstance().getReference("signup_user")
                .child("every_signup_user").child(phoneno);

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
                            String st_name = dataSnapshot.child("name").getValue(String.class);
                            String st_emailid = dataSnapshot.child("email").getValue(String.class);
                            String st_pubgname = dataSnapshot.child("pubgname").getValue(String.class);
                            String st_pubgid = dataSnapshot.child("pubgid").getValue(String.class);
                            String st_mobileno = dataSnapshot.child("mobileno").getValue(String.class);

                            String st_address = dataSnapshot.child("address").getValue(String.class);
                            String st_coins = dataSnapshot.child("coins").getValue(String.class);
                            String st_lastname = dataSnapshot.child("lastname").getValue(String.class);
                            String st_date = dataSnapshot.child("date").getValue(String.class);


                            name.setText(st_name);
                            pubgname.setText(st_pubgname);
                            mobileno.setText(st_mobileno);
                            emailid.setText(st_emailid);
                            pubgid.setText(st_pubgid);
                            address.setText(st_address);
                            coins.setText(st_coins);
                            lastname.setText(st_lastname);
                            date.setText(st_date);
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

                    String st_name = name.getText().toString();
                    String st_lname = lastname.getText().toString();
                    String st_pubgname = pubgname.getText().toString();
                    String st_pubgid = pubgid.getText().toString();
                    String st_email = emailid.getText().toString();
                    String st_mobile = mobileno.getText().toString();
                    String st_add = address.getText().toString();
                    String st_date = date.getText().toString();
                    String st_coins = coins.getText().toString();
                    databaseReference = FirebaseDatabase.getInstance().getReference("signup_user")
                            .child("every_signup_user").child(phoneno);


                    final All_Detail_signup s1 = new All_Detail_signup(st_add, st_coins, st_email, st_mobile, st_name, st_pubgid, st_pubgname, st_date, st_lname);
                    databaseReference.setValue(s1);


                    try {
                        AllUserDetail_Activity.super.onBackPressed();
                        Toast.makeText(AllUserDetail_Activity.this, "update successfull", Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException nullPointerException) {
                        System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
                    }


                    if (!((Activity) AllUserDetail_Activity.this).isFinishing()) {

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
