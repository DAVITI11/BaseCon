package com.example.davaleba1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class InsertVals extends Fragment {
    EditText InptTbNm;
    TextView TbNm;
    Button SLBTN,GoToMainButton,InsertVals;
    ListView ListV;
    Pair<ArrayList<String>,ArrayList<String>> Columns;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_vals, container, false);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InptTbNm = view.findViewById(R.id.InptTbNm);
        SLBTN = view.findViewById(R.id.SLBTN);
        GoToMainButton = view.findViewById(R.id.GoToMainButton);
        InsertVals = view.findViewById(R.id.InsertVals);
        Columns = new Pair<>(new ArrayList<>(),new ArrayList<>());

        GoToMainButton.setOnClickListener(v->{
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Are You Sure You Want To Go To Main Activity?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        ((MainActivity)getActivity()).ChangeFragment(new FirstFrmg());
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
        TbNm = view.findViewById(R.id.TbNm);
        TbNm.setGravity(Gravity.CENTER);
        ListV = view.findViewById(R.id.ListV);
        SLBTN.setOnClickListener(v -> {
            if (!InptTbNm.getText().toString().isEmpty()) {
                Columns = ((MainActivity) getActivity()).getClmn(InptTbNm.getText().toString());
                if (Columns.first.isEmpty()) {
                    TbNm.setText("Table Not Found");
                    return;
                }
                TbNm.setText(InptTbNm.getText().toString());
                InptTbNm.setText("");
                ArrayList<String> ClN=Columns.first;
                ArrayList<String> ClTP=Columns.second;
                InstAdpt adpt = new InstAdpt(getActivity(),ClN,ClTP);
                ListV.setAdapter(adpt);
            }
        });
        InsertVals.setOnClickListener(v->{
            if(TbNm.getText().toString().isEmpty())return;
            if(TbNm.getText().toString().equals("Table Not Found"))return;

            ArrayList<String>Values = new ArrayList<>();
            for(int i=0;i<ListV.getChildCount();i++){
                EditText et = ListV.getChildAt(i).findViewById(R.id.ClmVal);
                Values.add(et.getText().toString());
                et.setText("");
            }
            ((MainActivity)getActivity()).InsertValues(TbNm.getText().toString(),Values);
            Toast.makeText(getActivity(),"Values Inserted",Toast.LENGTH_SHORT).show();
        });
    }
}
