package coms309.sb_c_4_cydisc;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class ScorecardFragment extends Fragment  {
    TableLayout scoreTable;

    public ScorecardFragment() {
        // Required empty public constructor
    }

    public static ScorecardFragment newInstance() {
        return new ScorecardFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTable();
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.scorecard, container, false);
        scoreTable = rootView.findViewById(R.id.scoreTable);

        return inflater.inflate(R.layout.scorecard, container, false);
    }

    private void createTable()
    {
        TextView currentNode;
        TableRow currentRow;
        for(int i = 0; i < 4; i++)
        {
            currentRow = (TableRow) scoreTable.getChildAt(1);
            currentNode = (TextView) currentRow.getChildAt(i);
            currentNode.setText("David");
        }
        for(int i = 1; i < 19; i++) {
            currentRow = (TableRow) scoreTable.getChildAt(i);
            for(int j = 0; j < 4; j++)
            {
                currentNode = (TextView) currentRow.getChildAt(j);
                currentNode.setText("hi");
            }
        }

    }


}