package ru.korytnikov.oleg.models;

public class JsoupAnswer {
    private String url;
    private String title;

    public JsoupAnswer() {
    }

    public JsoupAnswer(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "JsoupAnswer{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
