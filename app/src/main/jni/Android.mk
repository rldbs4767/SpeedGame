LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := TextLcd
LOCAL_SRC_FILES := TextLcd.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)

include $(CLEAR_VARS)

LOCAL_MODULE    := LED
LOCAL_SRC_FILES := LED.c
LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)


