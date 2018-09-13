package com.cardshop.cardshop.Utils;

import java.util.Random;

public class VertifyUtils {
    public static String vertifyCode = "";

    public static String createVertifyCode() {
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            code += rand.nextInt(10);
        }
        vertifyCode = code;
        return code;
    }
}
