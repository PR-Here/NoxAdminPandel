package com.pankaj.adminnox.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pankaj.adminnox.R;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText userid, password;
    Button verify;
    private static final String TAG = "LoginRegisterActivity";
    int AUTHUI_REQUEST_CODE = 10001;
    private DatabaseReference Post;
    String st_password;
    String st_mobileno;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Post = FirebaseDatabase.getInstance().getReference().child("classicmode");
        upiId();
        databaseReference = FirebaseDatabase.getInstance().getReference("signup_user").child("every_signup_user");


        userid = findViewById(R.id.edt_id);
        password = findViewById(R.id.password);

        verify = findViewById(R.id.verify_btn);

        sharedPrefrence();


        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                st_mobileno = userid.getText().toString();

                databaseReference.child(st_mobileno).addListenerForSingleValueEvent(valueEventListener);


//                Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, All_inOne_Activity.class);
//                startActivity(intent);
//                finish();


            }
        });


    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                final String mobileno = dataSnapshot.child("mobileno").getValue(String.class);

                if (mobileno.equals(st_mobileno)) {

                    SharedPreferences sharedPreferences = getSharedPreferences("signup", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("number", st_mobileno);
                    editor.apply();

                    Intent intent = new Intent(MainActivity.this, All_inOne_Activity.class);
                    intent.putExtra("number2", userid.getText().toString());
                    startActivity(intent);
                    finish();


                }
            } else {
                Toast.makeText(MainActivity.this, "No User Found ! please signup ", Toast.LENGTH_LONG).show();

            }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

    };

    public void shared() {
        SharedPreferences prefs = getSharedPreferences("com.mobisoftseo.myapplication_login", MODE_PRIVATE);
        String cheak = prefs.getString("login status", "off");
        if (cheak.equals("on")) {
            startActivity(new Intent(MainActivity.this, All_inOne_Activity.class));
            finish();
        } else {
            Toast.makeText(this, "please login first", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    private void sharedPrefrence() {
        String st_no = password.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number", st_no);
        editor.apply();

    }

    private void upiId() {

        Post.child("firstactivity")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        st_password = dataSnapshot.child("password").getValue(String.class);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
