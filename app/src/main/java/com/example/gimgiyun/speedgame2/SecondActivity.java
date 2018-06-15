package com.example.gimgiyun.speedgame2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondActivity extends AppCompatActivity{

    int i;
    int cnt = 1; //숫자의 순서를 맞추기 위한 변수.
    boolean last; //숫자의 마지막을 확인하기 위한 변수.
    int data[] = {0x01,0x3,0x7,0x15,0x1f,0x3f,0x7f,0xff};

    private TextLcd textLcd;
    private LED led;
    Chronometer chronometer;//타이머

    List<Integer> Second_list = new ArrayList<Integer>(); //16개의 숫자를 list에 넣는다.
    Button buttons[] = new Button[16]; //16개의 버튼을 배열로 나타냄
    int numBtnID[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8 ,R.id.button9,R.id.button10, R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16};

    void LED_playing(){

        led = new LED();
        for(int j=0; j<2; j++){

            for(int i=0; i<8; i++) {
                led.Turn_on(data[i]);
                SystemClock.sleep(50);
            }
        }

    }

    //알림 띄우는 부분.
    void show(){

        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);

        Intent intent2 = getIntent();
        String res = intent2.getStringExtra("res"); //전 단계에서 몇 초가 걸렸는지를 받아오는 변수.
        long Add = Integer.parseInt(res)+(SystemClock.elapsedRealtime()-chronometer.getBase())/1000; //전 단계와 현재 단계에서 걸린 시간의 합!

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("오~"+(SystemClock.elapsedRealtime()-chronometer.getBase())/1000 +"초");
        builder.setMessage("잘했으! 두 단계 합치니까"+ (Add-2) + "초 걸리네"); //1단계에서 2단계로 이동할 때, 2초정도가 흘러가기 때문에 2초를 빼준다.
                                                                        //시간의 오차는 1초정도 난다.
        builder.setPositiveButton("끝내기",
                new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which){

                        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                });

        builder.show();
    }

    //button이 마지막 숫자이면 리턴값을 true로 넘겨서 스위치문에서 break를 걸어준다!
    //아닐 시 계속 진행...
    boolean button_conrol(){

        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
        textLcd = new TextLcd();
        led = new LED();

        if((Integer.parseInt(buttons[i].getText().toString()) - cnt) == 0){ //button의 숫자와 cnt가 같을 때,

            if(Integer.parseInt(buttons[i].getText().toString()) == 16) { //16일 때, 타이머 종료!
                buttons[i].setText("0");
                buttons[i].setEnabled(false); //버튼 비활성화
                chronometer.stop();
                LED_playing(); //LED 깜빡깜빡!
                textLcd.UpdateValue("Finish!","Good-Bye");
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
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);

            final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
            chronometer.setBase(SystemClock.elapsedRealtime());
            led = new LED();
            led.Turn_on(0xff);

            //16개의 버튼 사용
            for(int i=0; i<16; i++)
               buttons[i] = (Button)findViewById(numBtnID[i]);

           //1~16까지 숫자를 리스트에 넣기.
            for(int i=0; i<16; i++)
                Second_list.add(i+1);

            //list안의 숫자들 랜덤으로 섞기!
            Collections.shuffle(Second_list);

            //버튼에 무작위로 배열!
            for(int i=0; i<16; i++)
                buttons[i].setText(String.valueOf(Second_list.get(i)));

            //타이머 스타뜨!
            chronometer.start();

            View.OnClickListener listener = new View.OnClickListener(){

                @Override
                public void onClick(View view){


                    switch(view.getId())          //cnt는 숫자를 1~16순서대로 누르기 위해서 선언했다.
                    {                            //순서대로 숫자를 누르면 cnt가 증가한다.
                                                //cnt의 초기값을 1로 설정. 숫자가 증가할 때마다 cnt값도 증가시켜서 숫자의 순서를 지켜야만 다음 숫자를 누를 수 있게 control
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

                        case R.id.button10:

                            i = 9;
                            last = button_conrol();
                            if(last)break;
                            break;

                        case R.id.button11:

                            i = 10;
                            last = button_conrol();
                            if(last)break;
                            break;

                        case R.id.button12:

                            i = 11;
                            last = button_conrol();
                            if(last)break;
                            break;

                        case R.id.button13:

                            i = 12;
                            last = button_conrol();
                            if(last)break;
                            break;

                        case R.id.button14:

                            i = 13;
                            last = button_conrol();
                            if(last)break;
                            break;

                        case R.id.button15:

                            i=14;
                            last = button_conrol();
                            if(last)break;
                            break;

                          case R.id.button16:

                            i = 15;
                            last = button_conrol();
                            if(last)break;
                            break;
                    }
                }
            };
                for(int i=0; i<16; i++)
                    buttons[i].setOnClickListener(listener);

        }
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event){

        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer);
        //Toast.makeText(this,"버튼 눌렸다",Toast.LENGTH_LONG).show();

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

            case KeyEvent.KEYCODE_4://4 ,61 ---30

                i = 3;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_5: //5

                i = 4;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_6: //6

                i = 5;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_7: //7

                i = 6;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_8: //8, 62----31

                i = 7;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_Q: //9

                i = 8;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_R: //10

                i = 9;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_T: //11

                i = 10;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_U: //12 ------32

                i = 11;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_E: //13-------33

                i = 12;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_I: //14

                i = 13;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_O: //15-------34

                i = 14;
                last = button_conrol();
                if(last)break;
                break;

            case KeyEvent.KEYCODE_P: //16-------35

                i = 15;
                last = button_conrol();
                if(last)break;
                break;
        }

        return false;
    }


}
