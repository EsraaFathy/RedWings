package com.example.redwings;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Manager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_manager);
        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manager_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.ps_menu_manager){
            startActivity(new Intent(Manager.this,PC_Table.class));
        }
        else if(id==R.id.cafe_menu_pc_action_par){
            startActivity(new Intent(Manager.this,Cafe_Table.class));
        }
        else if(id==R.id.manage_menu){
            startActivity(new Intent(Manager.this,AddToMenu.class));
        }else if(id==R.id.log_out){
            Intent intent=new Intent(Manager.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("logout",false);
            startActivity(intent);
            Manager.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

