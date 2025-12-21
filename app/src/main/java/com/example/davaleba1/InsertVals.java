package com.example.davaleba1;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class InsertVals extends Fragment {
    EditText InptTbNm;
    TextView TbNm;
    Button SLBTN,GoToMainButton;
    ListView ListV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_vals, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InptTbNm = view.findViewById(R.id.InptTbNm);
        SLBTN = view.findViewById(R.id.SLBTN);
        GoToMainButton = view.findViewById(R.id.GoToMainButton);
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
                ArrayList<String> Columns = ((MainActivity) getActivity()).getClmn(InptTbNm.getText().toString());
                if (Columns.isEmpty()) {
                    TbNm.setText("Table Not Found");
                    return;
                }
                InstAdpt adpt = new InstAdpt(getActivity(),Columns);
                ListV.setAdapter(adpt);
            }
        });
    }
}
