package com.example.redwings;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Cafe_Button_par_man extends Fragment {


    ArrayList<NewAdapter> stringList;
    ListView listView;
    DatabaseReference databaseReference;
    public Cafe_Button_par_man() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_cafe__button_par_man, container, false);
        listView=view.findViewById(R.id.listviewCaffeButtonParMan);


/////////////////////////////////////////////////////////////

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stringList.clear();
                for (DataSnapshot menudataSnapshot : dataSnapshot.getChildren()) {

                    NewAdapter newMeal = menudataSnapshot.getValue(NewAdapter.class);
                    stringList.add(newMeal);

                }
                CafeeParManAdapter menuList = new CafeeParManAdapter(getActivity(), stringList);
                listView.setAdapter(menuList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
class CafeeParManAdapter extends ArrayAdapter<NewAdapter> {

    private List<NewAdapter> arrList;
    private Activity context;


    CafeeParManAdapter(Activity context, List<NewAdapter> arrList) {
        super(context, R.layout.item_design_make_order, arrList);
        this.context = context;
        this.arrList = arrList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.iten_desgin_par_man, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.cafeeee);

        NewAdapter newMeal = arrList.get(position);

        textViewName.setText(newMeal.getMealName());

        return listViewItem;
    }
}

class NewAdapter<mealName> {
    private String mealName;



    public NewAdapter() {
    }

    public NewAdapter (String mealName) {
        this.mealName = mealName;
    }



    public String getMealName() {
        return mealName;
    }



    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

  }
