package com.example.tablayout.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by JeremyC on 10/19/2017.
 */

import com.example.tablayout.content;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;


import static com.example.tablayout.utils.VolleySingleton.SERVER_ADDRESS;


public class LeaderboardServReq
{
    static String url = SERVER_ADDRESS + "/leaderboardNetwork/leaderboard_stats.php";
    private static ArrayList<content> list = new ArrayList<content>(); //list to contained with retrieved data.

    public static ArrayList<content> FetchData (String link)
    {
        class procData extends AsyncTask<String, Void, String>
        {

            private boolean ready = false;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                String s = strings[0];
                return getResponseFrom(s);
            }

            @Override
            protected void onPostExecute(String response) {
                if (!response.contains("ERROR")) {
                    String[] players = response.split(Pattern.quote("#"));
                    Gson gson = new Gson();
                    for (String p : players) {
                        PlayerLeaderboard p1 = gson.fromJson(p, PlayerLeaderboard.class);
                        list.add(p1.toContent());
                    }
                }
                ready = true;
            }

            public ArrayList<content> getData()
            {
                while (!ready) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               return list;
            }
        }

        procData fetch = new procData();
        fetch.execute(url);
        return fetch.getData();


    }




    /**
     * Small helper to get the response from a url.
     * @param string the url to call
     * @return the response we want.
     */

    @NonNull
    private static String getResponseFrom(String string)
    {
        String s = string;
        BufferedReader br = null;
        try{
            URL url = new URL(s);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String res;
            while((res = br.readLine())!= null){
                sb.append(res);

            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "ERROR";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

}

