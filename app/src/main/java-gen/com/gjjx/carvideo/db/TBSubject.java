package com.gjjx.carvideo.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TBSUBJECT".
 */
public class TBSubject {

    private Long id;
    private String name;
    private String summary;

    public TBSubject() {
    }

    public TBSubject(Long id) {
        this.id = id;
    }

    public TBSubject(Long id, String name, String summary) {
        this.id = id;
        this.name = name;
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
