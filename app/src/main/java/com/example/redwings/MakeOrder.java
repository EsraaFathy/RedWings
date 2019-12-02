package com.example.redwings;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MakeOrder extends AppCompatActivity {

    ListView listView_manage_menu;
    ArrayList<String> arrayListOrdersIDs = new ArrayList<>();
    DatabaseReference databaseReferenceAddMenu;
    String PsOrCafe;
    List<NewMealMakeOrder> menulist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_make_order);
        Toolbar toolbar = findViewById(R.id.action_par);
        setSupportActionBar(toolbar);
        listView_manage_menu = findViewById(R.id.makeOrderListView);
        Button button = findViewById(R.id.buttonMakeOrder);

        Intent intent=getIntent();
        PsOrCafe=intent.getStringExtra("PsOrCafe");
        final int TableNumber=intent.getIntExtra("TableNumber",0)+1;


        databaseReferenceAddMenu = FirebaseDatabase.getInstance().getReference("NewMeal");
        FirebaseApp.initializeApp(this);

        menulist = new ArrayList<>();

        listView_manage_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final NewMealMakeOrder newMeal = menulist.get(i);
                final String itemSelseckted = newMeal.getMealName();
                Intent intent = new Intent(MakeOrder.this,Confirm_Order.class);
                intent.putExtra("TABLE_NUMBER",TableNumber);
                intent.putExtra("Meal",itemSelseckted);
                intent.putExtra("Type",PsOrCafe);
                startActivity(intent);

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
                for (DataSnapshot menudataSnapshot : dataSnapshot.getChildren()) {

                    NewMealMakeOrder newMeal = menudataSnapshot.getValue(NewMealMakeOrder.class);
                    menulist.add(newMeal);

                }
                MenuListMakeOrdeer menuList = new MenuListMakeOrdeer(MakeOrder.this, menulist);
                listView_manage_menu.setAdapter(menuList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chose_catogry_captain_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.log_out) {
            Intent intent = new Intent(MakeOrder.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("logout", false);
            startActivity(intent);
            MakeOrder.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

class MenuListMakeOrdeer extends ArrayAdapter<NewMealMakeOrder> {

    private List<NewMealMakeOrder> arrList;
    private Activity context;


    MenuListMakeOrdeer(Activity context, List<NewMealMakeOrder> arrList) {
        super(context, R.layout.item_design_make_order, arrList);
        this.context = context;
        this.arrList = arrList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.item_design_make_order, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textviewname_item_designmenu_table);
        TextView textViewPrice = listViewItem.findViewById(R.id.textviewprice_item_designmenu_table);

        NewMealMakeOrder newMeal = arrList.get(position);

        textViewName.setText(newMeal.getMealName());
        textViewPrice.setText(newMeal.getMealPrice());
        return listViewItem;
    }
}

class NewMealMakeOrder {
    private String mealName;
    private String mealPrice;
    private String mealId;


    public NewMealMakeOrder() {
    }

    public NewMealMakeOrder(String mealId, String mealName, String mealPrice) {
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

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
}
