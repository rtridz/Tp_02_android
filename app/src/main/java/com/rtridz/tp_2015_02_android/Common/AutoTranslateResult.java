package com.rtridz.tp_2015_02_android.Common;

public class AutoTranslateResult {
    private String fromLangAbbrev;
    private String translatedText;

    public AutoTranslateResult(String fromLangAbbrev, String translatedText) {
        this.fromLangAbbrev = fromLangAbbrev;
        this.translatedText = translatedText;
    }

    public String getFromLangAbbrev() {
        return fromLangAbbrev;
    }

    public void setFromLangAbbrev(String fromLangAbbrev) {
        this.fromLangAbbrev = fromLangAbbrev;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}
