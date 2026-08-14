package com.weekend.core.models;

public class MoreCard {

    private final String text;
    private final String pagePath;
    private final String icon;

    public MoreCard(String text, String pagePath, String icon) {
        this.text = text;
        this.pagePath = pagePath;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public String getPagePath() {
        return pagePath;
    }

    public String getIcon() {
        return icon;
    }
}