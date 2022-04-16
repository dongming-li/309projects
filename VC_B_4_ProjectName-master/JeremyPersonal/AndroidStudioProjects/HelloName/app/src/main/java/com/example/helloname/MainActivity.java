package com.example.helloname;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nameInput(View output)
    {
        TextView o = (TextView)findViewById(R.id.displayName);
        o.setText("Hello " + ((EditText)findViewById(R.id.enteredName)).getText().toString());
    }

}
