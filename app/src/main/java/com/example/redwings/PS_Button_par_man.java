package com.example.redwings;


import android.os.Bundle;
import android.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PS_Button_par_man extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    public PS_Button_par_man() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_ps__button_par_man, container, false);

        return view;
    }

}
