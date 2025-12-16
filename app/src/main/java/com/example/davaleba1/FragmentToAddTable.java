package com.example.davaleba1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentToAddTable extends Fragment {
    Button GoToMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgmlayout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GoToMain = view.findViewById(R.id.GoToMainButton);
        GoToMain.setOnClickListener(v->{
            ((MainActivity)getActivity()).ChangeFragment(new FirstFrmg());
        });
    }
}
