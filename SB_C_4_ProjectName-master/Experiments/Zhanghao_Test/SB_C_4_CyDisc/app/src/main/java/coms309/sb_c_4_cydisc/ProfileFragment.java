package coms309.sb_c_4_cydisc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {
    private static final String URL_FOR_DISCS = "http://proj-309-sb-c-4.cs.iastate.edu/davidFolder/getDiscs.php";
    private static final String URL_FOR_SCORES = "http://proj-309-sb-c-4.cs.iastate.edu/davidFolder/getProfileTopScores.php";
    OnDataPass dataPasser;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.profile_activity, container, false);
        OnDataPass activity = (OnDataPass) getActivity();
        String username = activity.getUsername();

        //TextView playerName = (TextView) view.findViewById(R.id.tvPlayerName);
        TextView viewAllDisc = (TextView) view.findViewById(R.id.textView11);
        TextView viewMoreScores = (TextView) view.findViewById(R.id.textView7);

        //playerName.setText(username);
        getPlayerDiscs(view);
        getPlayerTopScores(view);

        viewAllDisc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment fragment = null;
                Class fragmentClass = DiscBagFragment.class;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch(Exception e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        viewMoreScores.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment fragment = null;

                try {
                    fragment = (Fragment) LeaderboardFragment.class.newInstance();
                } catch(Exception e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
            }
        });

        return view;
    }

    private void getPlayerDiscs(final View view) {
        String cancel_req_tag = "load discs";
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_DISCS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    ListView listView = (ListView) view.findViewById(R.id.list_mostUsedDiscs);
                    List<String> discs = new ArrayList<String>();

                    JSONObject jObject = new JSONObject(response);

                    String discBrand = jObject.getString("discBrand");
                    String discName = jObject.getString("discName");
                    String discSpeed = jObject.getString("discSpeed");
                    String discGlide = jObject.getString("discGlide");
                    String discTurn = jObject.getString("discTurn");
                    String discFade = jObject.getString("discFade");

                    String insert = discBrand + " " + discName + "-<" + discSpeed + "," + discGlide + "," + discTurn + "," + discFade + ">";

                    discs.add(insert);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, discs);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);
    }

    private void getPlayerTopScores(final View view) {
        String cancel_req_tag = "top scores";
        StringRequest strReq = new StringRequest(Request.Method.POST, URL_FOR_SCORES, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                ListView listView = (ListView) view.findViewById(R.id.list_topScores);
                List<String> topScores = new ArrayList<String>();

                try {
                    JSONObject jObject = new JSONObject(response);
                    boolean success = jObject.getBoolean("success");

                    if (success) {
                        String courseNumber = jObject.getString("courseId");
                        String topScore = jObject.getString("totalScore");

                        String insert = "Top Score of: " + topScore + " at Course Number " + courseNumber;

                        topScores.add(insert);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(topScores.isEmpty()){
                    String message = "There are no top scores to show";
                    topScores.add(message);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, topScores);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                // hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getContext()).addToRequestQueue(strReq, cancel_req_tag);
    }
}