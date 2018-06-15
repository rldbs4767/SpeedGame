package com.example.gimgiyun.speedgame2;

import android.os.SystemClock;
import android.util.Log;

public class LED {

    static{
        System.loadLibrary("LED");

    }

    int data;

    public native int Control(int data);

    void Turn_on(int data){

        Control(data);

    }

}
