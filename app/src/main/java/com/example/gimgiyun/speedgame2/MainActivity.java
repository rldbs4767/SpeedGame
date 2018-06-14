package com.example.gimgiyun.speedgame2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextLcd textLcd;
    int ret;
    //private DotMatrix dotMatrix;
    //private int tflag = 0x0;
    //int preflag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLcd = new TextLcd();
        //dotMatrix = new DotMatrix();
        //DotThread d_thread = new DotThread();

        //d_thread.start();
        textLcd.control("Let's","Go");


        Button start_button = (Button)findViewById(R.id.start_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //textLcd.UpdateValue("Love","You!");

                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

    }

}
