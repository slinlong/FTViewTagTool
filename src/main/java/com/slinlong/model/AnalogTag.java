package com.slinlong.model;

/**
 * @ClassName AnalogTag
 * @Description ģ������ǩ�࣬�̳�SETag��
 * @Author z2006
 * @Date 2020/8/9 19:12
 * @Version 1.0
 **/
public class AnalogTag extends SETag {
    private String tagType;

    public AnalogTag(){
        super();
        setTagType("A");
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
