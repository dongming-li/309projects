package coms309.sb_c_4_cydisc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.koushikdutta.async.parser.JSONArrayParser;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;




public class ScorekeepingFragment extends Fragment implements View.OnClickListener {

    Button nextHole;
    Button sendScore;
    TextView tvHole;
    Button openScorecard;
    TextView holeOutput;
    EditText etScore;
    Button prevHole;
    Button submitGame;
    TextView tvLobbyId;
    int holeNumber;
    SocketManager socket;
    String username;
    String socketLobbyId;



    public ScorekeepingFragment() {
        // Required empty public constructor
    }

    public static ScorekeepingFragment newInstance() {
        return new ScorekeepingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scorekeeping, container, false);
        openScorecard = rootView.findViewById(R.id.openScorecard);
        nextHole = rootView.findViewById(R.id.bNext);
        prevHole = rootView.findViewById(R.id.bPrevious);
        etScore = rootView.findViewById(R.id.etScore);
        sendScore = rootView.findViewById(R.id.newGame);
        tvHole = rootView.findViewById(R.id.tvHole);
        holeOutput = rootView.findViewById(R.id.tvHole);
        tvLobbyId = rootView.findViewById(R.id.tvLobbyId);
        submitGame = rootView.findViewById(R.id.submitGame);
        holeNumber = 1;
        username = getArguments().getString("username");

        socket = (SocketManager) getArguments().getSerializable("socket");

        tvLobbyId.setText(socket.socketLobbyId);
        openScorecard.setOnClickListener(this);
        nextHole.setOnClickListener(this);
        prevHole.setOnClickListener(this);
        submitGame.setOnClickListener(this);
        sendScore.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.openScorecard:
                ScorecardFragment scorecardFragment = new ScorecardFragment();
                try {
                    fragment = scorecardFragment.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // insert the fragment, replacing any existing fragment(s)
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                break;
            case R.id.bNext:
                holeNumber++;
                if (holeNumber == 19) {
                    holeNumber = 1;
                }
                holeOutput.setText("Hole " + holeNumber);
                break;
            case R.id.bPrevious:
                holeNumber--;
                if (holeNumber == 0) {
                    holeNumber = 18;
                }
                holeOutput.setText("Hole " + holeNumber);
                break;

            case R.id.submitGame:
                break;

            case R.id.newGame:
                holeNumber++;
                holeOutput.setText("Hole " + holeNumber);
                String score = etScore.getText().toString();
                socket.sendScore(holeNumber, Integer.parseInt(score));

        }
    }
 };





