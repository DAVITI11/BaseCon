package com.example.davaleba1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TableRowAdpt extends BaseAdapter {
    ArrayList<String> ColumnsName = new ArrayList<>();
    ArrayList<String> ColumnsType = new ArrayList<>();
    Context context;

    public TableRowAdpt(Context context, ArrayList<String> clmnNm, ArrayList<String> clmnTp){
        ColumnsName = clmnNm;
        ColumnsType = clmnTp;
        this.context = context;
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
        View row = inflater.inflate(R.layout.table_row, parent, false);
        TextView CLMNNM = row.findViewById(R.id.CLMNNM);
        TextView CLMNTP = row.findViewById(R.id.CLMNTP);
        CLMNNM.setText(ColumnsName.get(position));
        CLMNTP.setText(ColumnsType.get(position));
        return row;
    }
    public void DeleteItem(int position){
        ColumnsName.remove(position);
        ColumnsType.remove(position);
        notifyDataSetChanged();
    }

}
