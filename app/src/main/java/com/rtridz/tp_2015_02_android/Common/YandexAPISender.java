package com.rtridz.tp_2015_02_android.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class YandexAPISender {
    private static final String API_KEY =
            "trnsl.1.1.20150224T085428Z.f41db3502553b605.a80b80e903ffbed3ec67ebe456f19f83d6fa0b3b";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String YA_TR_URL = "https://translate.yandex.net/api/v1.5/tr.json/";

    private static LangsInfo getLangsInfo()  {
        LangsInfo langsInfo = new LangsInfo();
        StringBuilder url = new StringBuilder(YA_TR_URL);
        url.append("getLangs?key=" + API_KEY + "&ui=ru");
        URL getReq;
        try {
            getReq = new URL(url.toString());
        } catch (MalformedURLException e) {
            return null;
        }

        HttpURLConnection con;
        try {
            con = (HttpURLConnection)getReq.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            return null;
        }
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Charset", "UTF-8");
        StringBuilder response = new StringBuilder();
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = con.getInputStream();
            if (con.getResponseCode() != 200) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignore) {}
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignore) {}
            }
        }
        JSONObject jsonResp;
        try {
            jsonResp = new JSONObject(response.toString());
            JSONArray langsRoutes = jsonResp.getJSONArray("dirs");
            String[] pair;
            for (int i = 0; i < langsRoutes.length(); ++i) {
                pair = langsRoutes.getString(i).split("-");
                langsInfo.putLangPairs(pair[0], pair[1]);
            }
            JSONObject langsAbbrev = jsonResp.getJSONObject("langs");
            Iterator<String> langIter = langsAbbrev.keys();
            String key;
            while (langIter.hasNext()) {
                key = langIter.next();
                langsInfo.putLangs(key, langsAbbrev.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return langsInfo;
    }

    private static String getTextLang(String text) {
        StringBuilder url = new StringBuilder(YA_TR_URL);
        url.append("detect?key=" + API_KEY + "&text=");
        url.append(text);
        URL getReq;
        try {
            getReq = new URL(url.toString());
        } catch (MalformedURLException e) {
            return null;
        }

        HttpURLConnection con;
        try {
            con = (HttpURLConnection)getReq.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            return null;
        }
        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Charset", "UTF-8");
        StringBuilder response = new StringBuilder();
        try (InputStream is = con.getInputStream()) {
            if (con.getResponseCode() != 200) {
                return null;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
        JSONObject jsonResp;
        try {
            jsonResp = new JSONObject(response.toString());
            int code = jsonResp.getInt("code");
            if (code != 200) {
                return null;
            }
            return jsonResp.getString("lang");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String translate(String text, String fromLang, String toLang) {
        if (text.length() > 10_000) {
            return null;
        }
        StringBuilder url = new StringBuilder(YA_TR_URL);
        url.append("translate?key=" + API_KEY + "&text=");
        url.append(text);
        url.append("&lang=" + fromLang + "-" + toLang + "&format=plain");
        URL getReq;
        try {
            getReq = new URL(url.toString());
        } catch (MalformedURLException e) {
            return null;
        }

        HttpURLConnection con;
        try {
            con = (HttpURLConnection)getReq.openConnection();
            con.setRequestMethod("POST");
        } catch (IOException e) {
            return null;
        }
        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Charset", "UTF-8");
        StringBuilder response = new StringBuilder();
        try (InputStream is = con.getInputStream()) {
            if (con.getResponseCode() != 200) {
                return null;
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                return null;
            }
        } catch (IOException e2) {
            return null;
        }
        JSONObject jsonResp;
        try {
            jsonResp = new JSONObject(response.toString());
            int code = jsonResp.getInt("code");
            if (code != 200) {
                return null;
            }
            return jsonResp.getJSONArray("text").getString(0);
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
}
