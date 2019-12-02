package com.example.redwings;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.TextView;

public class ParMan extends AppCompatActivity {
    Fragment fragment, fragment2,fragment3;
    FragmentTransaction fragmentTransaction, fragmentTransaction2,fragmentTransaction3;

    TextView cafe,takeAway,ps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_par_man);

        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);


        boolean taplet;
        taplet=isTablet(this);
        if (taplet){


        }else {
            cafe=findViewById(R.id.cafe_textview_par_man_fragment);
            takeAway=findViewById(R.id.takeaway_textview_par_man_fragment);
            ps=findViewById(R.id.ps_textview_par_man_fragment);

            fragment = new Cafe_Button_par_man();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frgment_par_man, fragment);
            fragmentTransaction.commit();

            cafe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment = new Cafe_Button_par_man();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frgment_par_man, fragment);
                    fragmentTransaction.commit();
                }
            });
            ps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment = new PS_Button_par_man();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frgment_par_man, fragment);
                    fragmentTransaction.commit();
                }
            });
            takeAway.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment = new Take_away_Button_par_man();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frgment_par_man, fragment);
                    fragmentTransaction.commit();
                }
            });
        }


    }
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.par_man_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out) {
            Intent intent=new Intent(ParMan.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("logout",false);
            startActivity(intent);
            ParMan.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
