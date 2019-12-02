package com.example.redwings;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Confirm_Order extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm__order_captain);
        Intent intent=getIntent();
        String type=intent.getStringExtra("Type");


        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        if (type.equals("Cafe")) {
            final DatabaseReference databaseReference1 = databaseReference.child("CAFE_ORDER");

            TextView tableOrPsNumber=findViewById(R.id.tableOrPsNumber);
            final TextView mealName=findViewById(R.id.mealNemaConfirmOrder);
            final EditText mealNumber=findViewById(R.id.mealNumber);
            final EditText notes=findViewById(R.id.notesConfirmOrder);
            Button confirm=findViewById(R.id.confirmOrderConfirmOrder);
            Button cancel=findViewById(R.id.cancelConfirmOrder);


            final String mealNameref=intent.getStringExtra("Meal");
            mealName.setText(mealNameref);
            int tablenumber= intent.getIntExtra("TABLE_NUMBER",0);
            final String text=type+" "+tablenumber;
            tableOrPsNumber.setText(text);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DatabaseReference databaseReference2=databaseReference1.child(text);
                    final DatabaseReference databaseReference3=databaseReference2.child("curendStatus");
                    final DatabaseReference databaseReference4=databaseReference2.child("orders");
                    final DatabaseReference databaseReference5=databaseReference4.child(mealNameref);
                    databaseReference5.setValue(mealNameref);
                    DatabaseReference databaseReference6=databaseReference5.child("theNumberOfMeal");
                    String mealnumber=mealNumber.getText().toString();
                    databaseReference6.setValue(mealnumber);
                    DatabaseReference databaseReference7=databaseReference5.child("Note");
                    String note=notes.getText().toString();
                    databaseReference7.setValue(note);
                    databaseReference3.setValue("true");
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        }else if(type.equals("Ps")){
            final DatabaseReference databaseReference1 = databaseReference.child("PS_ORDER");
            TextView tableOrPsNumber=findViewById(R.id.tableOrPsNumber);
            final TextView mealName=findViewById(R.id.mealNemaConfirmOrder);
            final EditText mealNumber=findViewById(R.id.mealNumber);
            final EditText notes=findViewById(R.id.notesConfirmOrder);
            Button confirm=findViewById(R.id.confirmOrderConfirmOrder);
            Button cancel=findViewById(R.id.cancelConfirmOrder);


            final String mealNameref=intent.getStringExtra("Meal");
            mealName.setText(mealNameref);
            int tablenumber= intent.getIntExtra("TABLE_NUMBER",0);
            final String text=type+" "+tablenumber;
            tableOrPsNumber.setText(text);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DatabaseReference databaseReference2=databaseReference1.child(text);
                    final DatabaseReference databaseReference3=databaseReference2.child("curendStatus");
                    final DatabaseReference databaseReference4=databaseReference2.child("orders");
                    final DatabaseReference databaseReference5=databaseReference4.child(mealNameref);
                    databaseReference5.setValue(mealNameref);
                    DatabaseReference databaseReference6=databaseReference5.child("theNumberOfMeal");
                    String mealnumber=mealNumber.getText().toString();
                    databaseReference6.setValue(mealnumber);
                    DatabaseReference databaseReference7=databaseReference5.child("Note");
                    String note=notes.getText().toString();
                    databaseReference7.setValue(note);
                    databaseReference3.setValue("true");
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        }else
        {
            final DatabaseReference databaseReference1 = databaseReference.child("TAKE_AWAY");
            final String usernameTakeAway=type;
            TextView username=findViewById(R.id.tableOrPsNumber);
            final TextView mealName=findViewById(R.id.mealNemaConfirmOrder);
            final EditText mealNumber=findViewById(R.id.mealNumber);
            final EditText notes=findViewById(R.id.notesConfirmOrder);
            Button confirm=findViewById(R.id.confirmOrderConfirmOrder);
            Button cancel=findViewById(R.id.cancelConfirmOrder);


            final String mealNameref=intent.getStringExtra("Meal");
            mealName.setText(mealNameref);

            username.setText(usernameTakeAway);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final DatabaseReference databaseReference2=databaseReference1.child(usernameTakeAway);
                    final DatabaseReference databaseReference4=databaseReference2.child("orders");
                    final DatabaseReference databaseReference5=databaseReference4.child(mealNameref);
                    databaseReference5.setValue(mealNameref);
                    DatabaseReference databaseReference6=databaseReference5.child("theNumberOfMeal");
                    String mealnumber=mealNumber.getText().toString();
                    databaseReference6.setValue(mealnumber);
                    DatabaseReference databaseReference7=databaseReference5.child("Note");
                    String note=notes.getText().toString();
                    databaseReference7.setValue(note);
                }
            });
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        }

    }
}
