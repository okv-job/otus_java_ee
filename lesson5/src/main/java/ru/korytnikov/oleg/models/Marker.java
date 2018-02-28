package ru.korytnikov.oleg.models;

public class Marker {
    private String markerName;
    private String pageName;
    private String browserName;
    private String ipAddress;
    private String time;

    public Marker() {
    }

    public Marker(String markerName, String pageName, String browserName, String ip_addr, String time) {
        this.markerName = markerName;
        this.pageName = pageName;
        this.browserName = browserName;
        this.ipAddress = ip_addr;
        this.time = time;
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ip_addr) {
        this.ipAddress = ip_addr;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "markerName='" + markerName + '\'' +
                ", pageName='" + pageName + '\'' +
                ", browserName='" + browserName + '\'' +
                ", ip_addr='" + ipAddress + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
