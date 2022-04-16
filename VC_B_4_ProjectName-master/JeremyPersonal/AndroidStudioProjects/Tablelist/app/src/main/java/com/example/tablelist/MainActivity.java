package com.example.tablelist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MainActivity extends AppCompatActivity {

    String[] leaderboardHeader = {"Pos.","Name", "Fastest Time"};
    String[][] tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TableView<String[]> tb = (TableView<String[]>)  findViewById(R.id.tableView);
        tb.setColumnCount(3);
        tb.setHeaderBackgroundColor(Color.parseColor("2ecc71"));

        fill_inData();

        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this,leaderboardHeader));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableLayout));
    }

    private void fill_inData()
    {
        content table = new content();
        ArrayList<content>  list = new ArrayList<>();

        table.setPostion("1");
        table.setPlayerName("NoobSlayer");
        table.setTimeTaken("1 min 25 secs");
        list.add(table);

        table = new content();
        table.setPostion("2");
        table.setPlayerName("Noob");
        table.setTimeTaken("2 min 25 secs");
        list.add(table);

        table = new content();
        table.setPostion("3");
        table.setPlayerName("Terry");
        table.setTimeTaken("2 min 45 secs");
        list.add(table);

        table = new content();
        table.setPostion("4");
        table.setPlayerName("John");
        table.setTimeTaken("3 min 34 secs");
        list.add(table);

        table = new content();
        table.setPostion("5");
        table.setPlayerName("Keyboard");
        table.setTimeTaken("3 min 42 secs");
        list.add(table);

        table = new content();
        table.setPostion("6");
        table.setPlayerName("pulley");
        table.setTimeTaken("4 min 02 secs");
        list.add(table);

        table = new content();
        table.setPostion("7");
        table.setPlayerName("Truck");
        table.setTimeTaken("4 min 25 secs");
        list.add(table);

        tableLayout= new String[list.size()][3];

        for(int i = 0; i < list.size(); i++)
        {
            content inputs = list.get(i);
            tableLayout[i][0] = inputs.getPosition();
            tableLayout[i][1] = inputs.getPlayerName();
            tableLayout[i][2] = inputs.getTimeTaken();
        }

    }
}
