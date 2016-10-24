package com.ningjiahao.firstproject.QueryFragment;

import com.ningjiahao.firstproject.IntroduceFragment.Urltools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 甯宁寧 on 2016-10-09.
 */
public class QueryTools {




    /**
     * @param urlAll
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
    public static String query_request(String from, String to,String date) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl = QueryContants.QUERY_HEADURL + "?" + QueryContants.QUERY_FROM+ Urltools.encode(from)+
                QueryContants.QUERY_TO+Urltools.encode(to)+QueryContants.QUERY_DATE+date;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "4b64b9f93a1b2e06de75b16754e1195a");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
