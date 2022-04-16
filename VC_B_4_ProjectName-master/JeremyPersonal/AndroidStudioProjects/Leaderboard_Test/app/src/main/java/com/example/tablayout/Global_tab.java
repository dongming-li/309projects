package com.example.tablayout;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

/**
 * Created by JeremyC on 10/3/2017.
 */

public class Global_tab extends Fragment
{
    String[][] tableLayoutG;
    String[] leaderboardHeaderG = {"Pos.","Name", "Fastest Time"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

            View layout_view = inflater.inflate(R.layout.global_tab, container, false);

            final TableView<String[]> tb = (TableView<String[]>) layout_view.findViewById(R.id.globalView);
            tb.setColumnCount(3);


            //populate table
            fill_inGlobal();

            //Adapters
            tb.setHeaderAdapter(new SimpleTableHeaderAdapter(getActivity(), leaderboardHeaderG));
            tb.setDataAdapter(new SimpleTableDataAdapter(getActivity(), tableLayoutG));

            return layout_view;
    }

    public void fill_inGlobal()
    {
        content table = new content();
        ArrayList<content> list = new ArrayList<>();

        table.setPosition("1");
        table.setPlayerName("NoobSlayer");
        table.setTimeTaken("1 min 25 secs");
        list.add(table);

        table = new content();
        table.setPosition("2");
        table.setPlayerName("Noob");
        table.setTimeTaken("2 min 25 secs");
        list.add(table);

        table = new content();
        table.setPosition("3");
        table.setPlayerName("Terry");
        table.setTimeTaken("2 min 45 secs");
        list.add(table);

        table = new content();
        table.setPosition("4");
        table.setPlayerName("John");
        table.setTimeTaken("3 min 34 secs");
        list.add(table);

        table = new content();
        table.setPosition("5");
        table.setPlayerName("Keyboard");
        table.setTimeTaken("3 min 42 secs");
        list.add(table);

        table = new content();
        table.setPosition("6");
        table.setPlayerName("pulley");
        table.setTimeTaken("4 min 02 secs");
        list.add(table);

        table = new content();
        table.setPosition("7");
        table.setPlayerName("Truck");
        table.setTimeTaken("4 min 25 secs");
        list.add(table);

        table = new content();
        table.setPosition("8");
        table.setPlayerName("push");
        table.setTimeTaken("4 min 25 secs");
        list.add(table);

        table = new content();
        table.setPosition("9");
        table.setPlayerName("sand");
        table.setTimeTaken("4 min 25 secs");
        list.add(table);

        table = new content();
        table.setPosition("10");
        table.setPlayerName("beach");
        table.setTimeTaken("4 min 25 secs");
        list.add(table);

        table = new content();
        table.setPosition("11");
        table.setPlayerName("laptop");
        table.setTimeTaken("4 min 25 secs");
        list.add(table);



        tableLayoutG= new String[list.size()][3];

        for(int i = 0; i < list.size(); i++)
        {
            content inputs = list.get(i);
            tableLayoutG[i][0] = inputs.getPosition();
            tableLayoutG[i][1] = inputs.getPlayerName();
            tableLayoutG[i][2] = inputs.getTimeTaken();
        }

    }

}
