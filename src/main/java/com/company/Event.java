package com.company;

public class Event
{
    //stores data from JSON
    private String title;
    private String start_time;
    private String description;
    private String url;

   //creates constructor
    public Event(String title, String start_time, String description, String url) {
        this.title = title;
        this.start_time = start_time;
        this.description = description;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
