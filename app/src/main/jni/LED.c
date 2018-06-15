//
// Created by 김기윤 on 2018. 6. 14..
//
#include "com_example_gimgiyun_speedgame2_LED.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <jni.h>
#include <termios.h>
#include <sys/mman.h>
#include <errno.h>

JNIEXPORT jint JNICALL Java_com_example_gimgiyun_speedgame2_LED_Control
        (JNIEnv *env, jobject obj, jint data){

    int fd,ret;

    fd = open("/dev/fpga_led",O_WRONLY);

    if(fd<0) return errno;

    if(fd>0){

        data &= 0xff;
        ret = write(fd,&data,1);
        close(fd);
    } else
        return fd;

    if(ret == 1) return 0;

    return -1;
}