package com.example.rajivbhoopala.myapplication;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Joel on 10/08/17.
 */

public class WeightTracker extends Fragment {

    int currentWeight;
    int desiredWeight;

    String[] weightReq = {};

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Weight Tracker");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViewsInLayout();
        View view = inflater.inflate(R.layout.fragment_weight_tracker, container, false);

        getWeight();
        Button submitButton = view.findViewById(R.id.submitButton);

        final EditText currentWeightInput = view.findViewById(R.id.currentWeightInput);
        final EditText desiredWeightInput = view.findViewById(R.id.desiredWeightInput);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String currentWStr = currentWeightInput.getText().toString();
                final String desiredWStr = desiredWeightInput.getText().toString();

                if ((currentWStr.isEmpty()) || (desiredWStr.isEmpty()))
                    new Utility(getActivity()).displayLongToast("Enter your current and desired weight");

                else {
                    currentWeight = Integer.valueOf(currentWStr);
                    desiredWeight = Integer.valueOf(desiredWStr);

                    Bundle getUser = getActivity().getIntent().getExtras(); // Get username from bundle
                    String username = getUser.getString("username");

                    UpdateCurrentWeight(username, currentWeight);
                    UpdateDesiredWeight(username, desiredWeight);

                    new Utility(getActivity()).displayShortToast("Saved changes for " + username);
                }
            }
        });

  //     GraphView graphView = view.findViewById(R.id.graph); // Create graph using graph view

        GetDataPoints();


  /**      LineGraphSeries<DataPoint> series_currWeight = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(7, 175),
                new DataPoint(8, 173),
                new DataPoint(9, 169),
                new DataPoint(10, 171),
                new DataPoint(11, 169),
                new DataPoint(12, 166),
                new DataPoint(13, 165),
                new DataPoint(14, 163),
                new DataPoint(15, currentWeight),
        });
        graphView.addSeries(series_currWeight);

        LineGraphSeries<DataPoint> series_desiredWeight = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 150)
        });
        graphView.addSeries(series_desiredWeight);
        series_desiredWeight.setColor(Color.RED); // Set the colour of desired weight line to blue

        // Title and misc styling options
        graphView.setTitle("Weekly Weight Tracking Chart");

        // Label axes
        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Weeks");
        gridLabel.setVerticalAxisTitle("Weight (pounds)"); */

        return view;
    }

    private void GetDataPoints()
    {
        Bundle getUser = getActivity().getIntent().getExtras(); // Get username from bundle
        String username = getUser.getString("username");

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date1 = new Date();
        String end = df.format(date1);
        LocalDate date2 = LocalDate.now().minusDays(14);
        String start = date2.toString() + " 00:00:00";


        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Weight_Over_Time.php?username=" + username + "&start_date=" + start + "&end_date=" + end;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        //The response has all of the workouts in it.
                        response = response.replace("<BR>",",");
                        String values[] = response.split(",");

                        Stack Dates = new Stack();
                        Queue Weights = new LinkedList();
                        Stack <Date> DateDates = new Stack();


                        for (int i = 0; i < values.length; i++) {
                            Weights.add(values[i]);
                            Dates.push(values[i + 1]);
                            i++;
                        }

                        while(!Dates.isEmpty())
                        {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                Date inputDate = df.parse(Dates.pop().toString());
                                DateDates.push(inputDate);
                            }
                            catch(Exception e)
                            {
                            }
                        }

                        GraphView graphView = getView().findViewById(R.id.graph); // Create graph using graph view
                        DataPoint[] dps = new DataPoint[Weights.size()];
                        int Size = Weights.size();
                        for(int i = 0; i < Size; i++)
                        {
                            int current = Integer.parseInt(Weights.remove().toString());
                            if(i == 0)
                            {
                                  graphView.getViewport().setMinX(current);
                            }
                            if(i == Size - 1)
                            {
                                  graphView.getViewport().setMaxX(current);
                            }
                            dps[i] = new DataPoint(DateDates.pop(), current);
                        }


                        LineGraphSeries<DataPoint> series_currWeight = new LineGraphSeries<>(dps);
                        graphView.addSeries(series_currWeight);

                        // Title and misc styling options
                        graphView.setTitle("Weekly Weight Tracking Chart");

                        graphView.getViewport().setXAxisBoundsManual(true);

                        graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
                        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);

                        // Label axes
                        GridLabelRenderer gridLabel = graphView.getGridLabelRenderer();
                        gridLabel.setHorizontalAxisTitle("Weeks");
                        gridLabel.setVerticalAxisTitle("Weight (pounds)");
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
    }


    /**
     * Get the current/desired weight from server..
     * @return specified weight
     */
    private void getWeight()
    {
        String weight = null;

        Bundle getUser = getActivity().getIntent().getExtras(); // Get username from bundle
        String username = getUser.getString("username");

        // Get weight status from server
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Weights.php?username=" + username;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //This function is Asynchronous. So it will not run linearly with the rest of the program.
                    @Override
                    public void onResponse(String response) {
                        final EditText currentWeightInput = getView().findViewById(R.id.currentWeightInput);
                        final EditText desiredWeightInput = getView().findViewById(R.id.desiredWeightInput);


                        weightReq = response.split(",");

                        currentWeight = Integer.parseInt(weightReq[0]);
                        desiredWeight = Integer.parseInt(weightReq[1]);

                        if (currentWeight != 0)
                            currentWeightInput.setText(weightReq[0]); // Set currentWeight to most recent value
                        if (desiredWeight != 0)
                            desiredWeightInput.setText(weightReq[1]); // Set desiredWeight to most recent value
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
     * Method that updates the current users weight.
     * @param username username of the specified user.
     * @param weight weight for specified user.
     */
    void UpdateCurrentWeight(String username, int weight)
    {
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Update_Weight.php?username=" + username + "&weight=" + weight;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                            System.out.println("User Weight updated");
                        else
                            System.out.println("User Weight NOT updated");
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

    /**
     * Method that updates the desired weight.
     * @param username username of the specified user.
     * @param weight weight for specified user.
     */
    void UpdateDesiredWeight(String username, int weight)
    {
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Update_LossGoal.php?username=" + username + "&loss_goal=" + weight;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1"))
                            System.out.println("User Loss goal updated");
                        else
                            System.out.println("User loss goal NOT updated");
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
}
