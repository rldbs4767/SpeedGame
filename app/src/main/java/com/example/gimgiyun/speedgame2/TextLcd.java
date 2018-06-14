package com.example.gimgiyun.speedgame2;

import android.util.Log;

public class TextLcd{

    static{
        System.loadLibrary("TextLcd");

    }

    public native boolean open();
    public native int control(String str, String str2);
    public native int clear();
    public native boolean close();
    public native int IOCtlDisplay (boolean data);
    public native int IOCtlReturnHome ();
    public native int IOCtlCursor(boolean data);
    public native int IOCtlBlink(boolean data);


    public TextLcd(){
        boolean ret = open();
        if (!ret)
            Log.d("TextLCD", "Open fail");
        clear();
        IOCtlReturnHome();
        IOCtlDisplay(true);
        //IOCtlCursor(false);
        //IOCtlBlink(false);
    }

    void UpdateValue(String str, String str2){
        clear();
        control(str, str2);
    }



}