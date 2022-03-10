package com.example.matthewburke.partyat;

/**
 * Created by MatthewBurke on 10/9/17.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by matthewkinnander on 10/8/17.
 */

/** This class takes in the arrays of titles, descriptions, start dates, and end dates and sets them into a layout to be viewed. **/
public class getPromoStrings extends ArrayAdapter<String>{

    private String[] titles;
    private String[] descriptions;
    private String[] startdates;
    private String[] enddates;

    private Activity context;



    public getPromoStrings(Activity context, String[] titles, String[] descriptions, String[] startdates, String[] enddates){
        super(context, R.layout.list_view_layout, titles);
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.startdates = startdates;
        this.enddates = enddates;
    }

    /** Sets a viewlist to display the information of the promotion. **/
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
