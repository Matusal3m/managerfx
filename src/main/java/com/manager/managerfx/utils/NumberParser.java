package com.manager.managerfx.utils;

import com.manager.managerfx.model.entities.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class NumberParser {
    public static Double parseDecimal(CharSequence chars) {
        double result = 0;
        int i = 0;
        int len = chars.length();

        if (chars.charAt(0) == '-') {
            return null;
        }

        for (; i < len; i++) {
            char c = chars.charAt(i);
            if (c == '.' || c == ',') {
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

    public static List<Product> formatDoubleFromList(List<Product> products) {
        for (Product product : products) {
            double price = product.getPrice();
            BigDecimal bd = new BigDecimal(price)
                    .setScale(2, RoundingMode.HALF_DOWN);
            product.setPrice(bd.doubleValue());
        }
        return products;
    }

}
