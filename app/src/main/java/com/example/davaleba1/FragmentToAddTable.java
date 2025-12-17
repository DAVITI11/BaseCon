package com.example.davaleba1;

import android.os.Bundle;
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

public class FragmentToAddTable extends Fragment {
    Button GoToMain;
    Button AddTableName;
    Button AddColumn;
    EditText TableNameInput;
    EditText ColumnNameInput;

    TextView TableName;
    TextView ColmnNm;
    TextView ColumnType;
    ListView ColumnList;
    ListView ColumnTpList;

    ArrayList<String> ColumnsName = new ArrayList<>();
    ArrayList<String> ColumnsType = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgmlayout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GoToMain = view.findViewById(R.id.GoToMainButton);
        AddTableName = view.findViewById(R.id.AddTableName);
        AddColumn = view.findViewById(R.id.AddColumn);
        TableNameInput = view.findViewById(R.id.TableNameInput);
        ColumnNameInput = view.findViewById(R.id.ColumnName);
        TableName = view.findViewById(R.id.TableName);
        ColmnNm = view.findViewById(R.id.ColmnNm);
        ColumnType = view.findViewById(R.id.ColmnTp);
        ColumnList = view.findViewById(R.id.ColumnList);
        ColumnTpList = view.findViewById(R.id.ColumnTpList);

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
        AddTableName.setOnClickListener(v->{
            if(!TableNameInput.getText().toString().isEmpty()){
                TableName.setText("Table Name is : " + TableNameInput.getText().toString());
                TableNameInput.setText("");
            }
        });
        AddColumn.setOnClickListener(v->{
            if(!ColumnNameInput.getText().toString().isEmpty()){
                ColmnNm.setText("Column Name");
                ColumnType.setText("Column Type");
                ColumnsName.add(ColumnNameInput.getText().toString());
                ColumnsType.add("Text");
                SetAdapter();
                ColumnNameInput.setText("");
            }
        });
        ColumnList.setOnItemLongClickListener((parent, v, position, id) -> {
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Are you sure you want to delete this column?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        ColumnsName.remove(position);
                        ColumnsType.remove(position);
                        SetAdapter();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();


            return true;
        });
    }
    void SetAdapter(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ColumnsName);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, ColumnsType);
        ColumnList.setAdapter(adapter);
        ColumnTpList.setAdapter(adapter2);
    }
}
