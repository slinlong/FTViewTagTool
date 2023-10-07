package com.slinlong.model;

/**
 * @ClassName SETag
 * @Description Tag对象，AnalogTag、DigtalTag、StringTag继承此类
 * @Author z2006
 * @Date 2020/8/10 11:09
 * @Version 1.0
 **/
public class SETag {
    private String tagName;
    private String tagDescription;
    private String securityCode;
    private String address;

    public SETag(){

    }

    public SETag(String tagName,String address){
        setTagName(tagName);
        setTagDescription("");
        setSecurityCode("*");
        setAddress(address);
    }

    public SETag(String tagName,String tagDescription,String address){
        setTagName(tagName);
        setTagDescription(tagDescription);
        setSecurityCode("*");
        setAddress(address);
    }

    public SETag(String tagName,String tagDescription,String securityCode,String address){
        setTagName(tagName);
        setTagDescription(tagDescription);
        setSecurityCode(securityCode);
        setAddress(address);
    }


    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
