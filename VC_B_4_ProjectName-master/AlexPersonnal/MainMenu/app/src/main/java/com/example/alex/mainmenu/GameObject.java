package com.example.alex.mainmenu;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Alex on 10/7/2017.
 */

public interface GameObject {
    public void draw(Canvas canvas);
    public void update();
    public void update(Point point);


}
