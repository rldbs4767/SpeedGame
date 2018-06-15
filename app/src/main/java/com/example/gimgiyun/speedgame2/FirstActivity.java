package com.example.gimgiyun.speedgame2;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Chronometer;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstActivity extends AppCompatActivity{

    int i;
    int cnt = 1;
    boolean last;

    private TextLcd textLcd;
    private LED led;

    Chronometer chronometer;

    List<Integer> First_list = new ArrayList<Integer>();
    Button buttons[] = new Button[9];//9개의 버튼을 배열로 나타냄
    int numBtnID[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8 ,R.id.button9};

    final Context context = this;

     void show(){

         final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);

         AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("오~"+(SystemClock.elapsedRealtime()-chronometer.getBase())/1000 +"초");
            builder.setMessage("다음 단계 Go?");

            builder.setPositiveButton("어!!",
            new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which){

                    led.Turn_on(0xff);
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    intent.putExtra("res",Long.toString((SystemClock.elapsedRealtime()-chronometer.getBase())/1000) );
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("아니!!",
            new DialogInterface.OnClickListener(){

                public void onClick(DialogInterface dialog, int which){

                    Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            builder.show();
        }

    //button이 마지막 숫자이면 리턴값을 true로 넘겨서 스위치문에서 break를 걸어준다!
    //아닐 시 계속 진행...
    boolean button_conrol(){

        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);

        if((Integer.parseInt(buttons[i].getText().toString()) - cnt) == 0){ //button의 숫자와 cnt가 같을 때,

            if(Integer.parseInt(buttons[i].getText().toString()) == 9) { //16일 때, 타이머 종료!
                buttons[i].setText("0");
                buttons[i].setEnabled(false); //버튼 비활성화
                chronometer.stop();
                show();
                return true;
            }
            buttons[i].setText("0");//버튼이 눌리면 0으로 바뀜!
            buttons[i].setEnabled(false); //버튼 비활성화
            cnt++;
        }

        return false;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        textLcd = new TextLcd();
        textLcd.UpdateValue("Now","Playing");
        led = new LED();

        led.Turn_on(0x0f);

        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());

        //9개의 버튼 사용
        for (int i = 0; i < 9; i++)
            buttons[i] = (Button) findViewById(numBtnID[i]);

        //1~9까지 숫자를 리스트에 넣기.
        for (int i = 0; i < 9; i++)
            First_list.add(i + 1);

        //list안의 숫자들 랜덤으로 섞기!
        Collections.shuffle(First_list);

        //버튼에 무작위로 배열!
        for (int i = 0; i < 9; i++)
            buttons[i].setText(String.valueOf(First_list.get(i)));

        //타이머 스타뜨!
        chronometer.start();

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.button1:

                        i = 0;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button2:

                        i = 1;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button3:

                        i = 2;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button4:

                        i = 3;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button5:

                        i = 4;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button6:

                        i = 5;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button7:

                        i = 6;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button8:

                        i = 7;
                        last = button_conrol();
                        if(last)break;
                        break;

                    case R.id.button9:

                        i = 8;
                        last = button_conrol();
                        if(last)break;
                        break;
                }
            }
        };
        for (int i = 0; i < 9; i++)
            buttons[i].setOnClickListener(listener);

    }
        @Override
        public boolean onKeyDown(int keycode, KeyEvent event){

            final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);

            switch (keycode) {

                case KeyEvent.KEYCODE_1: //1

                    i = 0;
                    last = button_conrol();
                    if(last)break;
                    break;

                case KeyEvent.KEYCODE_2: //2

                    i = 1;
                    last = button_conrol();
                    if(last)break;
                    break;

                case KeyEvent.KEYCODE_3: //3

                    i = 2;
                    last = button_conrol();
                    if(last)break;
                    break;

                //case KeyEvent.KEYCODE_4: //4

                case KeyEvent.KEYCODE_5: //5

                    i = 3;
                    last = button_conrol();
                    if(last)break;
                    break;

                case KeyEvent.KEYCODE_6: //6

                    i = 4;
                    last = button_conrol();
                    if(last)break;
                    break;

                case KeyEvent.KEYCODE_7: //7

                    i = 5;
                    last = button_conrol();
                    if(last)break;
                    break;

                //case KeyEvent.KEYCODE_8: //8

                case KeyEvent.KEYCODE_Q: //9

                    i = 6;
                    last = button_conrol();
                    if(last)break;
                    break;

                case KeyEvent.KEYCODE_R: //10

                    i = 7;
                    last = button_conrol();
                    if(last)break;
                    break;

                case KeyEvent.KEYCODE_T: //11

                    i = 8;
                    last = button_conrol();
                    if(last)break;
                    break;

                //case KeyEvent.KEYCODE_U: //12

                //case KeyEvent.KEYCODE_E: //13
                //case KeyEvent.KEYCODE_I: //14
                //case KeyEvent.KEYCODE_O: //15
                //case KeyEvent.KEYCODE_P: //16

            }
            return false;
        }
    }

