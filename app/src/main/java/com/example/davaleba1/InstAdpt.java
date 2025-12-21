package com.example.davaleba1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InstAdpt extends BaseAdapter {
    Context context;
    ArrayList<String> ColumnsName = new ArrayList<>();
    public InstAdpt(Context context, ArrayList<String> clmnNm){
        this.context = context;
        this.ColumnsName = clmnNm;
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.insert_vals2, parent, false);

        TextView ClmNm = row.findViewById(R.id.ClmNm);
        TextView ClmVal = row.findViewById(R.id.ClmVal);
        ClmNm.setText(ColumnsName.get(position));
        ClmVal.setHint(ColumnsName.get(position));
        return row;
    }

}
