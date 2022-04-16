package com.example.matthewburke.partyat;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by MatthewBurke on 11/5/17.
 */



/** Sets the name, icon, and friend count to each listview item in a list*/
public class getLocStrings extends ArrayAdapter<String> {

    private String[] titles = {"blank"};
    private String[] friendLocs = {"1", "5", "3", "4", "2","2", "5", "3", "4", "2","3", "5", "3", "4", "2","4", "5", "3", "4", "2"};
    private String[] icons = {"https://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png"};
    private String[] placeIds = {"placeId"};
    private Activity context;


    public getLocStrings(Activity context, String[] titles, String[] icons, String[] placeIds, String[] friendLocs){//, String[] type, String[] isOpen, String[] palceId){
        super(context, R.layout.nearby_loc_layout, titles);
        this.context = context;
        this.titles = titles;
        this.icons = icons;
        this.placeIds = placeIds;
        this.friendLocs = friendLocs;

    }


    /** Sets the name, icon, and friendcount to each listview item*/
    @Override
    public View getView(int position, View counterView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.nearby_loc_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        final ImageView imageViewIcon = (ImageView) listViewItem.findViewById(R.id.imageViewIcon);
        TextView textViewFriendCount = (TextView) listViewItem.findViewById(R.id.textViewFriendCount);

        //set names
        textViewName.setText(titles[position]);

        //set friend count
        int sum = 0;
        for (int i = 0; i < friendLocs.length; i++) {
            if (friendLocs[i].equals(placeIds[position])){
                sum ++;
            }
        }
        textViewFriendCount.setText("" + sum);

        //set icon image
        String url = icons[position];

        ImageRequest imgRequest = new ImageRequest(url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imageViewIcon.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                imageViewIcon.setBackgroundColor(Color.parseColor("#ff0000"));
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this.getContext()).add(imgRequest);


        return listViewItem;

    }


}