package com.example.calculator;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class OperatorListener implements OnClickListener {
	Double res = null;
	int prevop;

    private double calc(double v1, double v2, int op) {
    	switch(op) {
	    case R.id.opPlus:
	    	return v1 + v2;
	    case R.id.opMinus:
	    	return v1 - v2;
	    case R.id.opMult:
	    	return v1 * v2;
	    case R.id.opDiv:
	    	return v1 / v2;
	    }
	    return 0.0; // should never happen
    }

	public void onClick(View view) {
		View p = view.getRootView();
    	TextView tv = (TextView)p.findViewById(R.id.val);
    	String val = tv.getText().toString();

    	if(res == null) {
    		res = Double.parseDouble(val.toString());
    		prevop = view.getId();
    		tv.setText("");
    	} else {
    		if(view.getId() == R.id.opEq && val != null) {
				res = calc(res, Double.parseDouble(val.toString()), prevop);
				prevop = -1;
				tv.setText(Double.toString(res));
				res = null;
			} else if(prevop!=-1 && val != null){
				res = calc(res, Double.parseDouble(val.toString()), view.getId());
				prevop = view.getId();
				tv.setText("");
			}
		}
    }
}