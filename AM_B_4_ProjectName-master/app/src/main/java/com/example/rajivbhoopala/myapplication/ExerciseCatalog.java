package com.example.rajivbhoopala.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Joel on 11/25/17.
 */

public class ExerciseCatalog extends Fragment {

    ListView listViewSections;
    String[] workoutPlans = {};

    String[] users = {};


    int category = 0; // Selected category to open second listView.

    /**
     * Exercise catalog - empty constructor
     */
    public ExerciseCatalog() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Exercise Catalog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_catalog, container, false);

        displaySections(view);
        registerClickCallback(view);

        return view;
    }

    /**
     * Display workout classes/sections.
     * @param view display
     */
    private void displaySections(View view) {
        listViewSections = view.findViewById(R.id.exercise_catalog_list_categories);

        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Workouts.php";
        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //This function is Asynchronous. So it will not run linearly with the rest of the program.
                    @Override
                    public void onResponse(String response) {
                        workoutPlans = response.split("(\\s*<[Bb][Rr]\\s*?>)");

                        for (int i = 0; i <= workoutPlans.length - 1; i++) {
                            workoutPlans[i] = workoutPlans[i].substring(workoutPlans[i].indexOf(":") + 1);
                            workoutPlans[i] = workoutPlans[i].substring(0, workoutPlans[i].indexOf(":"));
                        }

                        // Convert items to list view
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, workoutPlans);

                        // Configure list view.
                        listViewSections.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        // Adds the request to the queue. If this wasn't here the string request would never be processed
        MySingleton.getInstance(getActivity()).addToRequestQueue(StringReq);
    }

    /**
     * Handle click on each section.
     * @param view display
     */
    private void registerClickCallback(View view) {
        listViewSections = view.findViewById(R.id.exercise_catalog_list_categories);
        listViewSections.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                category = position; // Store the position into category

                String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Usernames.php";

                StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                        new Response.Listener<String>() {
                            //Function that says what to do when a response is given.
                            @Override
                            public void onResponse(String response) {
                                users = response.split("(\\s*<[Bb][Rr]\\s*?>)");

                                // Add string users onto the memberList (array list)
                                ArrayList<String> memberList = new ArrayList<>();
                                memberList.addAll(Arrays.asList(users));

                                // Sort list alphabetically
                                Collections.sort(memberList, new Comparator<String>() {
                                    @Override
                                    public int compare(String s1, String s2) {
                                        return s1.compareToIgnoreCase(s2);
                                    }
                                });

                                users = new String[memberList.size()];
                                users = memberList.toArray(users);

                                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                                dialogBuilder.setTitle("Select a user");
                                dialogBuilder.setItems(users, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, final int which) {

                                        // TODO remove hard-coded user string
                                        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Change_User_Excercise_Routine.php?username="
                                        + users[which] + "&routine=" + category;

                                        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                                                new Response.Listener<String>() {
                                                    //Function that says what to do when a response is given.
                                                    @Override
                                                    public void onResponse(String response) {
                                                        new Utility(getActivity()).displayLongToast(users[which] + " has now switched to " +
                                                                workoutPlans[category] + " workout plan");
                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error) {
                                                        // Handle error
                                                    }
                                                });

                                        //Adds the request to the queue. If this wasnt here the string request would never be processed
                                        MySingleton.getInstance(getActivity()).addToRequestQueue(StringReq);
                                    }
                                });

                                dialogBuilder.create().show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                            }
                        });

                //Adds the request to the queue. If this wasnt here the string request would never be processed
                MySingleton.getInstance(getActivity()).addToRequestQueue(StringReq);
            }
        });
    }
}
