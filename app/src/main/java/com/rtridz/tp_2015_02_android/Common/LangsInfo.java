package com.rtridz.tp_2015_02_android.common;

import java.util.HashMap;

public class LangsInfo {
    private HashMap<String, String> langPairs;
    private HashMap<String, String> langs;

    public LangsInfo() {
        langPairs = new HashMap<>();
        langs = new HashMap<>();
    }

    public HashMap<String, String> getLangPairs() {
        return langPairs;
    }

    public void putLangPairs(String key, String value) {
        this.langPairs.put(key, value);
    }

    public HashMap<String, String> getLangs() {
        return langs;
    }

    public void putLangs(String key, String value) {
        this.langs.put(key, value);
    }
}
