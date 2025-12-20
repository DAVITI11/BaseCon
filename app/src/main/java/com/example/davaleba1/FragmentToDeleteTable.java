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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class FragmentToDeleteTable extends Fragment {
    ListView ListV;
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_delete_table, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListV = view.findViewById(R.id.ListV);
        ArrayList<String> Tables = ((MainActivity)getActivity()).SelectAllTable();
        ArrayAdapter<String>adpt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,Tables);
        ListV.setAdapter(adpt);


        ListV.setOnItemLongClickListener((parent, v, position, id) ->{

            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Are you sure you want to delete this table?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        ((MainActivity)getActivity()).DeleteTable(Tables.get(position));
                        Tables.remove(ListV.getItemAtPosition(position).toString());
                        adpt.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();

            return true;
        });


    }
}
