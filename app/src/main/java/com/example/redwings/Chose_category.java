package com.example.redwings;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Chose_category extends AppCompatActivity {

    LinearLayout linearLayout_cafe,linearLayout_pc,linearLayout_take_away;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chose_category_captain);

        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);

        linearLayout_cafe=findViewById(R.id.layout_cafe_chose_catogry);
        linearLayout_pc=findViewById(R.id.layout_pc_chose_catogry);
        linearLayout_take_away=findViewById(R.id.layout_takeaway_chose_catogry);
        linearLayout_take_away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Chose_category.this,OrderATakeAway.class);
                startActivity(intent);
            }
        });


        linearLayout_pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Chose_category.this,PC_Table.class));
            }
        });

        linearLayout_cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Chose_category.this,Cafe_Table.class));


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chose_catogry_captain_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.log_out){
            Intent intent=new Intent(Chose_category.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("logout",false);
            startActivity(intent);
            Chose_category.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
