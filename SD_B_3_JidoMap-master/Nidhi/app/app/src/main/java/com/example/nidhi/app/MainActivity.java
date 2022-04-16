package com.example.nidhi.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
 Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewByld(R.id.but1);
        b2=(Button)findViewByld(R.id.but2);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(MainActivity.this,home.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"hello button2",Toast.LENGTH_LONG).show();
            }
        });
    }
}


    }
}
