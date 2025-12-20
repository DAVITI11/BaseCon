package com.example.davaleba1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import android.content.Context;
public class FragmentToAddTable extends Fragment {
    Button GoToMain,AddTableName,CrtTbl,Ref,AddColumn;
    EditText TableNameInput,ColumnNameInput;
    TextView TableName,ColumnType,ColmnNm;
    ListView ListV;
    Spinner spinner;

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
        ListV = view.findViewById(R.id.ListV);
        CrtTbl = view.findViewById(R.id.CrtTbl);
        Ref = view.findViewById(R.id.RefButton);
        spinner = view.findViewById(R.id.spinner);

        String[] ItemOfSpinner = new String[]{"Select Type","INTEGER PRIMARY KEY AUTOINCREMENT","TEXT","INTEGER","SMALLINT","BOOLEAN","REAL"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, ItemOfSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(0);
        spinner.setAdapter(adapter);

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
                TableName.setText(TableNameInput.getText().toString());
                TableNameInput.setText("");
            }
        });
        AddColumn.setOnClickListener(v->{
            if(!ColumnNameInput.getText().toString().isEmpty() && spinner.getSelectedItemPosition()!=0){
                ColmnNm.setText("Column Name");ColumnType.setText("Column Type");
                ColumnsName.add(ColumnNameInput.getText().toString());
                ColumnsType.add(spinner.getSelectedItem().toString());
                spinner.setSelection(0);
                ColumnNameInput.setText("");
                SetAdapter();
            }
        });
        ListV.setOnItemLongClickListener((parent, v, position, id) -> {
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
        CrtTbl.setOnClickListener(v->{
            if(!ColumnsName.isEmpty() && !TableName.getText().toString().isEmpty()){
                CreateTable(TableName.getText().toString(),ColumnsName,ColumnsType);
                Toast.makeText(getActivity(), "Create Table Successful", Toast.LENGTH_SHORT).show();
                CrtTbl.setEnabled(false);
            }else{
                Toast.makeText(getActivity(), "Fill All Fields", Toast.LENGTH_SHORT).show();
            }
        });
        Ref.setOnClickListener(v->{
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Warning")
                    .setMessage("Are you sure you want to refresh?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        CrtTbl.setEnabled(true);ColumnsName.clear();
                        ColumnsType.clear();TableName.setText("");
                        ColmnNm.setText("");ColumnType.setText("");
                        ColumnNameInput.setText("");TableNameInput.setText("");
                        spinner.setSelection(0);
                        SetAdapter();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
    }
    void SetAdapter(){
        TableRowAdpt adpt = new TableRowAdpt(getActivity(),ColumnsName,ColumnsType);
        ListV.setAdapter(adpt);
    }
    public void CreateTable(String TBNM,ArrayList<String>Clmn,ArrayList<String>ClmnTp){
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sql.append(TBNM).append("(");
        for(int i=0;i<Clmn.size();i++){
            sql.append(Clmn.get(i)).append(" ").append(ClmnTp.get(i));
            if(i!=Clmn.size()-1){
                sql.append(",");
            }
        }
        sql.append(")");
        ((MainActivity)getActivity()).CreateTable(sql);
    }
}
