package com.slinlong.model;

/**
 * @ClassName ExcelModel
 * @Description Excel Tag对象模型
 * @Author z2006
 * @Date 2020/8/10 7:42
 * @Version 1.0
 **/
public class ExcelModel {
    private String tagType;
    private String alarmLevel;
    private String alarmType;
    private String alarmClassify;
    private String tagName;
    private String tagDescription;
    private String plcTagName;

    public ExcelModel(){

    }

    public ExcelModel(String tagType, String alarmLevel, String alarmType,String alarmClassify, String tagName, String tagDescription, String plcTagName) {
        this.tagType = tagType;
        this.alarmLevel = alarmLevel;
        this.alarmType = alarmType;
        this.alarmClassify=alarmClassify;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
        this.plcTagName = plcTagName;
    }

    @Override
    public String toString() {
        return "ExcelModel{" +
                "tagType='" + tagType + '\'' +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", alarmClassify='" + alarmClassify + '\'' +
                ", tagName='" + tagName + '\'' +
                ", tagDescription='" + tagDescription + '\'' +
                ", plcTagName='" + plcTagName + '\'' +
                '}';
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
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

    public String getPlcTagName() {
        return plcTagName;
    }

    public void setPlcTagName(String plcTagName) {
        this.plcTagName = plcTagName;
    }

    public String getAlarmClassify() {
        return alarmClassify;
    }

    public void setAlarmClassify(String alarmClassify) {
        this.alarmClassify = alarmClassify;
    }
}
