package com.manager.managerfx.utils;

public class NumberParser {
    public static Double formatDecimal(CharSequence chars) {
        double result = 0;
        int i = 0;
        int len = chars.length();

        if (chars.charAt(0) == '-') {
            return null;
        }

        for (; i < len; i++) {
            char c = chars.charAt(i);
            if (c == '.') {
                i++;
                break;
            }
            result = result * 10 + (c - '0');
        }

        double fraction = 0.1;
        for (; i < len; i++) {
            char c = chars.charAt(i);
            result += (c - '0') * fraction;
            fraction *= 0.1;
        }

        return result;
    }
}
