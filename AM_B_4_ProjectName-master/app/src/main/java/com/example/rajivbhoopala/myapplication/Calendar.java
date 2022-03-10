package com.example.rajivbhoopala.myapplication;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;



/**
 * Created by rishabs on 10/4/17.
 */

public class Calendar extends Fragment {
    private static final String TAG = "CalendarActivity";


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Calendar");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        container.removeAllViewsInLayout(); // Clear screen to avoid blending.

        View view = inflater.inflate(R.layout.calendar_layout, container, false);

        displayCalendar(view);

        return view;
    }

    /**
     * Will display the selected calendar date to previous page
     * @param view display
     */
    private void displayCalendar(View view) {

        CalendarView theCalendarView = view.findViewById(R.id.calendarView);

        //i2 = day, i = year, i1 = month
        theCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy: " + date);

                LaunchCalendar launchCalendar = new LaunchCalendar();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                Bundle sendDate = new Bundle();
                sendDate.putString("date", date);
                launchCalendar.setArguments(sendDate);

                ft.replace(R.id.show_calendar_layout, launchCalendar);
                ft.commit();
            }
        });
    }
}
