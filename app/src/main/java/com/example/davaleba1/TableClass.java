package com.example.davaleba1;

import java.util.ArrayList;

public class TableClass {
    public String TableName;
    public ArrayList<String> Columns;
    public TableClass(String name, ArrayList<String> clmn) {
        TableName = name;
        Columns = clmn;
    }
}
