package coms309.sb_c_4_cydisc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiscBagFragment extends Fragment {

    public DiscBagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.disc_bag_activity, container, false);

        String[] myItems = {"Disc 1", "Disc 2", "Disc 3", "Disc 4", "Disc 5", "Disc 6", "Disc 7", "Disc 8"};
        ListView listView = (ListView) view.findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, myItems);
        listView.setAdapter(adapter);

        return view;
    }

    //Fragment content here
}