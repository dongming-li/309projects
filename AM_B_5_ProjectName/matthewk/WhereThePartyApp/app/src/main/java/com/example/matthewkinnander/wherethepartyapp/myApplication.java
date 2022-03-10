package com.example.matthewkinnander.wherethepartyapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by matthewkinnander on 10/8/17.
 */

public class myApplication extends ArrayAdapter<String>{

    private String[] titles;
    private String[] descriptions;
    private String[] startdates;
    private String[] enddates;

    private Activity context;



    public myApplication(Activity context, String[] titles, String[] descriptions, String[] startdates, String[] enddates){
        super(context, R.layout.list_view_layout, titles);
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.startdates = startdates;
        this.enddates = enddates;
    }

    @Override
    public View getView(int position, View counterView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.textViewTitle);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);
        TextView textViewStartDate = (TextView) listViewItem.findViewById(R.id.textViewStartDate);
        TextView textViewEndDate = (TextView) listViewItem.findViewById(R.id.textViewEndDate);

        textViewTitle.setText(titles[position]);
        textViewDescription.setText(descriptions[position]);
        textViewStartDate.setText(startdates[position]);
        textViewEndDate.setText(enddates[position]);

        return listViewItem;


    }
}
