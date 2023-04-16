package com.example.hollowcalculator.objects;

public class HelpItem {
    private String keyword;
    private String syntax;
    private String description;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HelpItem(String keyword, String syntax, String description) {
        this.keyword = keyword;
        this.syntax = syntax;
        this.description = description;
    }


}

