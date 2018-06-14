package com.example.gimgiyun.speedgame2;

public class LED {

    static{
        System.loadLibrary("LED");

    }
    int data;

    public native int Control(int data);

    public LED(){

        data = 0xff;
        Control(data);

    }


}
