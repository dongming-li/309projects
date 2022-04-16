package coms309.sb_c_4_cydisc;

import android.os.Bundle;

/**
 * Created by Brian Newman on 11/8/2017.
 */

public interface OnDataPass {
    public void onDataPass(Bundle data);
    public String getUsername();
    public int getUserId(); ///added by bwwenwen
}