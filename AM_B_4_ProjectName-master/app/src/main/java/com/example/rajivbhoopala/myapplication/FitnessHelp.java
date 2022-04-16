package com.example.rajivbhoopala.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by rajivbhoopala on 10/4/17.
 */

public class FitnessHelp extends Fragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Fitness Help");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fitness_help, container, false);
        Button chatButton = view.findViewById(R.id.chatWithTrainer);
        chatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Utility(getActivity()).launchActivity(getActivity(), Login.class);
                }
         //   }
        });
        return view;
    }


}
