package com.example.matthewburke.partyat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Tyler Helmrich on 11/7/2017.
 */
/** Inflater for the friends list*/
public class FriendsStrings extends ArrayAdapter<String> {

    private String[] FirstNames;
    private String[] LastNames;
    private String[] UserNames;

    private Activity context;



    public FriendsStrings(Activity context, String[] FirstNames, String[] LastNames, String[] UserNames){
        super(context, R.layout.friends_list_layout, FirstNames);
        this.context = context;
        this.FirstNames = FirstNames;
        this.LastNames = LastNames;
        this.UserNames = UserNames;
    }

    public View getView(int position, View counterView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.friends_list_layout, null, true);
        TextView friendsListName = (TextView) listViewItem.findViewById(R.id.friendsListName);
        TextView friendsListUserName = (TextView) listViewItem.findViewById(R.id.friendsListUserName);

        friendsListName.setText(""+FirstNames[position] + " " + LastNames[position]);
        friendsListUserName.setText(UserNames[position]);

        return listViewItem;
    }
}
