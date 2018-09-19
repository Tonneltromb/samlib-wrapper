package com.ymatin.samlib.common.util;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class NumberUtilsTest {

    @Test
    public void isNumber_test() {
        String s = "xxx123 33 g";
        boolean result = NumberUtils.isNumber(s);
        assertTrue(!result);

        s = "-1";
        result = NumberUtils.isNumber(s);
        assertTrue(result);

        s = "-D";
        result = NumberUtils.isNumber(s);
        assertTrue(!result);

        s = "-1D";
        result = NumberUtils.isNumber(s);
        assertTrue(!result);

        s = "123";
        result = NumberUtils.isNumber(s);
        assertTrue(result);

        s = "-";
        result = NumberUtils.isNumber(s);
        assertTrue(!result);

        s = "-0";
        result = NumberUtils.isNumber(s);
        assertTrue(result);
    }

}