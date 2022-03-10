package com.example.rajivbhoopala.myapplication;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatWithUser extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Chat With User");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_with_user, container, false);
        Button chatButton = view.findViewById(R.id.chatWithUser);
        chatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new Utility(getActivity()).launchActivity(getActivity(), Login.class);
            }
            //   }
        });
        return view;
    }

}
