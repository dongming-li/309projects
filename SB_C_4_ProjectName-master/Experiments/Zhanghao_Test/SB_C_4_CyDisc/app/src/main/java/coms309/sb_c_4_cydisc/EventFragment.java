package coms309.sb_c_4_cydisc;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import 	android.widget.CalendarView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EventFragment extends Fragment {

    private static final String TAG = "CreateNewEvent";
    private static final String URL_FOR_CREATEEVENT = "http://proj-309-sb-c-4.cs.iastate.edu/ZhanghaoFolder/Event.php";

    String currentdate="";
    String[] myItems;
    ListView listView;

    public EventFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.event_activity, container, false);
        listView = (ListView) view.findViewById(R.id.list_events);

        CalendarView calendarView = view.findViewById(R.id.calendarView2);
        Button createEvent = (Button) view.findViewById(R.id.newEvent);
        //private StringBuilder date;


        createEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Context context = view.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText eventName = new EditText(context);
                final EditText eventDescription = new EditText(context);
                final EditText eventStartTime = new EditText(context);

                eventName.setHint("Event Name");
                eventDescription.setHint("Description");
                eventStartTime.setHint("Start time(xx:xx:xx)");

                layout.addView(eventName);
                layout.addView(eventDescription);
                layout.addView(eventStartTime);


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Add Event");
                builder.setView(layout);



                // Set up the buttons
                builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String eventname = "";
                        String eventdescription = "";
                        String eventstarttime = "";
                        String eventdate = "";
                        int userId;

                        userId = getArguments().getInt("userId");
                        eventname = eventName.getText().toString();
                        eventdescription = eventDescription.getText().toString();
                        eventstarttime = eventStartTime.getText().toString();
                        eventdate = EventFragment.this.currentdate;

                       //TEST COde Toast.makeText(((AlertDialog)dialog).getContext(),userId+eventname+eventdescription+eventstarttime+eventdate, Toast.LENGTH_LONG).show();
                        addListener("addAnEvent", userId,eventname,eventdescription,eventstarttime,eventdate);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
               builder.show();



            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                currentdate = year+"-"+(month+1)+"-"+dayOfMonth;
                //Toast.makeText(view.getContext(), "Year=" + year + " Month=" + month + " Day=" + dayOfMonth, Toast.LENGTH_LONG).show();
                addListener("onlyGetEventsToday", getArguments().getInt("userId"),
                        null, null,null,currentdate);

                }
        });



        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String currentdate = df.format(c.getTime());

        addListener("onlyGetEventsToday", getArguments().getInt("userId"),
                null, null,null,currentdate);


        return view;
    }

    private void addListener(final String request, final int userId, final String eventname, final String eventdescription,final String eventstarttime,final String eventdate) {

        if (request != null && userId>=0) {

            // Tag used to cancel the request
            String cancel_req_tag = "Cancel Creation";
            StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_CREATEEVENT, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Create New Event Response: " + response.toString());
                    // hideDialog();
                    try {
                        JSONObject jObj = new JSONObject(response);
                        boolean success = jObj.getBoolean("success");

                        if (success) {
                            // Launch User activity
                            //TODO
                            JSONArray eventsInformationArray = jObj.getJSONArray("eventsInformation");
                            myItems = new String[eventsInformationArray.length()];
                            for (int i = 0; i < eventsInformationArray.length(); i++) {
                                myItems[i] = eventsInformationArray.getString(i);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myItems);
                            EventFragment.this.listView.setAdapter(adapter);


                        } else {
                            String errorMsg = jObj.getString("error_msg");
                            Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "New Event Creation Error: " + error.getMessage());
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    // hideDialog();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    // Posting params to login url
                    Map<String, String> params = new HashMap<String, String>();

                    if (request.equals("addAnEvent")) {

                        params.put("request", request);   //determine create new event or not
                        params.put("userId", userId+"");
                        params.put("eventName", eventname);
                        params.put("eventDescription", eventdescription);
                        params.put("eventStartTime", eventstarttime);
                        params.put("eventDate", eventdate);

                    } else if (request.equals("onlyGetEventsToday")) {
                        params.put("request", request);
                        params.put("userId", userId+"");
                        params.put("eventDate", eventdate);
                    }

                    System.out.println(params.values().toString());

                    return params;

                }

            };
            // Adding request to request queue
            AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);

        }
    }


}
