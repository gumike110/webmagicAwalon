package org.awalon.utils;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by guming on 2016-08-16.
 */
public class JinshanUtil {

    public static final String APPKEY = "k-20100".trim();
    public static final String APPSEC = "4b6f029fb050ff72f8ef8bec7a0c3879".trim();
    public static final String URI = "/phish/".trim();
    public static final String HOST = "open.pc120.com".trim();


    public static String execute(String url){
        Map<String,String> paramMap = null;
        try{
            paramMap = new HashMap<>();
            String q = UrlBase64.encoder(url);
            paramMap.put("appkey",APPKEY);
            paramMap.put("q",q);
            paramMap.put("timestamp",getTimestamp());
            String sign = sign(paramMap,URI,APPSEC);
            paramMap.put("sign",sign);
            String jinshanUrl = generateUri(paramMap);
            String json = getJsonString(jinshanUrl);
            return json;
        }catch (Exception e){
            return  null;
        }
    }

    private static String getTimestamp(){
        long time = System.currentTimeMillis();
        BigDecimal bdTime = new BigDecimal(time);
        BigDecimal bdValue = bdTime.divide(new BigDecimal(1000));
        BigDecimal bdScale = bdValue.setScale(3,BigDecimal.ROUND_HALF_UP);
        return bdScale.toString();
    }

    private static String sign(Map<String,String> paramMap,String uri,String serect){
        StringBuilder sb = new StringBuilder(URI+"?");
        ArrayList<String> paramNames = new ArrayList<>();
        paramNames.addAll(paramMap.keySet());
        Collections.sort(paramNames);
        for(String paramName : paramNames){
            sb.append(paramName).append("=").append(paramMap.get(paramName)).append("&");
        }
        return MD5.encrypt(sb.substring(0,sb.length()-1)+serect);
    }

    private static String generateUri(Map<String,String> paramMap){
        StringBuilder sb = new StringBuilder(HOST+URI+"?");
        for(Map.Entry<String,String> map : paramMap.entrySet()){
            sb.append(map.getKey()).append("=").append(map.getValue()).append("&");
        }
        return sb.toString().substring(0,sb.length()-1);
    }

    public static String getJsonString(String url){
        BufferedReader reader = null;
        try{
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new java.net.URI(url));
            HttpResponse response = client.execute(request);
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            return sb.toString();
        }catch (Exception e){
            return null;
        }
    }
}
