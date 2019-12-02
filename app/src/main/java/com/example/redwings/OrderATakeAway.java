package com.example.redwings;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class OrderATakeAway extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_atake_away);
        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);
        final EditText userName=findViewById(R.id.takeawayusername);
        Button makeorder=findViewById(R.id.makeOrder_takeAway);

        makeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameString=userName.getText().toString();
                Intent intent=new Intent(OrderATakeAway.this,MakeOrder.class);
                intent.putExtra("PsOrCafe",usernameString);
                startActivity(intent);

            }
        });
    }
}
