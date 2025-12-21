package com.example.davaleba1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    public ArrayList<String> SelectTable(String tableName){
        ArrayList<String>Result = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                for(int i=0;i<cursor.getColumnCount();i++){
                    String Clmn = cursor.getColumnName(i);
                    String Value = cursor.getString(i);
                    Result.add(Clmn + " = " + Value);
                }
                Result.add("-----------------------");
            }while(cursor.moveToNext());
        }
        return Result;
    }
    public void DeleteTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }
    public void DeleteColumn(String tableName,String ColumnName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("ALTER TABLE "+tableName+" DROP COLUMN "+ColumnName);
    }
    public ArrayList<String>TablesName(){
        ArrayList<String>Tables = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT name FROM sqlite_master " +
                "WHERE type='table' AND name NOT LIKE 'sqlite_%' " +
                "AND name != 'android_metadata'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                Tables.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Tables;
    }
    public ArrayList<String>getColumns(String tableName){
        ArrayList<String>Columns = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "PRAGMA table_info("+tableName+")";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String Type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                Columns.add(Name+"   Type -->"+Type);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return Columns;
    }
    public ArrayList<String>getClmn(String tableName){
        ArrayList<String>Columns = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "PRAGMA table_info("+tableName+")";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String Name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String Type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                Columns.add(Name);
                Columns.add(Type);
            } while(cursor.moveToNext());
        }
        cursor.close();
        return Columns;
    }
    public void CreateTbl(String sql){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }
}
