package coms309.sb_c_4_cydisc;

import android.os.Bundle;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.Serializable;
import java.net.URISyntaxException;


/**
 * Created by David Kirshenbaum on 11/7/2017.
 */

public class SocketManager implements Serializable{
    Socket socket;
    public String socketLobbyId;
    public String username;


    public SocketManager()
    {
        try{
            socket = IO.socket("http://proj-309-sb-c-4.cs.iastate.edu:3000");
            socket.connect();
        }catch(URISyntaxException e){
            e.printStackTrace();
        }
        socket.on("getLobbyId", getLobbyId);


    }

    public Socket getSocket()
    {
        return socket;
    }

    public void sendScore(int holeNumber, int holeScore)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
            json.put("hole", holeNumber);
            json.put("score", holeScore);
            json.put("lobbyId", socketLobbyId);
        }catch(JSONException e){
            e.printStackTrace();
        }

        socket.emit("addScore", json );
    }

    public void getScores(String lobbyId)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("lobbyId", lobbyId);
        }catch(JSONException e){
            e.printStackTrace();
        }
        socket.emit("getScores", json.toString());
    }

    public void saveGameToDatabase(String username)
    {
        JSONObject json = new JSONObject();
        try{
            json.put("username", username);

        }catch(JSONException e){
            e.printStackTrace();
        }

        socket.emit("saveGameToDatabase", json.toString());
    }

    public void createLobby(String username)
    {
        JSONObject json = new JSONObject();
        try{
            json.put("username", username);

        }catch(JSONException e){
            e.printStackTrace();
        }

        socket.emit("newLobby", json.toString());
    }


    public void joinLobby(String lobbyId, String username)
    {
        JSONObject json = new JSONObject();
        try {
            json.put("lobbyId", lobbyId);
            json.put("username", username);
        }catch(JSONException e){
            e.printStackTrace();
        }
        socket.emit("joinLobby", json.toString());
    }

    private Emitter.Listener getLobbyId = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

           String lobbyId = (String) args[0];
            socketLobbyId = lobbyId;


        }
    };

}
