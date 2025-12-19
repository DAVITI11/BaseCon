package com.example.davaleba1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ShowTBL extends Fragment {
    ArrayList<String>TbNm;

    ListView ListV;
    DataBaseCon db = new DataBaseCon(getActivity(),"DataBase.db",null,1);

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showtablemainlt, container, false);
        return view;
    }
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListV = view.findViewById(R.id.ListV);
        TbNm = db.TablesName();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TbNm);
        ListV.setAdapter(adapter);
    }
}
