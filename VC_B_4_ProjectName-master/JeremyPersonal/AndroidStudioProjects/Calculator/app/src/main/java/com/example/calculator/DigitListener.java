package com.example.calculator;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class DigitListener implements OnClickListener {
	public void onClick(View view) {
		View p = view.getRootView();
    	TextView tv = (TextView)p.findViewById(R.id.val);
    	StringBuilder val;

    	if(tv.getText().length() == 0 || view.getId() == R.id.clear) {
    		val = new StringBuilder("");
    	} else
    		val = new StringBuilder(tv.getText().toString());

    	switch(view.getId()) {
    		case R.id.val1:
    			val.append("1");
    			break;
    		case R.id.val2:
    			val.append("2");
    			break;
    		case R.id.val3:
    			val.append("3");
    			break;
    		case R.id.val4:
    			val.append("4");
    			break;
    		case R.id.val5:
    			val.append("5");
    			break;
    		case R.id.val6:
    			val.append("6");
    			break;
    		case R.id.val7:
    			val.append("7");
    			break;
    		case R.id.val8:
    			val.append("8");
    			break;
    		case R.id.val9:
    			val.append("9");
    			break;
    		case R.id.val0:
    			val.append("0");
    			break;
    		case R.id.dot:
    			val.append(".");
    	}
    	tv.setText(val.toString());
    }
}