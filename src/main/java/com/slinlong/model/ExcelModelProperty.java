package com.slinlong.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @ClassName ExcelModelProperty
 * @Description Excel Tag对象模型,用于动态绑定JavaFX表格
 * @Author z2006
 * @Date 2020/8/10 18:34
 * @Version 1.0
 **/
public class ExcelModelProperty {
    private StringProperty tagType=new SimpleStringProperty(this,"tagType",null);;
    private StringProperty alarmLevel=new SimpleStringProperty(this,"alarmLevel",null);;
    private StringProperty alarmType=new SimpleStringProperty(this,"alarmType",null);;

    private StringProperty alarmClassify=new SimpleStringProperty(this,"alarmClassify",null);;
    private StringProperty tagName=new SimpleStringProperty(this,"tagName",null);;
    private StringProperty tagDescription=new SimpleStringProperty(this,"tagDescription",null);;
    private StringProperty plcTagName=new SimpleStringProperty(this,"plcTagName",null);;


    public ExcelModelProperty(){

    }

    /**
     * 构造函数，利用ExcelModel的普通类构造ExcelModelProperty类
     * @param excelModel
     */
    public ExcelModelProperty(ExcelModel excelModel){
        setTagType(excelModel.getTagType());
        setAlarmLevel(excelModel.getAlarmLevel());
        setAlarmType(excelModel.getAlarmType());
        setAlarmClassify(excelModel.getAlarmClassify());
        setTagName(excelModel.getTagName());
        setTagDescription(excelModel.getTagDescription());
        setPlcTagName(excelModel.getPlcTagName());
    }

    public ExcelModelProperty(String tagType, String alarmLevel, String alarmType,String alarmClassify, String tagName, String tagDescription, String plcTagName) {
        setTagType(tagType);
        setAlarmLevel(alarmLevel);
        setAlarmType(alarmType);
        setAlarmClassify(alarmClassify);
        setTagName(tagName);
        setTagDescription(tagDescription);
        setPlcTagName(plcTagName);
    }

    /**
     * 复制对象
     * @param em
     */
    public void copy(ExcelModelProperty em){
        this.setTagName(em.getTagName());
        this.setTagType(em.getTagType());
        this.setTagDescription(em.getTagDescription());
        this.setPlcTagName(em.getPlcTagName());
        this.setAlarmType(em.getAlarmType());
        this.setAlarmClassify(em.getAlarmClassify());
        this.setAlarmLevel(em.getAlarmLevel());
    }



    public String getTagType(){
        return this.tagType.get();
    }
    public String getAlarmLevel(){
        return this.alarmLevel.get();
    }
    public String getAlarmType(){
        return this.alarmType.get();
    }
    public String getAlarmClassify(){
        return this.alarmClassify.get();
    }
    public String getTagName(){
        return this.tagName.get();
    }
    public String getTagDescription(){
        return this.tagDescription.get();
    }
    public String getPlcTagName(){
        return this.plcTagName.get();
    }
    public void setTagType(String tagType){
        this.tagType.set(tagType);
    }
    public void setAlarmLevel(String alarmLevel){
        this.alarmLevel.set(alarmLevel);
    }
    public void setAlarmType(String alarmType){
        this.alarmType.set(alarmType);
    }
    public void setAlarmClassify(String alarmClassify){
        this.alarmClassify.set(alarmClassify);
    }
    public void setTagName(String tagName){
        this.tagName.set(tagName);
    }
    public void setTagDescription(String tagDescription){
        this.tagDescription.set(tagDescription);
    }
    public void setPlcTagName(String plcTagName){
        this.plcTagName.set(plcTagName);
    }
    public StringProperty tagTypeProperty(){
        if(tagType==null){
            this.tagType=new SimpleStringProperty();
        }
        return this.tagType;
    }
    public StringProperty alarmLevelProperty(){
        if(alarmLevel==null){
            this.alarmLevel=new SimpleStringProperty();
        }
        return this.alarmLevel;
    }
    public StringProperty alarmTypeProperty(){
        if(alarmType==null){
            this.alarmType=new SimpleStringProperty();
        }
        return this.alarmType;
    }

    public StringProperty alarmClassifyProperty(){
        if(alarmClassify==null){
            this.alarmClassify=new SimpleStringProperty();
        }
        return this.alarmClassify;
    }
    public StringProperty tagNameProperty(){
        if(tagName==null){
            this.tagName=new SimpleStringProperty();
        }
        return this.tagName;
    }
    public StringProperty tagDescriptionProperty(){
        if(tagDescription==null){
            this.tagDescription=new SimpleStringProperty();
        }
        return this.tagDescription;
    }
    public StringProperty plcTagNameProperty(){
        if(plcTagName==null){
            this.plcTagName=new SimpleStringProperty();
        }
        return this.plcTagName;
    }

}
