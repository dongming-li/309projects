package com.example.alex.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private ImageSwitcher imageSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ImageView imageView_penguin = (ImageView) findViewById(R.id.imageView_penguin);
       // imageView_penguin.setImageResource(R.drawable.icon);


        ImageView imageView_goblin = (ImageView) findViewById(R.id.goblin2);
        imageView_goblin.setImageResource(R.drawable.aligned_goblin2);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher1);


        //When this line executes the app crashes
      //  imageSwitcher.setImageResource(R.drawable.aligned_goblin2);
        //imageSwitcher.setImageResource(R.mipmap.ic_launcher);



 imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
        @Override
        public View makeView() {
            ImageView  myview = new ImageView(getApplicationContext());
            return myview;
        }
    });
    Animation in = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
    Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
}


}
