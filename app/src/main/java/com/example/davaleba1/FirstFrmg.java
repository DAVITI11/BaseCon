package com.example.davaleba1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstFrmg extends Fragment {

    Button AddButton;
    Button ShowButton;
    Button DelButton;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstfrgmlt, container, false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AddButton = view.findViewById(R.id.AddButton);
        ShowButton = view.findViewById(R.id.ShowButton);
        DelButton = view.findViewById(R.id.DelButton);

        AddButton.setOnClickListener(v->{
            ((MainActivity)getActivity()).ChangeFragment(new FragmentToAddTable());
        });

//        ShowButton.setOnClickListener(v->{
//            ((MainActivity)getActivity()).ChangeFragment(new FragmentToShowTable());
//        });
//        DelButton.setOnClickListener(v->{
//            ((MainActivity)getActivity()).ChangeFragment(new FragmentToDeleteTable());
//        });

    }

}
