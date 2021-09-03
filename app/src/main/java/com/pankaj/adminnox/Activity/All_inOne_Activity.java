package com.pankaj.adminnox.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pankaj.adminnox.Adapter.FragmentAdapter;
import com.pankaj.adminnox.JavaClass.Users;
import com.pankaj.adminnox.ModeActivity.ClassicArcade_Activity;
import com.pankaj.adminnox.ModeActivity.ClassicDuo_Activity;
import com.pankaj.adminnox.ModeActivity.ClassicSolo_Activity;
import com.pankaj.adminnox.ModeActivity.ClassicSquad_Activity;
import com.pankaj.adminnox.ModeActivity.ClassicTdm_Activity;
import com.pankaj.adminnox.ModeActivity.LiteDuo_Activity;
import com.pankaj.adminnox.ModeActivity.LiteSolo_Activity;
import com.pankaj.adminnox.ModeActivity.LiteSquad_Activity;
import com.pankaj.adminnox.ModeActivity.LiteTdm_Activity;
import com.pankaj.adminnox.R;

public class All_inOne_Activity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    LinearLayout linearLayout;
    private DatabaseReference Post;
    ImageView search;
    TextView Solo_txt, Duo_txt, Squad_Txt, Clash_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_in_one_);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.whiteall), PorterDuff.Mode.SRC_ATOP);
        Post = FirebaseDatabase.getInstance().getReference().child("mainactivity");

        Solo_txt = findViewById(R.id.solo_txt);
        Duo_txt = findViewById(R.id.duo_txt);
        Squad_Txt = findViewById(R.id.squad_txt);
        Clash_txt = findViewById(R.id.clash_txt);
        openActivity();
        linearLayout = findViewById(R.id.all_layout);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(All_inOne_Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

             tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.addTab(tabLayout.newTab().setText("Classic Squad"));
        tabLayout.addTab(tabLayout.newTab().setText("Classic Duo"));
        tabLayout.addTab(tabLayout.newTab().setText("Classic Solo"));
        tabLayout.addTab(tabLayout.newTab().setText("Classic Arcade"));
//        tabLayout.addTab(tabLayout.newTab().setText("Squad Lite"));
//        tabLayout.addTab(tabLayout.newTab().setText("Duo Lite"));
//        tabLayout.addTab(tabLayout.newTab().setText("Solo Lite"));

//        tabLayout.addTab(tabLayout.newTab().setText("FreeFire Solo"));
//        tabLayout.addTab(tabLayout.newTab().setText("FreeFire  Duo"));
//        tabLayout.addTab(tabLayout.newTab().setText("FreeFire Squad"));
//        tabLayout.addTab(tabLayout.newTab().setText("FreeFire Clash"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final FragmentAdapter adapter = new FragmentAdapter(All_inOne_Activity.this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        readRealTime();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {

        AuthUI.getInstance().signOut(All_inOne_Activity.this);

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "press again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        MenuItem classicsolo = menu.findItem(R.id.classicsolo);
        MenuItem classicduo = menu.findItem(R.id.classicduo);
        MenuItem classicsquad = menu.findItem(R.id.classicsquad);
        MenuItem classicarcade = menu.findItem(R.id.classicarcade);
        MenuItem classictdm = menu.findItem(R.id.classictdm);
        MenuItem litesolo = menu.findItem(R.id.litesolo);
        MenuItem liteduo = menu.findItem(R.id.liteduo);
        MenuItem litesquad = menu.findItem(R.id.litesquad);
        MenuItem litetdm = menu.findItem(R.id.litetdm);
        MenuItem appbarsearch = menu.findItem(R.id.app_bar_search);

        classicsolo.setVisible(false);
        classicduo.setVisible(false);
        classicsquad.setVisible(false);
        classicarcade.setVisible(false);
        classictdm.setVisible(false);
        appbarsearch.setVisible(false);

        appbarsearch.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                return true;
            }
        });


        classicsolo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, ClassicSolo_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        classicduo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, ClassicDuo_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        classicsquad.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, ClassicSquad_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        classicarcade.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, ClassicArcade_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        classictdm.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, ClassicTdm_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        litesolo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteSolo_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        liteduo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteDuo_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        litesquad.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteSquad_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        litetdm.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteTdm_Activity.class);
                startActivity(intent);


                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void readRealTime() {

        Post
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String adminview = dataSnapshot.child("adminview").getValue(String.class);

                        if (!adminview.isEmpty()) {
                            linearLayout.setVisibility(View.VISIBLE);
                        } else {
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(All_inOne_Activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void openActivity(){

        Solo_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteSolo_Activity.class);
                startActivity(intent);
            }
        });

        Duo_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteDuo_Activity.class);
                startActivity(intent);
            }
        });

        Squad_Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteSquad_Activity.class);
                startActivity(intent);
            }
        });

        Clash_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(All_inOne_Activity.this, LiteTdm_Activity.class);
                startActivity(intent);
            }
        });
    }
}
