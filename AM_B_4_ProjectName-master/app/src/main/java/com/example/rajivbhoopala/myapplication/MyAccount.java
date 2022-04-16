package com.example.rajivbhoopala.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
//import java.util.Calendar;

/**
 * Created by rajivbhoopala on 10/4/17.
 */

public class MyAccount extends Fragment {

    Button openCalender;
    Button imageButton;
    ImageView imageview;
    private static final int SELECTED_PICTURE = 1;
    private Uri imageUri;
    private String selectedImagePath=null;
    //ImageView iv;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Account");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);

        Bundle getUser = getActivity().getIntent().getExtras(); // Get username from bundle
        String username = getUser.getString("username");
        TextView name = view.findViewById(R.id.textView5);
        name.setText("Welcome");

        openCalender = view.findViewById(R.id.openCalendar11);
        imageButton = view.findViewById(R.id.button2);
        imageview = view.findViewById(R.id.imageView11);

        /*if (imageUri != null) {
            imageview.setImageURI(imageUri);
        }*/

        SetWorkouts(username);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();

                /*Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECTED_PICTURE);*/

            }
        });


          //iv = getView().findViewById(R.id.imageView7);
//        Bundle getUser = getActivity().getIntent().getExtras(); // Get username from bundle
//        String username = getUser.getString("username");
//        TextView tv = (TextView) getActivity().findViewById(R.id.userWelcome);
//        tv.setText("Hello " + username + ", here are your exercises for today");

        openCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.openCalendar11:
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.my_account, new Calendar(), "fragment screen");
                        ft.commit();
                }

            }
        });
        return view;
    }



    private void SetWorkouts(String username)
    {
        String Date = DateFormat.getDateTimeInstance().format(new Date());
        String[] datepieces;

        datepieces = Date.split(" ");

        datepieces[0] = GetMonth(datepieces[0]);
        datepieces[1] = datepieces[1].replace(",", " ");

        String date = datepieces[2] + "-" + datepieces[0] + "-" + datepieces[1];

        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Get_Workout.php?username=" + username + "&date=" + date;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        //The response has all of the workouts in it.
                        TextView info;
                        info = getView().findViewById(R.id.textView2);
                        response = response.replace(":","\n");

                        info.append("\n" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });


        //Adds the request to the queue. If this wasnt here the string request would never be
        //processed
        MySingleton.getInstance(getActivity()).addToRequestQueue(StringReq);
    }

    private String GetMonth(String month)
    {
        switch(month)
        {
            case "Jan":
                return "1";
            case "Feb":
                return "2";
            case "Mar":
                return "3";
            case "Apr":
                return "4";
            case "May":
                return "5";
            case "Jun":
                return "6";
            case "Jul":
                return "7";
            case "Aug":
                return "8";
            case "Sep":
                return "9";
            case "Oct":
                return "10";
            case "Nov":
                return "11";
            case "Dec":
                return "12";
            default:
                return "-1";
        }
    }

    public void openGallery()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, SELECTED_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //imageButton.setVisibility(View.VISIBLE);

        if(resultCode == Activity.RESULT_OK && requestCode == SELECTED_PICTURE)
        {
            imageUri = data.getData();
            imageview.setImageURI(imageUri);
            //imageButton.setVisibility(View.INVISIBLE);
        }

        /*if (requestCode == SELECTED_PICTURE) {
            imageUri = data.getData();
            imageview.setImageURI(imageUri);
            selectedImagePath = getPath(imageUri);
            System.out.println("Image Path : " + selectedImagePath);
            imageview.setImageURI(imageUri);
        }*/
    }


    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }

   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the image bitmap into outState
        Bitmap bitmap = ((BitmapDrawable)imageview.getDrawable()).getBitmap();
        outState.putParcelable("bitmap", bitmap);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Read the bitmap from the savedInstanceState and set it to the ImageView
        if (savedInstanceState != null){
            Bitmap bitmap = (Bitmap) savedInstanceState.getParcelable("bitmap");
            imageview.setImageBitmap(bitmap);
        }
    }*/

    //TODO - Also try to save the profile pic image so that it stays saved, becaue when you add the picture and then leae the page and come back it is not saved!

    /*public void btnClick(View v){

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, SELECTED_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SELECTED_PICTURE:
                if(resultCode == Activity.RESULT_OK){
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    Drawable d = new BitmapDrawable(getResources() ,yourSelectedImage);

                    iv.setBackground(d);
                }
                break;

            default:
            break;
        }
    }*/
}
