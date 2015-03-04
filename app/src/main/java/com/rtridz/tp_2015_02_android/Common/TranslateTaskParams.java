package com.rtridz.tp_2015_02_android.Common;

public class TranslateTaskParams {
    private String fromLang; // may be null (which auto translate)
    private String toLang;
    private String text;

    public TranslateTaskParams(String fromLang, String toLang, String text) {
        this.fromLang = fromLang;
        this.toLang = toLang;
        this.text = text;
    }

    public String getFromLang() {
        return fromLang;
    }

    public void setFromLang(String fromLang) {
        this.fromLang = fromLang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getToLang() {
        return toLang;
    }

    public void setToLang(String toLang) {
        this.toLang = toLang;
    }
}
