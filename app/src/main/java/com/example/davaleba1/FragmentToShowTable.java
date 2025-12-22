package com.example.davaleba1;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class FragmentToShowTable extends Fragment {
    TextView TbNm;
    EditText TableNameInput;
    Button SLBTN;
    Button GoToMainButton;
    ListView ListV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_table, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TbNm = view.findViewById(R.id.TableNm);
        TbNm.setGravity(Gravity.CENTER);
        GoToMainButton = view.findViewById(R.id.GoToMainButton);
        ListV = view.findViewById(R.id.ListV);
        TableNameInput = view.findViewById(R.id.TableNameInput);
        SLBTN = view.findViewById(R.id.SLBTN);
        SLBTN.setOnClickListener(v->{
            if(!TableNameInput.getText().toString().isEmpty()){
                if(((MainActivity)getActivity()).CheckTable(TableNameInput.getText().toString())) {
                    ArrayList<String> Result = ((MainActivity) getActivity()).SelectTable(TableNameInput.getText().toString());
//                if(Result.isEmpty()){
//                    new MaterialAlertDialogBuilder(requireContext())
//                            .setTitle("Warning")
//                            .setMessage("Table Not Found")
//                            .setPositiveButton("Ok", (dialog, which) -> {
//                                dialog.dismiss();
//                            })
//                            .show();
//                    return;
//                }
                    ArrayAdapter<String> adpt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, Result);
                    ListV.setAdapter(adpt);
                    TbNm.setText(TableNameInput.getText().toString());
                    TableNameInput.setText("");
                }
            }
        });
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

    }
}
