package com.example.davaleba1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseCon extends SQLiteOpenHelper {
    private static final String DataBaseName="DataBase.db";
    private static final int DataBaseVersion=1;
    public DataBaseCon(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){super(context,name,factory,version);}
    public DataBaseCon(Context context){
        super(context,DataBaseName,null,DataBaseVersion);
    }

    public void onCreate(SQLiteDatabase db) {

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void CreateTable(TableClass TBCL){

    }
    public void SelectTable(String tableName){

    }
    public void DeleteTable(String tableName){

    }
    public void UpdateTable(String tableName){

    }

}
