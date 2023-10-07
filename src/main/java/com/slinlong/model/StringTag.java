package com.slinlong.model;

/**
 * @ClassName StringTag
 * @Description ×Ö·û´®±êÇ©Àà£¬¼Ì³ÐSETagÀà
 * @Author z2006
 * @Date 2020/8/11 20:27
 * @Version 1.0
 **/
public class StringTag extends SETag {
    private String tagType;

    public StringTag(){
        super();
        setTagType("S");
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
