package com.stone.spider.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {


    /**
     * 连接url，取到读取的html内容数据
     * @param urlStr
     * @return html内容
     */
    public static String getURLConnect(String urlStr) {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        URL url = null;

        StringBuffer htmlContext = new StringBuffer();
        try {
            url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3 * 1000);

            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            String lines = null;
            while ((lines = br.readLine()) != null) {
                htmlContext.append(lines).append("\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    isr.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return htmlContext.toString();

    }

}
