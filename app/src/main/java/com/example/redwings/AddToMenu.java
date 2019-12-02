package com.example.redwings;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddToMenu extends AppCompatActivity {


    List<NewMeal>menulist;
    EditText editText_add_meal;
    EditText editText_add_price;
    Button button_add_data;
    Button button_cancel;
    ListView listView_manage_menu;

    DatabaseReference databaseReferenceAddMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_to_menu);


        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);


        editText_add_meal=findViewById(R.id.editTextAddMeal);
        editText_add_price=findViewById(R.id.editTextAddPrice);
        button_add_data=findViewById(R.id.buttonAddMenu);
        button_cancel=findViewById(R.id.buttonCancelMenu);
        listView_manage_menu=findViewById(R.id.listViewMangeMenu);


        databaseReferenceAddMenu= FirebaseDatabase.getInstance().getReference("NewMeal");
        FirebaseApp.initializeApp(this);

        menulist=new ArrayList<>();

        listView_manage_menu.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final NewMeal newMeal =menulist.get(i);
                final String itemKey=newMeal.getMealId();
                final DatabaseReference databaseReferenceDeleteItem = FirebaseDatabase.getInstance().getReference();
                databaseReferenceDeleteItem.child("NewMeal").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        databaseReferenceDeleteItem.child("NewMeal").child(itemKey).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
                return false;
            }
        });

        button_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeal();
            }
        });



        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_add_price.setText("");
                editText_add_meal.setText("");
                finish();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReferenceAddMenu.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menulist.clear();
                for (DataSnapshot menudataSnapshot : dataSnapshot.getChildren()){

                    NewMeal newMeal=menudataSnapshot.getValue(NewMeal.class);
                    menulist.add(newMeal);

                }
                MenuList menuList=new MenuList(AddToMenu.this,menulist);
                listView_manage_menu.setAdapter(menuList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addMeal(){
        String name=editText_add_meal.getText().toString();
        String price=editText_add_price.getText().toString();
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(price)){
            String id=databaseReferenceAddMenu.push().getKey();
            NewMeal newMeal=new NewMeal(id,name,price);
            databaseReferenceAddMenu.child(id).setValue(newMeal);
            editText_add_price.setText("");
            editText_add_meal.setText("");
        }else {
            Toast.makeText(this, R.string.please_add_data, Toast.LENGTH_SHORT).show();
        }
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
            startActivity(new Intent(AddToMenu.this,MainActivity.class));
        }
        else if(id==R.id.cafe_menu_pc_action_par){
            startActivity(new Intent(AddToMenu.this,Cafe_Table.class));
        }
        else if(id==R.id.manage_menu){
            startActivity(new Intent(AddToMenu.this,AddToMenu.class));
        }else if(id==R.id.log_out){
            Intent intent=new Intent(AddToMenu.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("logout",false);
            startActivity(intent);
            AddToMenu.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
 class MenuList extends ArrayAdapter<NewMeal> {

    private List<NewMeal> arrList;
    private Activity context;


    MenuList(Activity context, List<NewMeal> arrList) {
        super(context, R.layout.item_design_menu_table,arrList);
        this.context = context;
        this.arrList = arrList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.item_design_menu_table,null,true);

        TextView textViewName=listViewItem.findViewById(R.id.textviewname_item_designmenu_table);
        TextView textViewPrice=listViewItem.findViewById(R.id.textviewprice_item_designmenu_table);

        NewMeal newMeal=arrList.get(position);

        textViewName.setText(newMeal.getMealName());
        textViewPrice.setText(newMeal.getMealPrice());
        return listViewItem;    }
}

 class NewMeal {
    private String mealName;
    private String mealPrice;
    private String mealId;


    public NewMeal() {
    }

    public NewMeal(String mealId, String mealName, String mealPrice) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    public String getMealId() {
        return mealId;
    }


    public String getMealName() {
        return mealName;
    }


    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
