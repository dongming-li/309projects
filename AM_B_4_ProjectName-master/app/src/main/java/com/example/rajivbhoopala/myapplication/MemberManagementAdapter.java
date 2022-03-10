package com.example.rajivbhoopala.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
/**
 * Created by Joel on 11/2/2017.
 */

public class MemberManagementAdapter extends BaseAdapter implements ListAdapter{

    private ArrayList<String> list = new ArrayList<>(); // The actual member list
    private ArrayList<String> filteredList = new ArrayList<>();  // for loading  filter data
    private String[] names;
    private Context context;

    public MemberManagementAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.filteredList.addAll(list);
    }

    /**
     * Gets the number of items.
     * @return Returns size of list.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Gets the item position.
     * @param pos position of the item.
     * @return item position.
     */
    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    /**
     * Gets the item ID.
     * @param pos position of the item.
     * @return item ID.
     */
    @Override
    public long getItemId(int pos) {
        return pos;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_mm_custom_layout, null);
        }

        // Handle TextView and display string from your list
        final TextView textView = view.findViewById(R.id.custom_text_view);
        textView.setText(list.get(position));

        // Handle Remove button
        Button deleteBtn = view.findViewById(R.id.rm_list_btn);

        // Get username here
        final String user = textView.getText().toString();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Utility(context).displayShortToast(user);
            }
        });

        // Listen for rename
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new Utility(context).createDialogBuilder(context, "Rename User in form 'FirstName LastName'", null, true);

                final EditText input = new EditText(context);
                input.setText(user); // Set inital text
                input.setSelection(user.length()); // Set cursor position to end of string

                builder.setView(input);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String newUser = input.getText().toString();
                        String[] originalName = user.split(" ");
                        String[] newName = newUser.split(" ");

                        updateName(originalName, newName);

                        if (newUser.matches(""))
                            new Utility(context).displayShortToast("Please provide a valid name");
                        else
                        {
                            // Rename the current position of selection in array list to newUser
                            list.set(position, newUser);

                            // Refresh list
                            notifyDataSetChanged();
                            new Utility(context).displayShortToast("Renamed " + "'" + user + "'" + " to " + "'" + newUser + "'");
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        });

        // Listen for remove
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new Utility(context).createDialogBuilder(context, "Remove User",
                        "Are you sure you want to remove " + "'" + user + "'" + " ?", true);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // remove from list
                        list.remove(position);

                        new Utility(context).displayShortToast("Removed " + user);

                        // Remove user from database here.
                        names = user.split(" ");

                        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Remove_User.php?firstname=" + names[0] + "&lastname=" + names[1];

                        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                                new Response.Listener<String>() {
                                    //Function that says what to do when a response is given.
                                    @Override
                                    public void onResponse(String response) {
                                        System.out.println("User Removed");
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // Handle error
                                    }
                                });

                        //Adds the request to the queue. If this wasnt here the string request would never be processed
                        MySingleton.getInstance(context).addToRequestQueue(StringReq);
                        // Refresh list
                        notifyDataSetChanged();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }

    /**
     * Filters the listView.
     * @param charText filter the listView using the string specified.
     */
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();

        if (charText.length() == 0)
            list.addAll(filteredList);

        else {
            for (String wp : filteredList) {
                if (wp.toLowerCase(Locale.getDefault()).contains(charText))
                    list.add(wp);
            }
        }

        notifyDataSetChanged();
    }

    /**
     * Updates the name and pushes it to the database.
     * @param originalName Original name before renaming.
     * @param newName New name after renaming.
     */
    private void updateName(String[] originalName,String[] newName)
    {
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Update_Name.php?firstname=" + originalName[0] + "&lastname=" + originalName[1] + "&newfirst=" + newName[0] + "&newlast=" + newName[1];

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        System.out.println("User's Name updated");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        //Adds the request to the queue. If this wasnt here the string request would never be processed
        MySingleton.getInstance(context).addToRequestQueue(StringReq);
    }
}