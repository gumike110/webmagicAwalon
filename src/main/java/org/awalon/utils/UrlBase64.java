package org.awalon.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by guming on 2016-08-16.
 */
public class UrlBase64 {
    public static final String UTF8 = "UTF-8";

    public static String encoder(String url) throws UnsupportedEncodingException{
        byte[] b = Base64.getUrlEncoder().encode(url.getBytes(UTF8));
        return new String(b,UTF8);
    }

    public static String decoder(String url) throws UnsupportedEncodingException{
        byte[] b = Base64.getUrlDecoder().decode(url.getBytes(UTF8));
        return new String(b,UTF8);
    }
}
