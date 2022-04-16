package com.example.calculator;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends Activity
{

	OperatorListener ol = new OperatorListener();
	DigitListener dl = new DigitListener();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Set listeners for operators
        ((Button)findViewById(R.id.opPlus)).setOnClickListener(ol);
        ((Button)findViewById(R.id.opMinus)).setOnClickListener(ol);
        ((Button)findViewById(R.id.opMult)).setOnClickListener(ol);
        ((Button)findViewById(R.id.opDiv)).setOnClickListener(ol);
        ((Button)findViewById(R.id.opEq)).setOnClickListener(ol);

        // Set listeners for digits
        ((Button)findViewById(R.id.val1)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val2)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val3)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val4)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val5)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val6)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val7)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val8)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val9)).setOnClickListener(dl);
        ((Button)findViewById(R.id.val0)).setOnClickListener(dl);
        ((Button)findViewById(R.id.dot)).setOnClickListener(dl);
        ((Button)findViewById(R.id.clear)).setOnClickListener(dl);
    }
}
