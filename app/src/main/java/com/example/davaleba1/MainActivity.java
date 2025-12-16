package com.example.davaleba1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.frgm,new FirstFrmg()).commit();
        }
    }
    void ChangeFragment(Fragment to){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frgm,to).addToBackStack(null).commit();
    }
}