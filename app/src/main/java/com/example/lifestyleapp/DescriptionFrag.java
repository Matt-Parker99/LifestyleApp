package com.example.lifestyleapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DescriptionFrag extends Fragment {

    private DescriptionViewModel mViewModel;
    private static final String ARG_FILE_NAME = "file";


    public static DescriptionFrag newInstance(String filename) {
        DescriptionFrag fragment = new DescriptionFrag();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_FILE_NAME, filename);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DescriptionViewModel.class);
        String helpMe = ":(";
        if (getArguments() != null) {

            helpMe = getArguments().getString(ARG_FILE_NAME);

        } else {
            Log.e("DescriptionFrag", "Args = null");
        }
        mViewModel.setFile(helpMe);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.description_fragment, container, false);
        final TextView textBox = view.findViewById(R.id.descriptionText);
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textBox.setText(s);
            }
        });
        return view;
    }


}
