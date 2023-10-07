package com.slinlong.model;

/**
 * @ClassName DigtalTag
 * @Description 数字量标签类，继承SETag类
 * @Author z2006
 * @Date 2020/8/9 19:13
 * @Version 1.0
 **/
public class DigtalTag extends SETag {
    private String tagType;

    public DigtalTag(){
        super();
        setTagType("D");
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
