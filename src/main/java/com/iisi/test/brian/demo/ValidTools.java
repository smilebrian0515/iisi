package com.iisi.test.brian.demo;

import java.io.UnsupportedEncodingException;

public class ValidTools {
    public boolean isBig5Encoding(byte[] bytes) throws UnsupportedEncodingException {
        String str = new String(bytes, "BIG5");
        return (bytes.length == str.getBytes("BIG5").length);
    }
}
