package com.mj.room.util;

public class RoomUtils {

    public static String toStringCheckOnNull(Object obj, String alt) {
        return obj != null ? obj.toString() : alt;
    }
}
