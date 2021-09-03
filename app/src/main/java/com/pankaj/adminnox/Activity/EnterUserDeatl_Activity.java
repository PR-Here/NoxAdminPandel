package com.pankaj.adminnox.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pankaj.adminnox.ModeActivity.Scientist;
import com.pankaj.adminnox.R;

public class EnterUserDeatl_Activity extends AppCompatActivity {

    Spinner spin;
    String[] country = {"Select Mode", "Classic Solo", "Classic Duo", "Classic Squad", "Classic TDM", "Lite Solo", "Lite Duo", "Lite Squad", "Lite TDM"};
    String st_payment;
    EditText date, player, fee;
    DatePickerDialog.OnDateSetListener mdatelistener;
    Button confirm, view;
    private DatabaseReference db_match_detail;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_user_deatl_);
        firebaseAuth = FirebaseAuth.getInstance();
        db_match_detail = FirebaseDatabase.getInstance().getReference("Admin_Match_Deatil").child("detail");
        progressdialog = new ProgressDialog(this);
        progressdialog.setMessage("Loading Data ...");
        progressdialog.setCancelable(false);
        confirm = findViewById(R.id.confirm);
        view = findViewById(R.id.view);
        spin = findViewById(R.id.spinner);

        final ArrayAdapter aa = new ArrayAdapter(EnterUserDeatl_Activity.this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                st_payment = spin.getSelectedItem().toString();
                Toast.makeText(EnterUserDeatl_Activity.this, st_payment, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        player = findViewById(R.id.player);
        fee = findViewById(R.id.entryfee);
        date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(EnterUserDeatl_Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mdatelistener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mdatelistener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                Log.d("mytag", "onDateSet : dd-mm-yyy" + day + "-" + month + "-" + "-" + year);
                String date2 = day + "-" + month + "-" + year;
                date.setText(date2);
            }
        };

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (date.getText().toString().equals("") && player.getText().toString().equals("") && fee.getText().toString().equals("")){
                    Toast.makeText(EnterUserDeatl_Activity.this, "Field can not be Empty", Toast.LENGTH_SHORT).show();
                }else {

                }

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterUserDeatl_Activity.this, ViewResult_Activity.class);
                startActivity(intent);
            }
        });
    }

//    private void joinSquadPlayer() {
//
//        final String datee = date.getText().toString();
//        final String playerr = player.getText().toString();
//        final String enteryfee = fee.getText().toString();
//
//        final String type = st_payment;
//
//
//        firebaseAuth.createUserWithEmailAndPassword(st_payment, playerr)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        Scientist completeEntry = new Scientist(type, datee, playerr, enteryfee);
//                        db_match_detail.child(st_payment).setValue(completeEntry)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//
//                                        if (task.isSuccessful()) {
//                                            progressdialog.show();
//
//                                            Toast.makeText(EnterUserDeatl_Activity.this, "Player Join Successfully", Toast.LENGTH_SHORT).show();
//                                            progressdialog.dismiss();
//                                        }
//                                    }
//                                });
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(EnterUserDeatl_Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}
