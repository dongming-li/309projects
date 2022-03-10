package com.example.matthewburke.partyat;

/**
 * Created by MatthewBurke on 11/27/17.
 */

import com.android.volley.VolleyError;

import java.io.UnsupportedEncodingException;

import android.util.Log;
import android.widget.ImageView;
import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.ImageLoader;

/** Was going to process image requests, but we used ImageRequest class from com.android.volley.toolbox.ImageRequest instead, in a similar fashion.*/
public class ImageReq {
    private static final String TAG = ImageReq.class.getSimpleName();
    private ImageView imageView;

    public ImageReq(ImageView imageView){
        this.imageView = imageView;
    }

    public void makeImageRequest(String urlImage) {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

         imageLoader.get(urlImage, new ImageLoader.ImageListener() {

             @Override
             public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Image Load Error: " + error.getMessage());
             }

             @Override
             public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                 if (response.getBitmap() != null) {
                    imageView.setImageBitmap(response.getBitmap());
                 }
             }
         });

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(urlImage);
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            // cached response doesn't exists. Make a network call here
        }
    }
}
