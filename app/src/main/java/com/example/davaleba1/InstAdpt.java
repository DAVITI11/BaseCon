package com.example.davaleba1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InstAdpt extends BaseAdapter {
    Context context;
    ArrayList<String> ColumnsName = new ArrayList<>();
    ArrayList<String> ColumnsType = new ArrayList<>();
    ArrayList<String>Itm;
    public InstAdpt(Context context, ArrayList<String> clmnNm, ArrayList<String> clmnTp){
        this.context = context;
        this.ColumnsName = clmnNm;
        this.ColumnsType = clmnTp;
        Itm = new ArrayList<>();
        for(int i=0;i<ColumnsName.size();i++){
            Itm.add("");
        }
    }
    @Override
    public int getCount() {
        return ColumnsName.size();
    }
    @Override
    public Object getItem(int position) {
        return ColumnsName.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.insert_vals2, parent, false);
        }
        TextView ClmNm = row.findViewById(R.id.ClmNm);
        EditText clmVal = row.findViewById(R.id.ClmVal);
        ClmNm.setText(ColumnsName.get(position));
        clmVal.setHint(ColumnsType.get(position));
        clmVal.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void afterTextChanged(android.text.Editable s) {
                String value = s.toString().trim();

                Itm.add(position,value);

            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        return row;
    }
    public ArrayList<String> getItems() {
        return Itm;
    }
}
