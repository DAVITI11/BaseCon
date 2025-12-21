package com.example.davaleba1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DataBaseCon db ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseCon(this);
        if(savedInstanceState == null){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.frgm,new FirstFrmg()).commit();
        }
    }
    void ChangeFragment(Fragment to){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frgm,to).commit();
    }
    public void CreateTable(StringBuilder sql){
        String st = sql.toString();
        db.CreateTbl(st);
    }
    ArrayList<String>SelectAllTable(){
        ArrayList<String>Tables = db.TablesName();
        Tables = db.TablesName();
        return Tables;
    }
    void DeleteTable(String tableName){
        db.DeleteTable(tableName);
    }
    public ArrayList<String>GetColumns(String tableName) {
        ArrayList<String> Columns = db.getColumns(tableName);
        return Columns;
    }
    public ArrayList<String>SelectTable(String tableName){
        ArrayList<String>Result = db.SelectTable(tableName);
        return Result;
    }
    public ArrayList<String>getClmn(String tableName){
        ArrayList<String>Columns = db.getClmn(tableName);
        return Columns;
    }
}