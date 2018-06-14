//
// Created by 김기윤 on 2018. 6. 12..
//
#include "com_example_gimgiyun_speedgame2_DotMatrix.h"
#include <fcntl.h>
#include <errno.h>

int fd = -1;

JNIEXPORT jboolean JNICALL Java_com_example_gimgiyun_speedgame2_DotMatrix_openDotMatrix
  (JNIEnv *env, jobject obj)
 {
        fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);
            if (fd < 0) return -errno;
            else return 1;

    }


JNIEXPORT jint JNICALL Java_jniActivity_DotMatrix_Control
        (JNIEnv *env, jobject obj, jstring data) {

    const char *buf;
    int ret, len;
    char str[100];

    buf = (*env)->GetStringUTFChars(env, data, 0);
    len = (*env)->GetStringLength(env, data);

    fd = open("/dev/fpga_dotmatrix", O_RDWR | O_SYNC);

    if(fd != -1){

      ret = write(fd, buf, len);
      close(fd);
    }
    else
        return -errno;

    return 0;

}
/*
JNIEXPORT jboolean JNICALL Java_jniActivity_DotMatrix_Close
        (JNIEnv *env, jobject obj) {

          if (fd < 0) return -1;
            close(fd);
            fd = -1;
            return 1;

    return 1;
}*/