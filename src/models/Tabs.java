package models;

import com.fasterxml.jackson.annotation.JsonSetter;


public class Tabs {
    private String someKey;

    public Tabs()
    {

    }
    public Tabs(String someKey) {
        this.someKey = someKey;
    }

    public String getSomeKey() {
        return someKey;
    }
    @JsonSetter("some key")
    public void setSomeKey(String someKey) {
        this.someKey = someKey;
    }
}
