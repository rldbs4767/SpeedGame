package com.example.gimgiyun.speedgame2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextLcd textLcd;
    private LED led;
    int ret;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLcd = new TextLcd();
        led = new LED();

        textLcd.UpdateValue("Are you ready?","Let's Go");
        led.Turn_on(0x00);

        Button start_button = (Button)findViewById(R.id.start_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                led.Turn_on(0xff);
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

    }

}
