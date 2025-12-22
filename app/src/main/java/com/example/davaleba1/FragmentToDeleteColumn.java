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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentToDeleteColumn extends Fragment {
    EditText InptTbNm;
    TextView TBNM;
    Button SLBTN,GoToMain;
    ListView ListV;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_delete_column, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InptTbNm = view.findViewById(R.id.InptTbNm);
        SLBTN = view.findViewById(R.id.SLBTN);
        TBNM = view.findViewById(R.id.TbNm);
        ListV = view.findViewById(R.id.ListV);
        GoToMain = view.findViewById(R.id.GoToMainButton);

        SLBTN.setOnClickListener(v->{
            String TbNm = InptTbNm.getText().toString();
            if(TbNm.isEmpty()){
                Toast.makeText(getActivity(),"Enter Table Name",Toast.LENGTH_SHORT).show();
                return;
            }
     //       if(((MainActivity)getActivity()).CheckTable(TbNm)){
                TBNM.setText(TbNm);
                ArrayList<String> Columns = ((MainActivity)getActivity()).GetColumns(TbNm);
                ArrayAdapter<String>adpt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,Columns);
                ListV.setAdapter(adpt);
       //     }
        });

        ListV.setOnItemLongClickListener( (parent, v, position, id) ->{
            String ColumnName = ListV.getItemAtPosition(position).toString();
            ((MainActivity)getActivity()).DeleteColumn(TBNM.getText().toString(),ColumnName);
            ArrayList<String> Columns = ((MainActivity)getActivity()).GetColumns(TBNM.getText().toString());
            ArrayAdapter<String>adpt = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,Columns);
            ListV.setAdapter(adpt);
            return true;
        });
        GoToMain.setOnClickListener(v->{
            ((MainActivity)getActivity()).ChangeFragment(new FirstFrmg());
        });

    }
}
