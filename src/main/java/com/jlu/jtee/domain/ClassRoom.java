package com.jlu.jtee.domain;

public class ClassRoom {
    private Integer id;
    private Integer MaxNum;
    private Integer NowNum;
    private String Content;
    private Integer Type;
    private String ClassName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxNum() {
        return MaxNum;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public void setMaxNum(Integer maxNum) {
        MaxNum = maxNum;
    }

    public Integer getNowNum() {
        return NowNum;
    }

    public void setNowNum(Integer nowNum) {
        NowNum = nowNum;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public ClassRoom(Integer id, Integer maxNum, Integer nowNum, String content, Integer type, String className) {
        this.id = id;
        MaxNum = maxNum;
        NowNum = nowNum;
        Content = content;
        Type = type;
        ClassName = className;
    }


}
