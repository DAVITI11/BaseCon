package com.example.davaleba1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class ShowTBL extends Fragment {
    ArrayList<String>TbNm;
    ArrayList<String>MainList;
    Button GoToMain;
    ListView ListV;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.showtablemainlt, container, false);
        return view;
    }
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListV = view.findViewById(R.id.ListV);
        GoToMain = view.findViewById(R.id.GoToMainButton);

        MainList = new ArrayList<>();
        TbNm = ((MainActivity)getActivity()).SelectAllTable();

        for(int k = 0; k < TbNm.size(); k++) {
            int temp=k+1;
            MainList.add("Table  N : " + temp);
            MainList.add(TbNm.get(k));
            ArrayList<String> Columns = ((MainActivity)getActivity()).GetColumns(TbNm.get(k));
            for(int i = 0; i < Columns.size(); i++) {
                MainList.add("    --->    " + Columns.get(i));
            }
            if(k!=TbNm.size()-1)
                MainList.add("");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, MainList);
        ListV.setAdapter(adapter);
        GoToMain.setOnClickListener(v->{
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Are you sure you want to go to main page?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        ((MainActivity)getActivity()).ChangeFragment(new FirstFrmg());
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
                    });
    }
}
