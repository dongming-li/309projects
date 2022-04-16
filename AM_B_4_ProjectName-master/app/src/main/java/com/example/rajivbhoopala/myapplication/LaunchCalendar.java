package com.example.rajivbhoopala.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by rajivbhoopala on 11/2/17.
 */
public class LaunchCalendar extends Fragment {

    String selectedDate = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        selectedDate = getArguments().getString("date");
        container.removeAllViewsInLayout();
        return inflater.inflate(R.layout.activity_launch_calendar, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        assert view != null;

        TextView theDate = view.findViewById(R.id.date);
        Button btnCalendar = view.findViewById(R.id.btnCalendar);

        theDate.setText(selectedDate);
        SetData();

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnCalendar:
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.show_Calendar, new Calendar(), "fragment screen");
                        ft.commit();
                    break;
                }
            }
        });
    }
    //TODO
    //create comments for this
    private void SetData()
    {
        final View view = getView();
        assert view != null;

        Bundle getUser = getActivity().getIntent().getExtras(); // Get username from bundle
        String username = getUser.getString("username");
        String[] datepieces;

        if (selectedDate == null)
            selectedDate = "Error";
        else
        {
            datepieces = selectedDate.split("/");
            selectedDate = datepieces[2] + "-" + datepieces[0] +  "-" + datepieces[1];
        }

        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Workout.php?username=" + username + "&date="
                + selectedDate;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        //The response has all of the workouts in it.
                        TextView info;
                        info = view.findViewById(R.id.WorkoutInfo);
                        info.setText("\n" + response.replace(":", "\n"));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });


        //Adds the request to the queue. If this wasnt here the string request would never be
        //processed
        MySingleton.getInstance(getActivity()).addToRequestQueue(StringReq);

        String URL2 = "http://proj-309-am-b-4.cs.iastate.edu/Get_Workout_Info.php?username=" + username + "&date="
                + selectedDate;
        //Set the information of this days workout.
        StringRequest StringReq2 = new StringRequest(Request.Method.GET, URL2,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        //The response has all of the workouts in it.
                        TextView info;
                        info = view.findViewById(R.id.Workout);
                        info.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });


        //Adds the request to the queue. If this wasnt here the string request would never be
        //processed
        MySingleton.getInstance(getActivity()).addToRequestQueue(StringReq2);
    }
}
