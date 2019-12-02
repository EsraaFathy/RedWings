package com.example.redwings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextPassword;
    Button buttonLogIn;
    CheckBox mcheckBoxLogin;

    String aname="";
    String apassword="";
    Boolean aBoolean=false;
    Boolean log_out=true;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        buttonLogIn = findViewById(R.id.buttonLoginLogin);
        editTextUserName = findViewById(R.id.editTextUserNameLogIn);
        editTextPassword = findViewById(R.id.editTextPasswordLogIn);
        mcheckBoxLogin =findViewById(R.id.checkbboxLogin);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        log_out= getIntent().getBooleanExtra("logout",true);
        if (log_out==true) {
            checkLog();
            if (aBoolean == true) {
                if (editTextUserName.getText().toString().equals("") && editTextPassword.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Log in ", Toast.LENGTH_SHORT).show();
                }
                if (editTextUserName.getText().toString().equals("parman") && editTextPassword.getText().toString().equals("parman")) {
                    log_out=false;
                    startActivity(new Intent(MainActivity.this, ParMan.class));
                    this.finish();
                }
                if (editTextUserName.getText().toString().equals("captain") && editTextPassword.getText().toString().equals("captain")) {
                    log_out=false;
                    startActivity(new Intent(MainActivity.this, Chose_category.class));
                    this.finish();
                }
                if (editTextUserName.getText().toString().equals("manager") && editTextPassword.getText().toString().equals("manager123")) {
                    log_out=false;
                    startActivity(new Intent(MainActivity.this, Manager.class));
                    this.finish();
                }


            }
        }else{
            Intent intent=new Intent(MainActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            editor.putString("name","");
            editor.commit();


            editor.putString("password","");
            editor.commit();
        }

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(mcheckBoxLogin.isChecked()){
                    editor.putString("check","true");
                    editor.commit();

                    String name=editTextUserName.getText().toString();
                    editor.putString("name",name);
                    editor.commit();


                    String password=editTextPassword.getText().toString();
                    editor.putString("password",password);
                    editor.commit();
                }else{
                    editor.putString("check","false");
                    editor.commit();

                    editor.putString("name","");
                    editor.commit();


                    editor.putString("password","");
                    editor.commit();
                }
                if (editTextUserName.getText().toString().equals("parman") && editTextPassword.getText().toString().equals("parman")) {
                    log_out=false;
                    startActivity(new Intent(MainActivity.this, ParMan.class));
                    finish();
                }
                if (editTextUserName.getText().toString().equals("captain") && editTextPassword.getText().toString().equals("captain")) {
                    log_out=false;
                    startActivity(new Intent(MainActivity.this, Chose_category.class));
                    finish();
                }
                if (editTextUserName.getText().toString().equals("manager") && editTextPassword.getText().toString().equals("manager123")) {
                    log_out=false;
                    startActivity(new Intent(MainActivity.this, Manager.class));
                    finish();
                }

            }
        });


    }

    public void checkLog() {
        String checkLog = sharedPreferences.getString("check", "false");
        String name= sharedPreferences.getString("name","");
        String password=sharedPreferences.getString("password","");
        aname=name;
        apassword=password;
        aBoolean=true;
        editTextUserName.setText(name);
        editTextPassword.setText(password);

        if (checkLog.equals(true)){
            mcheckBoxLogin.setChecked(true);
        }else {
            mcheckBoxLogin.setChecked(false);
        }

    }


}