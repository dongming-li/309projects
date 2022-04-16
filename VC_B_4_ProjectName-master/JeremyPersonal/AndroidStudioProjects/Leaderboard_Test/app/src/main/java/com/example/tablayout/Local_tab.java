package com.example.tablayout;



import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;


import android.widget.Toast;


import com.example.tablayout.utils.LeaderboardServReq;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;


/**
 * Created by JeremyC on 10/3/2017.
 */
import static com.example.tablayout.utils.VolleySingleton.SERVER_ADDRESS;

public class Local_tab extends Fragment
{
    String[][] tableLayout;
    private static boolean doOnce = false;
    String[] leaderboardHeader = {"Pos.", "Name", "Most Wins"};
    private static ArrayList<content> list = new ArrayList<>();
    String url = SERVER_ADDRESS + "/leaderboardNetwork/leaderboard_stats.php";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout_view = inflater.inflate(R.layout.local_tab, container, false);

        final TableView<String[]> tb = (TableView<String[]>) layout_view.findViewById(R.id.localView);
        tb.setColumnCount(3);


        //populate table
        list = LeaderboardServReq.FetchData(url);

        setTable();

        //Adapters
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), leaderboardHeader));
        tb.setDataAdapter(new SimpleTableDataAdapter(getActivity(), tableLayout));

        return layout_view;
    }



    private void setTable() {
        tableLayout = new String[list.size()][3];

        for (int i = 0; i < list.size(); i++) {
            content inputs = list.get(i);
            tableLayout[i][0] = Integer.toString(i); //position
            tableLayout[i][1] = inputs.getPlayerName(); //username
            tableLayout[i][2] = inputs.getRatio(); //win-lose ration
        }

        for (int j = 0; j < list.size(); j++) {
            content inputs = list.get(j);
            tableLayout[j][0] = inputs.getPosition();
            tableLayout[j][1] = inputs.getPlayerName();
            tableLayout[j][2] = inputs.getnum_of_wins();
        }


    }

}













