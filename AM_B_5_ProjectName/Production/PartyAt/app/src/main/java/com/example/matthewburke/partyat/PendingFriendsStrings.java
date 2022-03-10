package com.example.matthewburke.partyat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**Inflates the pending friends listview*/
public class PendingFriendsStrings extends ArrayAdapter<String> {

    private String[] FirstNames;
    private String[] LastNames;
    private String[] UserNames;
    private String ID;

    private Activity activity;
    private Context context;



    public PendingFriendsStrings(Context context, String ID, String[] FirstNames, String[] LastNames, String[] UserNames){
        super((Activity)context, R.layout.pending_friends_list_layout, FirstNames);
        this.activity = (Activity) context;
        this.context = context;
        this.FirstNames = FirstNames;
        this.LastNames = LastNames;
        this.UserNames = UserNames;
        this.ID = ID;
    }

    private void sendPendingRequest(final String function, final String friendName){
        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/insertFriends.php",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        if(function.equals("DFR")||function.equals("CFR")){
                            activity.finish();
                            activity.startActivity(activity.getIntent());
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("function", function);
                params.put("id", ID);
                if(friendName!=null){
                    params.put("name", friendName);
                }
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public View getView(int position, View counterView, ViewGroup parent){
        LayoutInflater inflater = activity.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.pending_friends_list_layout, null, true);
        TextView friendsListName = (TextView) listViewItem.findViewById(R.id.pendingFriendsListName);
        final TextView friendsListUserName = (TextView) listViewItem.findViewById(R.id.pendingFriendsListUserName);
        Button confirmFriend = (Button) listViewItem.findViewById(R.id.confirmFriendButton);
        Button denyFriend = (Button) listViewItem.findViewById(R.id.denyFriendButton);




        friendsListName.setText(""+FirstNames[position] + " " + LastNames[position]);
        friendsListUserName.setText(UserNames[position]);

        denyFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPendingRequest("DFR",null);
            }
        });

        confirmFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPendingRequest("CFR",friendsListUserName.getText().toString());
            }
        });


        return listViewItem;
    }
}
