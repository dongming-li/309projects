package com.example.rajivbhoopala.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Track extends Fragment  {
    SensorManager sensorManager;
    TextView steps;
    boolean running = false;
    Button button;
    Button button2;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Track");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        container.removeAllViewsInLayout(); // Clear screen to avoid blending.
        View view = inflater.inflate(R.layout.fragment_track, container, false);
        button = (Button) view.findViewById(R.id.clickMe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrackActivity.class);
                startActivity(intent);
            }
        });

        button2 = view.findViewById(R.id.clickMe2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), watchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
