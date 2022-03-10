package com.example.rajivbhoopala.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by Joel on 10/8/2017.
 */

public class MemberManagement extends Fragment {

    public MemberManagement() {
        // Required empty public constructor
    }

    String[] users = {};
    MemberManagementAdapter memberManagementAdapter = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Member Management");
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_refresh); // Only display refresh button for member managment
        item.setVisible(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                memberManagementAdapter.notifyDataSetChanged(); // Refresh list view
                populateMemberView();
                new Utility(getActivity()).displayShortToast("Refreshed list");
                return true;
            default:
                break;
        }

        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member_management, container, false);

        populateMemberView();

        final EditText searchBar = view.findViewById(R.id.mm_editText);

        // Get text in search bar and then filter it.
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = searchBar.getText().toString().toLowerCase(Locale.getDefault());
                memberManagementAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
            }
        });

        return view;
    }

    /**
     * Populates the list of members from the database.
     */
    private void populateMemberView() {
        //Get Users
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Users.php";

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        users = response.split("(\\s*<[Bb][Rr]\\s*?>)");

                        View view = getView();
                        assert view != null;
                        ListView members = view.findViewById(R.id.ListViewMembers);

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

                        memberManagementAdapter = new MemberManagementAdapter(memberList, getContext());

                        // set list view using a custom adapter
                        members.setAdapter(memberManagementAdapter);
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